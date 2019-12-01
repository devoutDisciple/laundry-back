package com.laundry.moving.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private Boolean success;
    private Integer code;
    private Object data;

    public static BaseResponse success() {
        BaseResponse baseResponse = new BaseResponse(true, 200, "");
        return baseResponse;
    }

    public static BaseResponse success(Object data) {
        BaseResponse baseResponse = new BaseResponse(true, 200, data);
        return baseResponse;
    }

    public static BaseResponse error() {
        BaseResponse baseResponse = new BaseResponse(false, 500, "");
        return baseResponse;
    }

    public static BaseResponse error(Object data) {
        BaseResponse baseResponse = new BaseResponse(false, 500, data);
        return baseResponse;
    }
}
