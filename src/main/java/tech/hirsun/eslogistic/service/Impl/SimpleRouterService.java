package tech.hirsun.eslogistic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.pojo.Pack;
import tech.hirsun.eslogistic.pojo.WorkNode;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.dataBean.RegisteredWorkNodes;

import java.util.*;

@Service
public class SimpleRouterService implements RouterService {

    @Autowired
    private RegisteredWorkNodes registeredWorkNodes;

    public String nextHop(Pack pack) {
        WorkNode startWorkNode = registeredWorkNodes.getWorkNodes().get(pack.getSenderWorkNodeId());

        // find the nearest center of the start station
        String nearestCenterIDofStartStation = registeredWorkNodes.getStationToNearestCenter().get(pack.getSenderWorkNodeId());
        WorkNode nearestStartCenter = registeredWorkNodes.getWorkNodes().get(nearestCenterIDofStartStation);
        double distanceOfNearestCenterOfStartStation = startWorkNode.getCoordinate().calDistance(nearestStartCenter.getCoordinate());

        // find the nearest center of the end station
        String nearestCenterOfEndStationID = registeredWorkNodes.getStationToNearestCenter().get(pack.getReceiverWorkNodeId());
        WorkNode nearestCenterOfEndStation = registeredWorkNodes.getWorkNodes().get(nearestCenterOfEndStationID);
        double distanceOfNearestCenterOfEndStation = nearestStartCenter.getCoordinate().calDistance(nearestCenterOfEndStation.getCoordinate());

        // find the distance of two centers
        double distanceOfTwoCenters = nearestStartCenter.getCoordinate().calDistance(nearestCenterOfEndStation.getCoordinate());

        // find the nearest airport of the start Center
        String nearestAirportOfStartCenterID = registeredWorkNodes.getCenterToNearestAirport().get(nearestCenterIDofStartStation);
        WorkNode nearestAirportOfStartCenter = registeredWorkNodes.getWorkNodes().get(nearestAirportOfStartCenterID);
        double distanceOfNearestAirportOfStartCenter = nearestStartCenter.getCoordinate().calDistance(nearestAirportOfStartCenter.getCoordinate());

        // find the nearest airport of the end center
        String nearestAirportOfEndCenterID = registeredWorkNodes.getCenterToNearestAirport().get(nearestCenterOfEndStationID);
        WorkNode nearestAirportOfEndStation = registeredWorkNodes.getWorkNodes().get(nearestAirportOfEndCenterID);
        double distanceOfNearestAirportOfEndCenter = nearestCenterOfEndStation.getCoordinate().calDistance(nearestAirportOfEndStation.getCoordinate());

        // find the distance of two airports
        double distanceOfTwoAirports = nearestAirportOfStartCenter.getCoordinate().calDistance(nearestAirportOfEndStation.getCoordinate());


        // if the priority is 1
        // start station -> nearest center of start station -> nearest center of end station -> end station
        if (pack.getPackType() == 1) {
            // if the pack is in the end station, return null
            if (Objects.equals(pack.getCurrentWorkNodeId(), pack.getReceiverWorkNodeId())) {
                return null;
            }
            // if the pack is in the start station, return the nearest center of start station
            if (Objects.equals(pack.getCurrentWorkNodeId(), pack.getSenderWorkNodeId())) {
                return nearestCenterIDofStartStation;
            }

            // if the pack is in the center
            // if the current center is the nearest center of the end station, return the end station
            if (Objects.equals(pack.getCurrentWorkNodeId(), nearestCenterOfEndStation.getId())) {
                return pack.getReceiverWorkNodeId();
            }
            // if the current center is the nearest center of the start station, return the nearest center of the end station
            if (Objects.equals(pack.getCurrentWorkNodeId(), nearestCenterIDofStartStation)) {
                return nearestCenterOfEndStation.getId();
            }
        }

        // if the priority is 2
        // start station -> nearest center of start station -> nearest center of end station -> end station
        // or start station -> nearest center of start station -> nearest airport of start center -> nearest airport of end center -> nearest center of end station -> end station
        if (pack.getPackType() == 2) {
            // 0: land 1: air
            int way = 0;
            // Assume the average speed of the truck is 1/h, the average speed of the plane is 10/h
            // economies of scale
            double travelViaCenterTime = distanceOfNearestCenterOfStartStation + distanceOfTwoCenters + distanceOfNearestCenterOfEndStation;
            double travelViaAirportTime = distanceOfNearestCenterOfStartStation + distanceOfNearestAirportOfStartCenter + distanceOfTwoAirports/10 + distanceOfNearestAirportOfEndCenter + distanceOfNearestCenterOfEndStation;
            if (travelViaCenterTime > travelViaAirportTime) {
                way = 1;
            }

            // if the pack is in the end station, return null
            if (Objects.equals(pack.getCurrentWorkNodeId(), pack.getReceiverWorkNodeId())) {
                return null;
            }

            // if the pack is in the start station
            if (Objects.equals(pack.getCurrentWorkNodeId(), pack.getSenderWorkNodeId())) {
                if (way == 0) {
                    return nearestCenterIDofStartStation;
                } else {
                    return nearestAirportOfStartCenterID;
                }
            }
            // if in the center nearest to the end station
            if (Objects.equals(pack.getCurrentWorkNodeId(), nearestCenterOfEndStation.getId())) {
                return pack.getReceiverWorkNodeId();
            }
            // if in the center nearest to the start station
            if (Objects.equals(pack.getCurrentWorkNodeId(), nearestCenterIDofStartStation)) {
                if (way == 0) {
                    return nearestCenterOfEndStation.getId();
                } else {
                    return nearestAirportOfEndCenterID;
                }
            }


        }
        return null;
    }

    public List route(Map stationsBase, Map StationsPackAmount,
                      Map RoutesPackAmount, String packFrom, String packTo, int packPriority) {
        return null;
    }

    private void studyRoute(){
        Set<WorkNode> stationsSet = new HashSet<WorkNode>();
        Set<WorkNode> centersSet = new HashSet<WorkNode>();
        Set<WorkNode> airportsSet = new HashSet<WorkNode>();
        for (WorkNode workNode : registeredWorkNodes.getWorkNodes().values()) {
            if (workNode.getType() == 1) {
                stationsSet.add(workNode);
            } else if (workNode.getType() == 2) {
                centersSet.add(workNode);
            } else if (workNode.getType() == 3) {
                airportsSet.add(workNode);
            }
        }

        // for each station, find the nearest center
        for (WorkNode station : stationsSet) {
            WorkNode nearestCenter = null;
            double minDistance = Double.MAX_VALUE;

            for (WorkNode center : centersSet) {
                double distance = station.getCoordinate().calDistance(center.getCoordinate());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCenter = center;
                }
            }
            registeredWorkNodes.getStationToNearestCenter().put(station.getId(), nearestCenter.getId());
        }

        // for each center, find the nearest airport
        for (WorkNode center : centersSet) {
            WorkNode nearestAirport = null;
            double minDistance = Double.MAX_VALUE;

            for (WorkNode airport : airportsSet) {
                double distance = center.getCoordinate().calDistance(airport.getCoordinate());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestAirport = airport;
                }
            }
            registeredWorkNodes.getCenterToNearestAirport().put(center.getId(), nearestAirport.getId());
        }
    }
}
