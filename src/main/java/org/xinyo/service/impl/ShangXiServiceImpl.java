package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.ShangXiDao;
import org.xinyo.domain.ShangXi;
import org.xinyo.service.ShangXiService;

@Service
public class ShangXiServiceImpl implements ShangXiService {
    @Autowired
    private ShangXiDao shangXiDao;


    @Override
    public int insert(ShangXi shangXi) {
        return shangXiDao.insert(shangXi);
    }

    @Override
    public ShangXi findByPoetryId(Integer id) {
        return shangXiDao.findByPoetryId(id);
    }
}
