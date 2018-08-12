package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.ShangXi;

import java.util.List;
import java.util.Map;

@Repository
public interface ShangXiDao {
    int insert(ShangXi shangXi);
}
