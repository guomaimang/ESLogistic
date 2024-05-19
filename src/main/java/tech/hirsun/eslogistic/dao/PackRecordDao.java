package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.hirsun.eslogistic.pojo.po.DBPackRecord;

import java.util.List;

@Mapper
public interface PackRecordDao {

    @Insert("insert into pack_record(pack_id, message, create_time) values(#{packId}, #{message}, #{createTime})")
    public void insert(DBPackRecord dbPackRecord);

    @Select("select * from pack_record where pack_id = #{packId}")
    public List<DBPackRecord> query(DBPackRecord dbPackRecord);
}
