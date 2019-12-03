package com.laundry.moving.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Util {
    public static String getUuid() {
        String str = UUID.randomUUID().toString();
        String uuid = str.replaceAll("-", "") + new Date().getTime();
        return uuid;
    }
}
