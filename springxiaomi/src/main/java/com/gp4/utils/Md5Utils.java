package com.gp4.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * ckd 2019/9/10 16:28
 */
public class Md5Utils {
    public static String md5(String s){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(s.getBytes());
            byte[] digest = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, digest);
            String secret = bigInteger.toString(16);
            return secret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
