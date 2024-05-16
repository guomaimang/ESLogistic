package tech.hirsun.eslogistic.service;


import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

import java.util.List;
import java.util.Map;

public interface RouterService {

    public List<String> route (Map stationsBase, Map StationsPackAmount,
                      Map RoutesPackAmount, String packFrom, String packTo, int packPriority);

    public WorkNode nextHop(Pack pack);

    public void initService();
}
