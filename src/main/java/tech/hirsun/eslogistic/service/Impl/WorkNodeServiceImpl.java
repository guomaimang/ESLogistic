package tech.hirsun.eslogistic.service.Impl;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.WorkNodeDao;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WorkNodeServiceImpl implements WorkNodeService {

    @Autowired
    private WorkNodeDao workNodeDao;

    @Getter
    private Map<String, WorkNode> workNodesMap;

    @Getter
    private Map<String, WorkNode> stationsMap;
    @Getter
    private Map<String, WorkNode> centersMap;
    @Getter
    private Map<String, WorkNode> airportsMap;


    public void initService() {
        workNodesMap = new HashMap<>();

        stationsMap = new HashMap<>();
        centersMap = new HashMap<>();
        airportsMap = new HashMap<>();

        List<WorkNode> allWorkNodes = workNodeDao.queryAll();

        for (WorkNode workNode : allWorkNodes) {
            workNodesMap.put(workNode.getId(), workNode);

            if (workNode.getType() == 1) {
                stationsMap.put(workNode.getId(), workNode);
            } else if (workNode.getType() == 2) {
                centersMap.put(workNode.getId(), workNode);
            } else if (workNode.getType() == 3) {
                airportsMap.put(workNode.getId(), workNode);
            }
        }

    }

}

