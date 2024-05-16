package tech.hirsun.eslogistic.emulator.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distributor implements Runnable{
    WorkNode belongToStation;

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
