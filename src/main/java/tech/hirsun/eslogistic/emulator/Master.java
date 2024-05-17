package tech.hirsun.eslogistic.emulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.WorkNodeService;


@Component
public class Master {

    @Autowired
    private WorkNodeService workNodeService;
    @Autowired
    private PackDao packDao;
    @Autowired
    private RouterService routerService;

    public void initService() {
        // create node processor for each workNode
        for (WorkNode node : workNodeService.getWorkNodesMap().values()) {
            NodeProcessor nodeProcessor = new NodeProcessor(node, packDao, workNodeService, routerService);
            Thread thread = new Thread(nodeProcessor);
            thread.start();
        }
    }

}
