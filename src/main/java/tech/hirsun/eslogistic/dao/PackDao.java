package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.po.DBPack;

import java.util.List;

@Mapper
public interface PackDao {

    // By Annotation
    @Insert("insert into pack(" +
            "sender_name, sender_phone, sender_work_node_id, " +
            "receiver_name, receiver_phone, receiver_work_node_id, " +
            "pack_type, status, current_transportation_id, current_work_node_id) " +
            "values(" +
            " #{senderName}, #{senderPhone}, #{senderWorkNodeId},"
            + "#{receiverName}, #{receiverPhone}, #{receiverWorkNodeId}," +
              "#{packType}, #{status}, #{currentTransportationId}, #{currentWorkNodeId})")
    public void insert(DBPack dbpack);


    // By XML
    public List<DBPack> query(@Param("id") Long id,
                            @Param("senderWorkNodeId") String senderWorkNodeId,
                            @Param("receiverWorkNodeId") String receiverWorkNodeId,
                            @Param("status") Integer status,
                            @Param("packType") Integer packType,
                            @Param("currentWorkNodeId") String currentWorkNodeId,
                            @Param("start") Integer start,
                            @Param("pageSize") Integer pageSize);

    public void update(DBPack dbPack);
}
