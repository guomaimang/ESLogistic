package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.hirsun.eslogistic.pojo.po.DBWorkNode;

import java.util.List;

@Mapper
public interface WorkNodeDao {

    // By Annotation

    @Select("select * from worknode")
    public List<DBWorkNode> queryAll();

    // By XML

    public int count(String id, Integer start, Integer pageSize);
    public List<DBWorkNode> query(String id, Integer start, Integer pageSize);



}
