package tech.hirsun.eslogistic.service;


import java.util.List;
import java.util.Map;

public interface RouterService {

    public List<String> route (Map stationsBase, Map StationsPackAmount,
                      Map RoutesPackAmount, String packFrom, String packTo, int packPriority);
}
