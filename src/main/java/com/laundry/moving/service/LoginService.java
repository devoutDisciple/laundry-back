package com.laundry.moving.service;

import com.laundry.moving.modal.entity.UserEntity;
import com.laundry.moving.modal.repo.HelloEntityRepo;
import com.laundry.moving.modal.repo.UserEntityRepo;
import com.laundry.moving.modal.vo.Test;
import com.laundry.moving.modal.vo.User;
import com.laundry.moving.util.BaseResponse;
import com.laundry.moving.util.ModalMapping;
import com.laundry.moving.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserEntityRepo userEntityRepo;

    @Autowired
    HelloEntityRepo helloEntityRepo;

    public BaseResponse login(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            System.out.println(user);
            UserEntity userEntity = userEntityRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(userEntity == null) {
               return BaseResponse.error("用户名或账号密码错误");
            }
            String uuid = Util.getUuid();
            userEntity.setToken(uuid);
            userEntityRepo.saveAndFlush(userEntity);
            Cookie cookie = new Cookie("token", uuid);
            cookie.setHttpOnly(true);
//                单位是秒
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
            return BaseResponse.success("登录成功");
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.error("网络异常，请稍后重试");
        }
    }


    public BaseResponse logout(HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return BaseResponse.success("退出成功");
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.error("网络异常，请稍后重试");
        }
    }

    public BaseResponse testAndQuery() {
        try {
            List<Test> list = ModalMapping.mapping(helloEntityRepo.queryHelloAndUser(1), Test.class, new Test());
            return BaseResponse.success(list);
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.error("网络异常，请稍后重试");
        }
    }
}
