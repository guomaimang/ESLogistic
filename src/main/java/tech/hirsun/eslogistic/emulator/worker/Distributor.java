package tech.hirsun.eslogistic.emulator.worker;

import lombok.*;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

@Data
@Service
public class Distributor implements Runnable{
    @Getter @Setter
    private WorkNode belongToStation;

    public void run() {
        // distribute 10 pack every 1 second
        while (true){
            try {
                // TODO: distribute 10 pack
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
