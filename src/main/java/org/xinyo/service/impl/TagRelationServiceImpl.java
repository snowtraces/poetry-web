package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.TagRelationDao;
import org.xinyo.domain.TagRelation;
import org.xinyo.service.TagRelationService;

import java.util.List;

/**
 * Created by chengxinyong on 2018/4/28.
 */
@Service
public class TagRelationServiceImpl implements TagRelationService {
    @Autowired
    private TagRelationDao tagRelationDao;

    @Override
    public TagRelation selectByTag(TagRelation tagRelation) {
        return tagRelationDao.selectByTag(tagRelation);
    }

    @Override
    public int add(TagRelation tagRelation) {
        return tagRelationDao.insert(tagRelation);
    }

    @Override
    public int updateConnection(TagRelation tagRelation) {
        return tagRelationDao.updateConnection(tagRelation);
    }

    @Override
    public int batchInsert(List<TagRelation> list) {
        return tagRelationDao.batchInsert(list);
    }
}
