package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.AuthorDao;
import org.xinyo.domain.Author;
import org.xinyo.service.AuthorService;

import java.util.Map;

/**
 * Created by CHENG on 2018/3/31.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author findByIdAndLanguage(Map<String, Object> params) {
        return authorDao.findByIdAndLanguage(params);
    }
}
