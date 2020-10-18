package com.test.sdk.core.util;

import com.test.sdk.common.util.ErrorCode;
import lombok.Data;

/**
 * @author 徒有琴
 */
public interface ErrorConstants {
    ErrorCode SYSTEM_UNKNOWN=new ErrorCode("0000","UNKNOWN");
    // 未知错误
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    // 游戏不存在
    String GAME_NOT_EXIST = "GAME_NOT_EXIST";
    // 游戏服务器不存在
    String SERVER_NOT_EXIST = "SERVER_NOT_EXIST";
    // cp不存在
    String CP_NOT_EXIST = "CP_NOT_EXIST";
    // 推广渠道不存在
    String CHANNEL_NOT_EXIST = "CHANNEL_NOT_EXIST";
    // 账号输入不合法
    String INVALID_ACCOUNT_INPUT = "INVALID_ACCOUNT_INPUT";
    // 手机号输入不合法
    String INVALID_MOBILE_INPUT = "INVALID_MOBILE_INPUT";
    // 密码输入不合法
    String INVALID_PASSWORD_INPUT = "INVALID_PASSWORD_INPUT";
    // 账号不能为空
    String NULL_ACCOUNT_INPUT = "NULL_ACCOUNT_INPUT";
    // 密码不能为空
    String NULL_PASSWORD_INPUT = "NULL_PASSWORD_INPUT";
    // 确认密码不能为空
    String NULL_REPASSWORD_INPUT = "NULL_REPASSWORD_INPUT";
    // 两次输入密码不一致
    String PASSWORD_REPASSWORD_UNEQUAL = "PASSWORD_REPASSWORD_UNEQUAL";
    // 账号不存在
    String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
    // 账号已存在
    String ACCOUNT_ALREADY_EXIST = "ACCOUNT_ALREADY_EXIST";
    // 用户名密码错误
    String ACCOUNT_PASSWORD_ERROR = "ACCOUNT_PASSWORD_ERROR";
    // 用户名已存在
    String USERNAME_ALREADY_EXIST = "USERNAME_ALREADY_EXIST";
    // 用户名不存在
    String USERNAME_NOT_EXIST = "USERNAME_NOT_EXIST";
    // 该手机号已存在
    String PHONE_ALREADY_EXIST = "PHONE_ALREADY_EXIST";
    // 该手机号不存在
    String PHONE_NOT_EXIST = "PHONE_NOT_EXIST";
    // 没有签名参数
    String NO_SIGN_ERROR = "NO_SIGN_ERROR";
    // 您还未登录，请先登录！
    String NEED_LOGIN = "NEED_LOGIN";
    // 您的请求已过期，请重新操作！
    String SESSION_TIME_OUT = "SESSION_TIME_OUT";
    // 签名错误
    String SIGN_ERROR = "SIGN_ERROR";
    // 参数错误
    String PARAM_ERROR = "PARAM_ERROR";
    // 数据库异常
    String DATABASE_ERROR = "DATABASE_ERROR";
    // 支付请求错误
    String PAY_ERROR = "PAY_ERROR";
    // 充值金额不正确！
    String AMOUNT_ERROR = "AMOUNT_ERROR";
    // 跳转至登录操作失败，请重新操作
    String GO_LOGIN_ERROR = "GO_LOGIN_ERROR";
    // 您不是游客
    String ACCOUNT_NOT_VISITOR = "ACCOUNT_NOT_VISITOR";
    // 渠道禁止充值
    String CHANNEL_PAY_PROHIBIT = "CHANNEL_PAY_PROHIBIT";
    // 游客注册失败
    String VISITOR_REGIST_FAILED = "VISITOR_REGIST_FAILED";
}
