package tech.hirsun.eslogistic.emulator.worker;

import tech.hirsun.eslogistic.pojo.bo.WorkNode;

public class NodeProcessor implements Runnable{
    WorkNode belongToNode;

    public void run() {
        // process at most 30 packs every 1 minutes if station, and assign to a driver
        // process at most 240 packs every 1 minutes if center, and assign to a driver
        // process at most 120 packs every 1 minutes if airport, and assign to a driver
        while (true){
            try {
                // TODO: process 120 packs
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
