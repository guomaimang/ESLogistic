package tech.hirsun.eslogistic.emulator.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collector implements Runnable{
    WorkNode belongToStation;

    public void run() {
        // collect 1 pack every 1 second
        while (true){
            try {
                // TODO: collect 1 pack
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
