package tech.hirsun.eslogistic.pojo.bo;

import lombok.Data;
import tech.hirsun.eslogistic.pojo.po.DBPack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Pack {
    private Long id;

    // sender info
    private String senderName;
    private String senderPhone;
    private WorkNode senderWorkNode;

    // receiver info
    private String receiverName;
    private String receiverPhone;
    private WorkNode receiverWorkNode;

    // pack info, 2: quick pack 1: normal pack
    private Integer packType;

    // pack status
    // current status
    /*
    0: Waiting for pickup
    1: In workNode
    2: In transit
    3. need to be delivered
    4: Delivered

    5: Rejected
    6: Lost
    7: Cancelled
     */
    private Integer status;
    private WorkNode currentWorkNode;

    // ["s1", "a1", "a2", "s5"]
    private List<String> plannedRoute = new ArrayList<>();

    private Date frozenTime;

    public DBPack toDBPack() {
        DBPack dbPack = new DBPack();
        dbPack.setId(id);

        dbPack.setSenderName(senderName);
        dbPack.setSenderPhone(senderPhone);
        dbPack.setSenderWorkNodeId(senderWorkNode.getId());

        dbPack.setReceiverName(receiverName);
        dbPack.setReceiverPhone(receiverPhone);
        dbPack.setReceiverWorkNodeId(receiverWorkNode.getId());

        dbPack.setPackType(packType);
        dbPack.setStatus(status);
        if (currentWorkNode != null){
            dbPack.setCurrentWorkNodeId(currentWorkNode.getId());

        }
        dbPack.setFrozenTime(frozenTime);
        return dbPack;
    }
}
