package org.xinyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xinyo.domain.Poetry;
import org.xinyo.service.PoetryService;

import java.util.List;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@RestController
public class PoetryApiController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Poetry getPoetryById(@PathVariable Integer id){
        return poetryService.findPoetryById(id);
    }
}
