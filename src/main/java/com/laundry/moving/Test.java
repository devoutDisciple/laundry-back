package com.laundry.moving;

import com.laundry.moving.modal.entity.UserEntity;
import com.laundry.moving.modal.repo.UserEntityRepo;
import com.laundry.moving.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {

    @Autowired
    UserEntityRepo userEntityRepo;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public BaseResponse hello() {
        List<UserEntity> all = userEntityRepo.findAll();
        System.out.println(all);
        for (UserEntity item : all) {
            System.out.println(item);
        }
        BaseResponse baseResponse = BaseResponse.success(all);
        return baseResponse;
    }

    @RequestMapping(value = "world")
    public BaseResponse world() {
        BaseResponse baseResponse = BaseResponse.success();
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setPhone("234324");
        userEntityRepo.save(userEntity);
        return baseResponse;
    }

    @RequestMapping(value = "delete")
    public BaseResponse delete() {
        BaseResponse baseResponse = BaseResponse.success();
        userEntityRepo.deleteById(2);
        return baseResponse;
    }


    @RequestMapping(value = "update")
    public BaseResponse update() {
        BaseResponse baseResponse = BaseResponse.success();
        UserEntity userEntity = userEntityRepo.findById(1).orElse(null);
        System.out.println(userEntity);
        userEntity.setName("zhangzhen");
        userEntityRepo.saveAndFlush(userEntity);
        return baseResponse;
    }
}
