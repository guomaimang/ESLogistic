package tech.hirsun.eslogistic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.dao.PackDao;
import tech.hirsun.eslogistic.dao.PackRecordDao;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.pojo.bo.PackRecord;
import tech.hirsun.eslogistic.pojo.bo.PageBean;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.service.PackService;
import tech.hirsun.eslogistic.service.WorkNodeService;

import java.util.ArrayList;
import java.util.List;


@Service
public class PackServiceImpl implements PackService {
    @Autowired
    private PackDao packDao;

    @Autowired
    private PackRecordDao packRecordDao;

    @Autowired
    private WorkNodeService workNodeService;

    @Override
    public PageBean list(Long id, String keyword, Integer pageNum, Integer pageSize, Integer status) {
        int count = packDao.count(id, null, null, status, null, null, keyword);

        int start = (pageNum-1) * pageSize;
        List<DBPack> DBPacks = packDao.query(id, null, null, status, null, null, keyword, start, pageSize);

        // convert DBPack to Pack, and save it to packs
        List<Pack> packs = new ArrayList<>();
        for (DBPack dbPack : DBPacks) {
            packs.add(dbPack.toPack(workNodeService));
        }

        return new PageBean(count, packs, Math.floorDiv(count, pageSize) + 1, pageNum);
    }

    @Override
    public Pack info(Long id) {
        return packDao.query(id, null, null, null, null, null, null, 0, 1).get(0).toPack(workNodeService);
    }

    @Override
    public void create(Pack pack) {
        DBPack dbPack = new DBPack();
        packDao.insert(dbPack);
    }

    @Override
    public int count() {
        return packDao.count(null, null, null, null, null, null, null);
    }

    @Override
    public PageBean getPackRecords(Long id, Integer pageNum, Integer pageSize) {
        int count = packRecordDao.count(id);

        int start = (pageNum-1) * pageSize;
        List<PackRecord> PackRecords = packRecordDao.query(id, start, pageSize);

        return new PageBean(count, PackRecords, Math.floorDiv(count, pageSize) + 1, pageNum);
    }
}
