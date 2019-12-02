package com.laundry.moving;

import com.laundry.moving.modal.entity.UserEntity;
import com.laundry.moving.modal.repo.UserEntityRepo;
import com.laundry.moving.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@Api(tags = "测试相关内容")
public class Test {

    @Autowired
    UserEntityRepo userEntityRepo;

    @ApiOperation("查找用户")
    @GetMapping(value = "hello")
    public BaseResponse hello() {
        List<UserEntity> all = userEntityRepo.findAll();
        System.out.println(all);
        for (UserEntity item : all) {
            System.out.println(item);
        }
        BaseResponse baseResponse = BaseResponse.success(all);
        return baseResponse;
    }

    @ApiOperation("添加用户")
    @GetMapping(value = "world")
    public BaseResponse world() {
        BaseResponse baseResponse = BaseResponse.success();
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setPhone("234324");
        userEntityRepo.save(userEntity);
        return baseResponse;
    }

    @GetMapping(value = "delete")
    public BaseResponse delete() {
        BaseResponse baseResponse = BaseResponse.success();
        userEntityRepo.deleteById(2);
        return baseResponse;
    }


    @GetMapping(value = "update")
    public BaseResponse update() {
        BaseResponse baseResponse = BaseResponse.success();
        UserEntity userEntity = userEntityRepo.findById(1).orElse(null);
        System.out.println(userEntity);
        userEntity.setName("zhangzhen");
        userEntityRepo.saveAndFlush(userEntity);
        return baseResponse;
    }
}
