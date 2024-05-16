package tech.hirsun.eslogistic.pojo.bo;

import lombok.*;
import org.springframework.stereotype.Service;

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

    // only for redis
    // private Integer waitPackNum;

}
