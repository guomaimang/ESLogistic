package tech.hirsun.eslogistic.emulator.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.TransportationService;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
public class NodeProcessor implements Runnable{

    @Getter @Setter
    private WorkNode belongToNode;
    @Getter @Setter
    private PackDao packDao;
    @Getter @Setter
    private WorkNodeService workNodeService;
    @Getter @Setter
    private TransportationService transportationService;
    @Getter @Setter
    private RouterService routerService;

    public void run() {
        while (true){
            try {
                if (belongToNode.getType() == 1) {
                    // station

                    // process collection: 60 pack 1 minutes
                    List<DBPack> dbPacks = packDao.query(null, belongToNode.getId(), null,
                            0, null, null, 0, 1);
                    for (DBPack dbPack : dbPacks) {
                        Pack pack = dbPack.toPack(workNodeService, transportationService);
                        dbPack.setCurrentWorkNodeId(routerService.nextHop(pack).getId());
                        dbPack.setStatus(1);
                        packDao.update(dbPack);
                        log.info("Collector: pack " + pack.getId() + " collected by " + belongToStation.getId());
                    }

                    // process pack has been collected
                    // process at most 30 packs every 1 minutes if this is station, and assign to a driver
                    dbPacks = packDao.query(null, null, null,
                            1, null, belongToNode.getId(), 0, 30);

                    // process pack need to



                } else if (belongToNode.getType() == 2) {
                    // center
                    // process at most 240 packs every 1 minutes if this is center, and assign to a driver
                    dbPacks = packDao.query(null, null, null,
                            1, null, belongToNode.getId(), 0, 240);
                } else if (belongToNode.getType() == 3) {
                    // airport
                    // process at most 120 packs every 1 minutes if this is airport, and assign to a driver
                    dbPacks = packDao.query(null, null, null,
                            1, null, belongToNode.getId(), 0, 120);
                }

                for (DBPack dbPack : dbPacks) {
                    // assign to a driver
                    dbPack.setCurrentWorkNodeId(0);
                    dbPack.setStatus(1);
                    packDao.update(dbPack);
                }


                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
