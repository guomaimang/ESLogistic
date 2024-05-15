package tech.hirsun.eslogistic.pojo;

import lombok.Data;

@Data
public class Transportation {

    private Long id;

    private Long driverId;
    private String type;
    private String identifier;

    private Long startNodeId;
    private Long endNodeId;
    // 0: Loading 1: In transit 2: Completed
    private Integer status;

    private Integer packNum;

    private String createTime;
}
