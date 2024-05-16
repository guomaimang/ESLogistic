package tech.hirsun.eslogistic.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BDWorkNode {

    private String id;

    // 1. station 2. center 3. airport
    private Integer type;
    private Double coordinateX;
    private Double coordinateY;

    // 1: normal 0: offline
    private Integer status;
    // only for redis
    private Integer waitPackNum;

}
