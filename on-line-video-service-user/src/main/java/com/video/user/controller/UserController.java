package com.video.user.controller;

import com.video.user.bean.vo.UserTokenInfoVo;
import com.video.user.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: master
 * @date: 2018/8/8
 */
@RestController
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private UserTokenService userTokenService;

    @PostMapping(value = "add")
    public void addUserToken(@RequestBody @Validated UserTokenInfoVo userTokenInfoVo) throws Exception {
        userTokenService.saveUserToken(userTokenInfoVo);
    }
}
