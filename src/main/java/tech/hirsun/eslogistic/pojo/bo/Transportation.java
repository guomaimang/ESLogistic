package tech.hirsun.eslogistic.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportation {

    private Long id;

    private Long driverId;
    private String type;
    private String license;

    private String startWorkNodeId;
    private String endWorkNodeId;
    // 1: Loading 2: In transit 3: Completed
    private Integer status;
    private Integer packNum;

    private Date createTime;


}
