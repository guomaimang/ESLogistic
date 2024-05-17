package tech.hirsun.eslogistic.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;
import tech.hirsun.eslogistic.pojo.po.DBPack;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootTest
class RouterServiceTest {

    private static final Logger log = LoggerFactory.getLogger(RouterServiceTest.class);
    @Autowired
    private WorkNodeService workNodeService;

    @Autowired
    private RouterService routerService;

    @Autowired
    private TransportationService transportationService;

    @Test
    void nextHop() {
        DBPack dbPack = new DBPack();

        // random gen name with 6 letters
        StringBuilder name = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            name.append((char) (Math.random() * 26 + 'a'));
        }
        dbPack.setSenderName(name.toString());

        name = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            name.append((char) (Math.random() * 26 + 'a'));
        }
        dbPack.setReceiverName(name.toString());

        // random gen phone number with 10 digits
        StringBuilder phone = new StringBuilder();
        phone.append("1");
        for (int j = 0; j < 10; j++) {
            phone.append((char) (Math.random() * 10 + '0'));
        }
        dbPack.setSenderPhone(phone.toString());

        phone = new StringBuilder();
        phone.append("1");
        for (int j = 0; j < 10; j++) {
            phone.append((char) (Math.random() * 10 + '0'));
        }
        dbPack.setReceiverPhone(phone.toString());

        // randomly select a station from stationsMap
        SecureRandom random = null;
        int randomInt;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        randomInt= Math.abs(random.nextInt()) % workNodeService.getStationsMap().size();
        String randKey = (String) workNodeService.getStationsMap().keySet().toArray()[randomInt];
        dbPack.setSenderWorkNodeId(workNodeService.getStationsMap().get(randKey).getId());
        dbPack.setCurrentWorkNodeId(dbPack.getSenderWorkNodeId());

        randomInt= Math.abs(random.nextInt()) % workNodeService.getStationsMap().size();
        randKey = (String) workNodeService.getStationsMap().keySet().toArray()[randomInt];
        dbPack.setReceiverWorkNodeId(workNodeService.getStationsMap().get(randKey).getId());

        // randomly select a pack type
        dbPack.setPackType((int) (Math.random() * 2) + 1);

        dbPack.setStatus(0);
        dbPack.setCurrentTransportationId(null);

        log.info("Start station: {}, end station: {}", dbPack.getSenderWorkNodeId(), dbPack.getReceiverWorkNodeId());

        Pack pack = dbPack.toPack(workNodeService, transportationService);
        pack.setPackType(2);

        WorkNode nextHop = routerService.nextHop(pack);
        log.info("Current station: {}, Next station: {}", pack.getCurrentWorkNode().getId(), nextHop.getId());

        while(nextHop != null){
            pack.setCurrentWorkNode(nextHop);
            nextHop = routerService.nextHop(pack);
            log.info("Current station: {}, Next station: {}", pack.getCurrentWorkNode().getId(), nextHop.getId());
        }
    }

}