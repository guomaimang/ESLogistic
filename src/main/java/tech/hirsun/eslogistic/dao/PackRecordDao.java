package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import tech.hirsun.eslogistic.pojo.bo.PackRecord;

import java.util.List;

@Mapper
public interface PackRecordDao {

    // By Annotation
    @Insert("insert into pack_record(pack_id, message, create_time) values(#{packId}, #{message}, #{createTime})")
    public void insert(PackRecord packRecord);

    // By XML
    public List<PackRecord> query(Long packId, Integer start, Integer pageSize);
    public Integer count(Long packId);
}
