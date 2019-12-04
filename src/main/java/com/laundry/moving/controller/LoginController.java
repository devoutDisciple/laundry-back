package com.laundry.moving.controller;

import com.laundry.moving.modal.vo.User;
import com.laundry.moving.service.LoginService;
import com.laundry.moving.util.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "登录相关接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public BaseResponse login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        return loginService.login(request, response, user);
    }

    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }

    @GetMapping("/zhangzhen")
    public BaseResponse test() {
        return BaseResponse.success("该账号已经通过拦截器，成功访问");
    }

    @GetMapping("/toLogin")
    public BaseResponse noLogin() {
        return BaseResponse.success("该账号没有登录");
    }

}
