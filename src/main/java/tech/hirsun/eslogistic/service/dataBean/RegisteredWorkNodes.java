package tech.hirsun.eslogistic.service.dataBean;

import lombok.Getter;
import org.springframework.stereotype.Component;
import tech.hirsun.eslogistic.pojo.WorkNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Component
public class RegisteredWorkNodes {

    private Map<String, WorkNode> workNodes = new HashMap<>();
    private Map<String, String> stationToNearestCenter;
    private Map<String, String> centerToNearestAirport;

    public void addWorkNode(WorkNode workNode) {
        workNodes.put(workNode.getId(), workNode);
    }

}
