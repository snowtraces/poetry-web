package org.xinyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chengxinyong on 2018/4/18.
 */
@Controller
public class UserController {

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String userLogin(){
        return "login";
    }
}
