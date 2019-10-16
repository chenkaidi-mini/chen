package com.gp4.utils;

import java.util.Base64;

/**
 * ckd 2019/9/10 16:41
 */
public class Base64Utils {
    //加密
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //解密
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }
}
