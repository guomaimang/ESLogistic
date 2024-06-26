package tech.hirsun.eslogistic.emulator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.dao.PackRecordDao;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.pojo.bo.PackRecord;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.Date;
import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
public class NodeProcessor implements Runnable{

    @Getter @Setter
    private WorkNode belongToNode;

    private PackDao packDao;
    private PackRecordDao packRecordDao;
    private WorkNodeService workNodeService;
    private RouterService routerService;

    public void run() {
        while (true){
            try {
                List<DBPack> dbPacks;

                // station
                if (belongToNode.getType() == 1) {
                    // process collection: 10 pack 1 minutes
                    dbPacks = packDao.query(null, belongToNode.getId(), null,
                            0, null, null, null,0, 60);
                    for (DBPack dbPack : dbPacks) {
                        log.info("Pack: " + dbPack.getId() + " status " + dbPack.getStatus());
                        Pack pack = dbPack.toPack(workNodeService);
                        dbPack.setCurrentWorkNodeId(belongToNode.getId());
                        dbPack.setStatus(1);
                        packDao.update(dbPack);
                        log.info("Station: pack " + pack.getId() + " collected by " + belongToNode.getId());
                        PackRecord packRecord = new PackRecord(null, pack.getId(), "Station: pack " + pack.getId() + " collected by " + belongToNode.getId(), new Date());
                        packRecordDao.insert(packRecord);
                    }

                    // process pack has been collected, send it out, process 30 pack 1 minutes
                    dbPacks = packDao.query(null, belongToNode.getId(), null,
                            1, null, belongToNode.getId(), null,0, 30);
                    for (DBPack dbPack : dbPacks) {
                        Pack pack = dbPack.toPack(workNodeService);
                        WorkNode nextHop = routerService.nextHop(pack);
                        if (nextHop!= null) {
                            Date frozenTime = new Date();
                            frozenTime.setTime(new Date().getTime() + (long) pack.getCurrentWorkNode().getCoordinate().calDistance(nextHop.getCoordinate()) * 1000);
                            dbPack.setFrozenTime(frozenTime);
                            dbPack.setCurrentWorkNodeId(nextHop.getId());
                        }
                        dbPack.setStatus(2);
                        packDao.update(dbPack);
                        log.info("Station: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + (nextHop != null ? nextHop.getId() : null));
                        PackRecord packRecord = new PackRecord(null, pack.getId(), "Station: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + (nextHop != null ? nextHop.getId() : null), new Date());
                        packRecordDao.insert(packRecord);

                    }

                    // process pack need to be received, process 30 pack 1 minutes
                    dbPacks = packDao.query(null, null , belongToNode.getId(),
                            2, null, belongToNode.getId(), null,0, 10);
                    for (DBPack dbPack : dbPacks) {
                        if (dbPack.getFrozenTime().getTime() < new Date().getTime()) {
                            Pack pack = dbPack.toPack(workNodeService);
                            dbPack.setCurrentWorkNodeId(null);
                            dbPack.setStatus(3);
                            packDao.update(dbPack);
                            log.info("Station: pack " + pack.getId() + " received by " + belongToNode.getId());
                            PackRecord packRecord = new PackRecord(null, pack.getId(), "Station: pack " + pack.getId() + " received by " + belongToNode.getId(), new Date());
                            packRecordDao.insert(packRecord);
                        }
                    }

                    // process pack need to be delivered, process 10 pack 1 minutes
                    dbPacks = packDao.query(null, null , belongToNode.getId(),
                            3, null, belongToNode.getId(), null,0, 10);
                    for (DBPack dbPack : dbPacks) {
                        Pack pack = dbPack.toPack(workNodeService);
                        WorkNode nextHop = routerService.nextHop(pack);
                        if (nextHop!= null) {
                            dbPack.setCurrentWorkNodeId(nextHop.getId());
                        }
                        dbPack.setStatus(4);
                        packDao.update(dbPack);
                        log.info("Station: pack " + pack.getId() + " delivered by " + belongToNode.getId());
                        PackRecord packRecord = new PackRecord(null, pack.getId(), "Station: pack " + pack.getId() + " delivered by " + belongToNode.getId(), new Date());
                        packRecordDao.insert(packRecord);
                    }

                }
                // center
                else if (belongToNode.getType() == 2) {
//                    // process pack need to be sent out, process at most 240 packs every 1 minutes
//                    dbPacks = packDao.query(null, null, null,
//                            1, null, belongToNode.getId(), 0, 240);
//                    for (DBPack dbPack : dbPacks) {
//                        Pack pack = dbPack.toPack(workNodeService);
//                        WorkNode nextHop = routerService.nextHop(pack);
//                        if (nextHop!= null) {
//                            Date frozenTime = new Date();
//                            frozenTime.setTime(new Date().getTime() + (long) pack.getCurrentWorkNode().getCoordinate().calDistance(nextHop.getCoordinate()) * 1000);
//                            dbPack.setFrozenTime(frozenTime);
//                            dbPack.setCurrentWorkNodeId(nextHop.getId());
//                        }
//                        dbPack.setStatus(2);
//                        packDao.update(dbPack);
//                        log.info("Center: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + (nextHop != null ? nextHop.getId() : null));
//                        DBPackRecord dbPackRecord = new DBPackRecord(null, pack.getId(), "Center: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + (nextHop != null ? nextHop.getId() : null), new Date());
//                        packRecordDao.insert(dbPackRecord);
//                    }

                    // process pack need to be received, process at most 240 packs every 1 minutes
                    dbPacks = packDao.query(null, null, null,
                            2, null, belongToNode.getId(), null,0, 240);
                    for (DBPack dbPack : dbPacks) {
                        if (dbPack.getFrozenTime().getTime() < new Date().getTime()) {
                            Pack pack = dbPack.toPack(workNodeService);
                            WorkNode nextHop = routerService.nextHop(pack);
                            if (nextHop!= null) {
                                dbPack.setCurrentWorkNodeId(nextHop.getId());
                            }
                            dbPack.setStatus(2);
                            packDao.update(dbPack);
                            log.info("Center: pack " + pack.getId() + " received by " + belongToNode.getId() + " and to " + (nextHop != null ? nextHop.getId() : null));
                            PackRecord packRecord = new PackRecord(null, pack.getId(), "Center: pack " + pack.getId() + " received by " + belongToNode.getId() + " and to " + (nextHop != null ? nextHop.getId() : null), new Date());
                            packRecordDao.insert(packRecord);
                        }
                    }
                }
                // airport
                else if (belongToNode.getType() == 3) {
//                    // process pack need to be sent out, process at most 120 packs every 1 minutes
//                    dbPacks = packDao.query(null, null, null,
//                            1, null, belongToNode.getId(), 0, 120);
//                    for (DBPack dbPack : dbPacks) {
//                        Pack pack = dbPack.toPack(workNodeService);
//                        WorkNode nextHop = routerService.nextHop(pack);
//                        if (nextHop!= null) {
//                            Date frozenTime = new Date();
//                            frozenTime.setTime(new Date().getTime() + (long) pack.getCurrentWorkNode().getCoordinate().calDistance(nextHop.getCoordinate()) * 100);
//                            dbPack.setFrozenTime(frozenTime);
//                            dbPack.setCurrentWorkNodeId(nextHop.getId());
//                        }
//                        dbPack.setStatus(2);
//                        packDao.update(dbPack);
//                        log.info("Airport: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + nextHop.getId());
//                        DBPackRecord dbPackRecord = new DBPackRecord(null, pack.getId(), "Airport: pack " + pack.getId() + " sent out by " + belongToNode.getId() + " to " + nextHop.getId(), new Date());
//                        packRecordDao.insert(dbPackRecord);
//                    }

                    // process pack need to be received, process at most 120 packs every 1 minutes
                    dbPacks = packDao.query(null, null, null,
                            2, null, belongToNode.getId(), null,0, 120);
                    for (DBPack dbPack : dbPacks) {
                        if (dbPack.getFrozenTime().getTime() < new Date().getTime()) {
                            Pack pack = dbPack.toPack(workNodeService);

                            WorkNode nextHop = routerService.nextHop(pack);
                            if (nextHop!= null) {
                                dbPack.setCurrentWorkNodeId(nextHop.getId());
                            }
                            dbPack.setStatus(2);
                            packDao.update(dbPack);
                            log.info("Airport: pack " + pack.getId() + " received by " + belongToNode.getId() + " and to " + (nextHop != null ? nextHop.getId() : null));
                            PackRecord packRecord = new PackRecord(null, pack.getId(), "Airport: pack " + pack.getId() + " received by " + belongToNode.getId() + " and to " + (nextHop != null ? nextHop.getId() : null), new Date());
                            packRecordDao.insert(packRecord);
                        }
                    }
                }
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
