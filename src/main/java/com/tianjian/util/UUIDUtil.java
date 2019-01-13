package com.tianjian.util;

/**
 * 生成带头的ID
 * Created by tianjian on 2018/12/31.
 */
public class UUIDUtil {
    public static String getPreUUID(String pre) {
        if (pre == null) {
            return java.util.UUID.randomUUID().toString();
        }
        return pre + ":" + java.util.UUID.randomUUID().toString();
    }
}
