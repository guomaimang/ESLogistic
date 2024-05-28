package tech.hirsun.eslogistic.service;

import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.PackRecord;
import tech.hirsun.eslogistic.pojo.bo.PageBean;

import java.util.List;

public interface PackService {

    public PageBean list(Long id, String keyword, Integer pageNum, Integer pageSize, Integer status);

    public Pack info(Long id);

    public void create(Pack pack);

    public int count();

    public PageBean getPackRecords(Long id, Integer pageNum, Integer pageSize);
}
