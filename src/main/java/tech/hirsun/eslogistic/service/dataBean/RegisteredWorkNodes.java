package tech.hirsun.eslogistic.service.dataBean;

import lombok.Getter;
import org.springframework.stereotype.Component;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class RegisteredWorkNodes {

    private Map<String, WorkNode> workNodesMap = new HashMap<>();

    private Map<WorkNode, WorkNode> stationToNearestCenter;
    private Map<WorkNode, WorkNode> centerToNearestAirport;

    public void addWorkNode(WorkNode workNode) {
        workNodesMap.put(workNode.getId(), workNode);
    }

}
