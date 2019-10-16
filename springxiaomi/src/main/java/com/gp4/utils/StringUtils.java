package com.gp4.utils;

/**
 * ckd 2019/9/10 16:16
 */
public class StringUtils {
    public static boolean isEmpty(String s){
        if (s==null||s.trim().length()==0){
            return true;
        }
        return false;
    }
}
