package tech.hirsun.eslogistic.service.Impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.WorkNodeDao;
import tech.hirsun.eslogistic.pojo.bo.PageBean;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.pojo.po.DBWorkNode;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.ArrayList;
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

        List<DBWorkNode> allDBWorkNodes = workNodeDao.queryAll();
        List<WorkNode> allWorkNodes = new ArrayList<>();
        for (DBWorkNode dbWorkNode : allDBWorkNodes) {
            allWorkNodes.add(dbWorkNode.toWorkNode());
        }

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

    @Override
    public PageBean list(String id, int pageNum, int pageSize) {
        int count = workNodeDao.count(id, (pageNum - 1) * pageSize, pageSize);

        int start = (pageNum - 1) * pageSize;
        List<DBWorkNode> dbWorkNodes = workNodeDao.query(id, start, pageSize);

        List<WorkNode> workNodes = new ArrayList<>();
        for (DBWorkNode dbWorkNode : dbWorkNodes) {
            workNodes.add(dbWorkNode.toWorkNode());
        }

        return new PageBean(count, workNodes, Math.floorDiv(count, pageSize) + 1, pageNum);
    }

    @Override
    public WorkNode info(String id) {
        return workNodeDao.query(id, null, null).get(0).toWorkNode();
    }

    @Override
    public int count() {
        return workNodeDao.count(null,null, null);
    }


}

