package tech.hirsun.eslogistic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.hirsun.eslogistic.pojo.bo.WorkNode;

import java.util.List;

@Mapper
public interface WorkNodeDao {

    @Select("select * from worknode")
    public List<WorkNode> queryAll();
}
