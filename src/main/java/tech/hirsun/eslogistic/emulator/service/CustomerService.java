package tech.hirsun.eslogistic.emulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private PackDao packDao;

    @Autowired
    private WorkNodeService workNodeService;

    public void batchGenPack() {
        // randomly gen 3 packs
        for (int i = 0; i < 3; i++) {
            DBPack pack = new DBPack();

            // random gen name with 6 letters
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                name.append((char) (Math.random() * 26 + 'a'));
            }
            pack.setSenderName(name.toString());

            name = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                name.append((char) (Math.random() * 26 + 'a'));
            }
            pack.setReceiverName(name.toString());

            // random gen phone number with 10 digits
            StringBuilder phone = new StringBuilder();
            phone.append("1");
            for (int j = 0; j < 10; j++) {
                phone.append((char) (Math.random() * 10 + '0'));
            }
            pack.setSenderPhone(phone.toString());

            phone = new StringBuilder();
            phone.append("1");
            for (int j = 0; j < 10; j++) {
                phone.append((char) (Math.random() * 10 + '0'));
            }
            pack.setReceiverPhone(phone.toString());

            // randomly select a station from stationsMap
            SecureRandom random = null;
            int randomInt = 0;
            try {
                random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(System.currentTimeMillis());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            randomInt= Math.abs(random.nextInt()) % workNodeService.getStationsMap().size();
            String randKey = (String) workNodeService.getStationsMap().keySet().toArray()[randomInt];
            pack.setSenderWorkNodeId(workNodeService.getStationsMap().get(randKey).getId());

            randomInt= Math.abs(random.nextInt()) % workNodeService.getStationsMap().size();
            randKey = (String) workNodeService.getStationsMap().keySet().toArray()[randomInt];
            pack.setReceiverWorkNodeId(workNodeService.getStationsMap().get(randKey).getId());

            // randomly select a pack type
            pack.setPackType((int) (Math.random() * 2) + 1);

            pack.setStatus(0);
            pack.setCurrentTransportationId(null);

            packDao.insert(pack);

        }
    }
}
