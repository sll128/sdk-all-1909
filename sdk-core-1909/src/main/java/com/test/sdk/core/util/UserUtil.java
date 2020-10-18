package com.test.sdk.core.util;

import java.util.Random;

/**
 * @author 徒有琴
 */
public class UserUtil {

    public static String randomNum(int len) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public static boolean checkMobile(String phone) {
        if (phone == null || phone.trim().length() != 11) {
            return false;
        }
        String reg = "^1[3-9]\\d{9}$";
        return phone.matches(reg);
    }

    public static boolean checkUsername(String name) {
        if (name == null || name.trim().length() == 0) {
            return false;
        }
        String reg = "^[a-zA-Z]\\w{5,31}$";
        return name.matches(reg);
    }

    public static int getAccounType(String account) {
        if (checkMobile(account)) {
            return SdkConstants.ACCOUNT_TYPE_MOBILE;
        }
        if (checkUsername(account)) {
            return SdkConstants.ACCOUNT_TYPE_USERNAME;
        }
        return SdkConstants.ACCOUNT_TYPE_UNKNOWN;
    }

    public static boolean checkPassword(String password) {
        if (password == null || password.trim().length() == 0 || password.trim().length() > 24) {
            return false;
        }
        String reg = "^[a-zA-Z\\d]{6,24}$";
        return password.matches(reg);
    }

    final static String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomName(int len) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

}
