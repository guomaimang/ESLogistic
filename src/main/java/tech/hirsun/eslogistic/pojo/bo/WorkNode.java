package tech.hirsun.eslogistic.pojo.bo;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkNode {

    private String id;

    // 1. station 2. center 3. airport
    private Integer type;
    private Coordinate coordinate;

    // 1: normal 0: offline
    private Integer status;

    private LinkedBlockingQueue<Pack> packsStorage = new LinkedBlockingQueue<>();

}
