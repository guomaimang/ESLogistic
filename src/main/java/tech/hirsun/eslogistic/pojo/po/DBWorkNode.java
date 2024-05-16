package tech.hirsun.eslogistic.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hirsun.eslogistic.pojo.bo.Coordinate;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBWorkNode {

    private String id;

    // 1. station 2. center 3. airport
    private Integer type;
    private Double coordinateX;
    private Double coordinateY;

    // 1: normal 0: offline
    private Integer status;

    public WorkNode toWorkNode() {
        WorkNode workNode = new WorkNode();
        workNode.setId(id);
        workNode.setType(type);
        workNode.setCoordinate(new Coordinate(coordinateX, coordinateY));
        workNode.setStatus(status);
        return workNode;
    }

}
