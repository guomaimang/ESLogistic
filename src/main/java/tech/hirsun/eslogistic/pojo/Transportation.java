package tech.hirsun.eslogistic.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Transportation {

    private Long id;

    private Long driverId;
    private String type;
    private String license;

    private Long startNodeId;
    private Long endNodeId;
    // 1: Loading 2: In transit 3: Completed
    private Integer status;
    private Integer packNum;

    private Date createTime;
}
