package com.test.sdk.core.util;

public interface SdkConstants {
    int VER_CODE_TIMEOUT = 60 * 5;//验证过期时间，5分钟
    int VER_CODE_LENGTH = 6;//手机验证码长度
    int ACCOUNT_TYPE_MOBILE = 1;
    int ACCOUNT_TYPE_USERNAME = 2;
    int ACCOUNT_TYPE_UNKNOWN = 0;
    int USER_TYPE_BIND = 1;
    int USER_TYPE_VISITOR = 0;
    String PASSWORD_SALT = "helloworld@1909";
    int USER_SESSION_TIMEOUT = 20 * 60 ;//用户登陆过期时间
}
