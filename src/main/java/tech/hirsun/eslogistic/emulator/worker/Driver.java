package tech.hirsun.eslogistic.emulator.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hirsun.eslogistic.pojo.bo.Transportation;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver implements Runnable{

    private Transportation transportation;
    private Double distance;

    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
                // TODO: put all packs into the queue of next node
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
