package tech.hirsun.eslogistic.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBTransportation {
    private Long id;

    private Long driverId;
    private String type;
    private String license;

    private String startNodeId;
    private String endNodeId;
    // 1: Loading 2: In transit 3: Completed
    private Integer status;
    private Integer packNum;

    private Date createTime;
}
