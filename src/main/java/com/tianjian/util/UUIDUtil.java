package com.tianjian.util;
/**
 * Created by tianjian on 2018/12/31.
 */
public class UUIDUtil {
    public static String getPreUUID(String pre) throws Exception {
        if(pre == null) {
            throw new Exception("pre is not null");
        }
        return pre + ":" + java.util.UUID.randomUUID().toString();
    }

}
