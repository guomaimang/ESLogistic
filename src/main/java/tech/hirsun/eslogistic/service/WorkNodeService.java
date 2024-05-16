package tech.hirsun.eslogistic.service;

import tech.hirsun.eslogistic.pojo.bo.WorkNode;

import java.util.List;
import java.util.Map;

public interface WorkNodeService {


    public void initService();
    public Map<String, WorkNode> getWorkNodesMap();

    public Map<String, WorkNode> getStationsMap();
    public Map<String, WorkNode> getCentersMap();
    public Map<String, WorkNode> getAirportsMap();


}
