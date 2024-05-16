package tech.hirsun.eslogistic.emulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.hirsun.eslogistic.emulator.worker.Collector;
import tech.hirsun.eslogistic.emulator.worker.Distributor;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.List;

@Component
public class Master {


    @Autowired
    private WorkNodeService workNodeService;

    public void initService() {
        // create collector for each station
        for (WorkNode station : workNodeService.getStationsMap().values()) {
            Collector collector = new Collector(station);
            Thread thread = new Thread(collector);
            thread.start();
        }

        // create distributor for each station
        for (WorkNode station : workNodeService.getStationsMap().values()) {
            Distributor distributor = new Distributor(station);
            Thread thread = new Thread(distributor);
            thread.start();
        }

        // create driver for each workNode
        for (WorkNode node : workNodeService.getWorkNodesMap().values()) {
            Distributor distributor = new Distributor(node);
            Thread thread = new Thread(distributor);
            thread.start();
        }

        // create node


    }

}
