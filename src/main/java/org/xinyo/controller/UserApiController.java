package org.xinyo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xinyo.WebSecurityConfig;
import org.xinyo.domain.User;
import org.xinyo.service.UserService;
import org.xinyo.util.WebUtils;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/4/18.
 */
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/api/user/login"}, method = RequestMethod.POST)
    public Map<String, Object> userLogin(@RequestParam(name = "username", defaultValue = "") String username,
                                         @RequestParam(name = "email", defaultValue = "") String email,
                                         @RequestParam(name = "password", defaultValue = "") String password,
                                         HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();

        // 参数验证
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(email)) {
            return buildResult(resultMap, "global", "-1", "用户名和邮箱不能同时为空");
        }
        if (StringUtils.isEmpty(password)) {
            return buildResult(resultMap, "password", "-1", "不能为空");
        }

        // 验证账号
        try {
            password = WebUtils.getMD5(WebUtils.getMD5(password) + "@poetry!_*");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User userParam = new User();
        userParam.setUsername(StringUtils.isEmpty(username)?null:username);
        userParam.setPassword(StringUtils.isEmpty(password)?null:password);
        User user = userService.validateUser(userParam);

        if (user == null) {
            return buildResult(resultMap, "global", "-1", "账户或密码错误");
        }

        // 写入session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getUsername());

        return buildResult(resultMap, "global", "0", "登录成功");
    }

    @RequestMapping(value = {"/api/user/register"}, method = RequestMethod.POST)
    public Map<String, Object> userRegister(@RequestParam(name = "username", defaultValue = "") String username,
                                            @RequestParam(name = "email", defaultValue = "") String email,
                                            @RequestParam(name = "password", defaultValue = "") String password) {

        Map<String, Object> resultMap = new HashMap<>();

        // 参数验证
        resultMap = registerCheckParam(username, email, password, resultMap);
        if ("-1".equals(resultMap.get("code"))) {
            return resultMap;
        }

        // 写入结果
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setGroup(1);
        try {
            user.setPassword(WebUtils.getMD5(WebUtils.getMD5(password) + "@poetry!_*"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int n = userService.addUser(user);

        if (n == 1) {
            return buildResult(resultMap, "global", "0", "新建用户成功");
        } else {
            return buildResult(resultMap, "global", "-1", "新建用户失败");
        }
    }


    /**
     * 注册参数验证
     *
     * @param username
     * @param email
     * @param password
     * @param resultMap
     * @return
     */
    private Map<String, Object> registerCheckParam(String username, String email, String password, Map<String, Object> resultMap) {
        if (StringUtils.isEmpty(username)) {
            return buildResult(resultMap, "username", "-1", "不能为空");
        } else {
            if (username.length() < 2) {
                return buildResult(resultMap, "username", "-1", "用户名长度过短");
            }
            boolean isExist = userService.checkUsernameExist(username);
            if (isExist) {
                return buildResult(resultMap, "username", "-1", "用户名已存在");
            }
        }
        if (StringUtils.isEmpty(email)) {
            return buildResult(resultMap, "email", "-1", "不能为空");
        } else {
            boolean isExist = userService.checkEmailExist(email);
            if (isExist) {
                return buildResult(resultMap, "email", "-1", "邮箱已被注册");
            } else {
                boolean matches = email.matches("[\\w.-_]+@\\w+\\.\\w{2,5}");
                if (!matches) {
                    return buildResult(resultMap, "email", "-1", "邮箱地址不正确");
                }
            }
        }
        if (StringUtils.isEmpty(password)) {
            return buildResult(resultMap, "password", "-1", "不能为空");
        } else {
            if (password.length() < 5) {
                return buildResult(resultMap, "password", "-1", "密码长度不能小于5");
            }
        }
        return resultMap;
    }


    private Map<String, Object> buildResult(Map<String, Object> resultMap, String tag, String code, String msg) {
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("tag", tag);
        return resultMap;
    }
}
