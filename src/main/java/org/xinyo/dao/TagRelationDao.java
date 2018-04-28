package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.TagRelation;
import org.xinyo.domain.User;

import java.util.List;

/**
 * Created by chengxinyong on 2018/4/18.
 */
@Repository
public interface TagRelationDao {

    TagRelation selectByTag(TagRelation tagRelation);

    int insert(TagRelation tagRelation);

    int updateConnection(TagRelation tagRelation);

    int batchInsert(List<TagRelation> list);
}
