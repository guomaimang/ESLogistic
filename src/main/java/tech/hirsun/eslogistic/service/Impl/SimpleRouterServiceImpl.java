package tech.hirsun.eslogistic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.dataBean.RegisteredWorkNodes;

import java.util.*;

@Service
public class SimpleRouterServiceImpl implements RouterService {

    @Autowired
    private RegisteredWorkNodes registeredWorkNodes;

    public WorkNode nextHop(Pack pack) {

        // find the nearest center of the start station
        WorkNode nearestCenterOfStartStation = registeredWorkNodes.getStationToNearestCenter().get(pack.getSenderWorkNode());
        // find the distance of the nearest center of the start station
        double distanceOfNearestCenterOfStartStation = pack.getSenderWorkNode().getCoordinate().calDistance(nearestCenterOfStartStation.getCoordinate());
        // find the nearest center of the end station
        WorkNode nearestCenterOfEndStation = registeredWorkNodes.getStationToNearestCenter().get(pack.getReceiverWorkNode());
        // find the distance of the nearest center of the end station
        double distanceOfNearestCenterOfEndStation = pack.getReceiverWorkNode().getCoordinate().calDistance(nearestCenterOfEndStation.getCoordinate());
        // find the distance of two centers
        double distanceOfTwoCenters = nearestCenterOfStartStation.getCoordinate().calDistance(nearestCenterOfEndStation.getCoordinate());

        // find the nearest airport of the start Center
        WorkNode nearestAirportOfStartCenter = registeredWorkNodes.getCenterToNearestAirport().get(nearestCenterOfStartStation);
        // find the distance of the nearest airport of the start center
        double distanceOfNearestAirportOfStartCenter = nearestCenterOfStartStation.getCoordinate().calDistance(nearestAirportOfStartCenter.getCoordinate());
        // find the nearest airport of the end center
        WorkNode nearestAirportOfEndCenter = registeredWorkNodes.getCenterToNearestAirport().get(nearestCenterOfEndStation);
        // find the distance of the nearest airport of the end center
        double distanceOfNearestAirportOfEndCenter = nearestCenterOfEndStation.getCoordinate().calDistance(nearestAirportOfEndCenter.getCoordinate());
        // find the distance of two airports
        double distanceOfTwoAirports = nearestAirportOfStartCenter.getCoordinate().calDistance(nearestAirportOfEndCenter.getCoordinate());


        // if the priority is 1
        // start station -> nearest center of start station -> nearest center of end station -> end station
        if (pack.getPackType() == 1) {
            // if the pack is in the end station, return null
           if (Objects.equals(pack.getCurrentWorkNode(), pack.getReceiverWorkNode())) {
                return null;
            }
            // if the pack is in the start station, return the nearest center of start station
            if (Objects.equals(pack.getCurrentWorkNode(), pack.getSenderWorkNode())) {
                return nearestCenterOfStartStation;
            }

            // if the pack is in the center
            // if the current center is the nearest center of the end station, return the end station
            if (Objects.equals(pack.getCurrentWorkNode(), nearestCenterOfEndStation)) {
                return pack.getReceiverWorkNode();
            }

            // if the current center is the nearest center of the start station, return the nearest center of the end station
            if (Objects.equals(pack.getCurrentWorkNode(), nearestCenterOfStartStation)) {
                return nearestCenterOfEndStation;
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

            // in the station
            // if the pack is in the end station, return null
            if (Objects.equals(pack.getCurrentWorkNode(), pack.getReceiverWorkNode())) {
                return null;
            }
            // if the pack is in the start station
            if (Objects.equals(pack.getCurrentWorkNode(), pack.getSenderWorkNode())) {
                return nearestCenterOfStartStation;
            }

            // in the airport
            // if in the airport nearest to the end center
            if (Objects.equals(pack.getCurrentWorkNode(), nearestAirportOfEndCenter)){
                return nearestCenterOfEndStation;
            }
            // if in the airport nearest to the start center
            if (Objects.equals(pack.getCurrentWorkNode(), nearestAirportOfStartCenter)){
                    return nearestCenterOfEndStation;
            }

            // in the center
            // if in the center nearest to the end station
            if (Objects.equals(pack.getCurrentWorkNode(), nearestCenterOfEndStation)){
                return pack.getReceiverWorkNode();
            }
            // if in the center nearest to the start station
            if (Objects.equals(pack.getCurrentWorkNode(), nearestCenterOfStartStation)){
                if (way == 0){
                    return nearestCenterOfEndStation;
                } else {
                    return nearestAirportOfEndCenter;
                }
            }
        }
        return null;
    }

    public List<String> route(Map stationsBase, Map StationsPackAmount,
                      Map RoutesPackAmount, String packFrom, String packTo, int packPriority) {
        return null;
    }

    private void studyRoute(){
        Set<WorkNode> stationsSet = new HashSet<>();
        Set<WorkNode> centersSet = new HashSet<>();
        Set<WorkNode> airportsSet = new HashSet<>();
        for (WorkNode workNode : registeredWorkNodes.getWorkNodesMap().values()) {
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
            registeredWorkNodes.getStationToNearestCenter().put(station, nearestCenter);
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
            registeredWorkNodes.getCenterToNearestAirport().put(center, nearestAirport);
        }
    }
}
