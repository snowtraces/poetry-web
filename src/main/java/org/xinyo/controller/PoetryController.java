package org.xinyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xinyo.domain.Poetry;
import org.xinyo.service.PoetryService;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@RestController
public class PoetryController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/api/poetry", method = RequestMethod.GET)
    public Poetry findOnePoetry(@RequestParam Integer poetryId){
        return  poetryService.findPoetryById(poetryId);
    }
}
