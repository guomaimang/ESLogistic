package tech.hirsun.eslogistic.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BDPack {

    private Long id;

    // sender info
    private String senderName;
    private String senderPhone;
    private String senderWorkNodeId;

    // receiver info
    private String receiverName;
    private String receiverPhone;
    private String receiverWorkNodeId;

    // pack info, 2: quick pack 1: normal pack
    private Integer packType;

    // pack status
    // current status
    /*
    0: Waiting for pickup
    1: Loading
    2: In transit
    3: Delivered

    4: Waiting for delivery
    5: Rejected
    6: Lost
    7: Cancelled
     */
    private Integer status;
    // if status is 2, this field is valid, else null.
    private Long currentTransportationId;
    // if status is 1/4/5/6/7, this field is valid, else null.
    private String currentWorkNodeId;
    // ["s1", "a1", "a2", "s5"]
    private List plannedRoute = new ArrayList<String>();

}
