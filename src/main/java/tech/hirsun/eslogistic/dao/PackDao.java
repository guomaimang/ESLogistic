package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import tech.hirsun.eslogistic.pojo.po.DBPack;

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





}
