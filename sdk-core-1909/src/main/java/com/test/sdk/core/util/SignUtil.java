package com.test.sdk.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author 徒有琴
 */
public class SignUtil {
    public static String getSignString(Map<String, String[]> params) {
        Set<String> keySet = params.keySet();
        List<String> keys = new ArrayList<>(keySet);
        Collections.sort(keys);
        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            if ("sign".equals(key)) {//sign不参与md5，sign是md5后的那个结果
                continue;
            }
            builder.append(key).append("=");
            String[] value = params.get(key);
            if (value != null && value.length > 0) {
                builder.append(value[0]);
            }
            builder.append("&");
        }
        return builder.toString();
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
