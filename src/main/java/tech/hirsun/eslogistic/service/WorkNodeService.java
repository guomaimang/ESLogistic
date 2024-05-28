package tech.hirsun.eslogistic.service;

import tech.hirsun.eslogistic.pojo.bo.PageBean;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import java.util.Map;

public interface WorkNodeService {


    public void initService();
    public Map<String, WorkNode> getWorkNodesMap();

    public Map<String, WorkNode> getStationsMap();
    public Map<String, WorkNode> getCentersMap();
    public Map<String, WorkNode> getAirportsMap();

    public PageBean list(String id, int pageNum, int pageSize);

    public WorkNode info(String id);

    public int count();

}
