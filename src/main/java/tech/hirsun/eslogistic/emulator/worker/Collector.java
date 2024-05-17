package tech.hirsun.eslogistic.emulator.worker;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.TransportationService;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.List;

@AllArgsConstructor
public class Collector implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(Collector.class);

    @Getter @Setter
    private WorkNode belongToStation;
    @Getter @Setter
    private PackDao packDao;
    @Getter @Setter
    private WorkNodeService workNodeService;
    @Getter @Setter
    private TransportationService transportationService;
    @Getter @Setter
    private RouterService routerService;

    public void run() {
        // collect 1 pack every 1 second
        while (true){
            try {
                // collect 1 pack
                List<DBPack> dbPacks = packDao.query(null, belongToStation.getId(), null,
                        0, null, null, 0, 1);
                for (DBPack dbPack : dbPacks) {
                    Pack pack = dbPack.toPack(workNodeService, transportationService);
                    dbPack.setCurrentWorkNodeId(routerService.nextHop(pack).getId());
                    dbPack.setStatus(1);
                    packDao.update(dbPack);
                    log.info("Collector: pack " + pack.getId() + " collected by " + belongToStation.getId());
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
