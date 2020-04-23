package com.eroad.project.web;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.service.OAuthUserService;

/**
* Created by cyt on 2018/10/10.
*/
@RestController
@RequestMapping("/oauth/user")
public class OAuthUserController {
    @Resource
    private OAuthUserService oAuthUserService;

    @PostMapping("/add")
    public Result add(String userName, String passWord) {
        oAuthUserService.addOAuthClient(userName, passWord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String userName) {
        oAuthUserService.delOAuthClient(userName);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String userName, String passWord) {
        oAuthUserService.updateOAuthClient(userName, passWord);
        return ResultGenerator.genSuccessResult();
    }
}
