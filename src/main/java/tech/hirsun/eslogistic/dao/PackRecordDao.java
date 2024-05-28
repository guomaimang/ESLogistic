package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.hirsun.eslogistic.pojo.bo.PackRecord;

import java.util.List;

@Mapper
public interface PackRecordDao {

    @Insert("insert into pack_record(pack_id, message, create_time) values(#{packId}, #{message}, #{createTime})")
    public void insert(PackRecord packRecord);

    @Select("select * from pack_record where pack_id = #{packId}")
    public List<PackRecord> query(Long packId);
}
