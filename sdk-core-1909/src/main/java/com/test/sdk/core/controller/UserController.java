package com.test.sdk.core.controller;

import com.test.sdk.common.pojo.Game;
import com.test.sdk.common.pojo.User;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.cache.CPCache;
import com.test.sdk.core.cache.GameCache;
import com.test.sdk.core.exception.SdkException;
import com.test.sdk.core.service.RedisService;
import com.test.sdk.core.service.UserService;
import com.test.sdk.core.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徒有琴
 */
@RestController
public class UserController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @RequestMapping("/user/reg/sendcode.html")
    public ResponseTO sendCode(String account) throws SdkException {
        if (!UserUtil.checkMobile(account)) {
            throw new SdkException(ErrorConstants.INVALID_MOBILE_INPUT);
        }
        return userService.doSendCode(account);
    }

    @RequestMapping("/user/register.html")
    public ResponseTO regist(String account, String code, Integer prtchid, String password, String repassword, Integer cpid, Integer type) throws SdkException {
        if (type == SdkConstants.USER_TYPE_BIND) {
            int accountType = UserUtil.getAccounType(account);
            switch (accountType) {
                case SdkConstants.ACCOUNT_TYPE_MOBILE:
                    if (StringUtils.isEmpty(code)) {//没有输入验证码
                        throw new SdkException(ErrorConstants.PARAM_ERROR);
                    }
                    if (userService.doCheckCode(account, code)) {//校验验证码
                        boolean result = userService.doNumRegist(account, prtchid);
                        return ResponseTO.ok(result);
                    } else {
                        throw new SdkException(ErrorConstants.PARAM_ERROR);//验证码错误
                    }
                case SdkConstants.ACCOUNT_TYPE_USERNAME:
                    if (StringUtils.isEmpty(password)) {//没有输入密码
                        throw new SdkException(ErrorConstants.NULL_PASSWORD_INPUT);
                    }
                    if (StringUtils.isEmpty(repassword)) {//没有输入确认密码
                        throw new SdkException(ErrorConstants.NULL_REPASSWORD_INPUT);
                    }
                    if (!password.equals(repassword)) {//两次输入密码不一致
                        throw new SdkException(ErrorConstants.PASSWORD_REPASSWORD_UNEQUAL);
                    }
                    //客户端传递过来的密码是DES加密过的密文，我们要验证密码格式（数字、字母、长度等），需要对密文进行解密
                    String secretKey = CPCache.getSecretKey(cpid);
                    try {
                        System.out.println(password);
                        password = DES.decode(password, Base64.encode(secretKey.getBytes()));
                        System.out.println(password);
                    } catch (Exception e) {//无法解密代表密文被篡改或者密文加密不对
                        e.printStackTrace();
                        throw new SdkException(ErrorConstants.INVALID_PASSWORD_INPUT);
                    }
                    if (!UserUtil.checkPassword(password)) {
                        throw new SdkException(ErrorConstants.INVALID_PASSWORD_INPUT);
                    }
                    boolean result = userService.doNameRegist(account, password, prtchid);
                    return ResponseTO.ok(result);
                case SdkConstants.ACCOUNT_TYPE_UNKNOWN://账号不合法
                    throw new SdkException(ErrorConstants.INVALID_ACCOUNT_INPUT);
            }
        } else {//游客
            User user = userService.doVisitorRegist(prtchid);
            return ResponseTO.ok(user);
        }
        return null;
    }

    @RequestMapping("/user/login.html")
    public ResponseTO login(String account, String password, Integer cpid,Integer seqnum) throws SdkException {
        int accountType = UserUtil.getAccounType(account);
        switch (accountType) {
            case SdkConstants.ACCOUNT_TYPE_MOBILE:
                //略
                break;
            case SdkConstants.ACCOUNT_TYPE_USERNAME:
                //客户端传递过来的密码是DES加密过的密文，我们要验证密码格式（数字、字母、长度等），需要对密文进行解密
                String secretKey = CPCache.getSecretKey(cpid);
                try {
                    password = DES.decode(password, Base64.encode(secretKey.getBytes()));
                } catch (Exception e) {//无法解密代表密文被篡改或者密文加密不对
                    e.printStackTrace();
                    throw new SdkException(ErrorConstants.INVALID_PASSWORD_INPUT);
                }
                if (!UserUtil.checkPassword(password)) {
                    throw new SdkException(ErrorConstants.INVALID_PASSWORD_INPUT);
                }
                Game game= GameCache.getGameByCpidAndSeqnum(cpid,seqnum);
                return userService.doNameLogin(account, password,game.getId());
        }
        return ResponseTO.error(null);
    }

    @RequestMapping("/user/service/heartbeat.html")
    public ResponseTO heartbeat(String ticket) {
        ticket = TicketUtil.decode(ticket);
        redisService.expire(ticket, SdkConstants.USER_SESSION_TIMEOUT);
        userService.updateLastAct(ticket);
        ResponseTO response = ResponseTO.ok(null);
        response.setTicket(TicketUtil.encode(ticket));//刷新时间戳
        return response;
    }
}
