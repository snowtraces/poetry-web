package org.xinyo.service;

import org.xinyo.domain.ShangXi;

public interface ShangXiService {
    int insert(ShangXi shangXi);

    ShangXi findByPoetryId(Integer id);
}
