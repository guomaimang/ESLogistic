package tech.hirsun.eslogistic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.PageBean;
import tech.hirsun.eslogistic.service.PackService;


@Service
public class PackServiceImpl implements PackService {
    @Autowired
    private PackDao packDao;

    @Override
    public PageBean list(Long id, String keyword, int pageNum, int pageSize, int status) {
    }

    @Override
    public Pack info(Long id) {
        return null;
    }

    @Override
    public void create(Pack pack) {

    }

    @Override
    public int count(Long id, String keyword) {
        return 0;
    }
}
