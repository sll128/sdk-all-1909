package com.test.sdk.core.service.impl;

import com.test.sdk.common.logs.LoginServerLog;
import com.test.sdk.common.pojo.OnlineUser;
import com.test.sdk.common.pojo.User;
import com.test.sdk.common.util.JsonUtil;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.dao.OnlineUserDAO;
import com.test.sdk.core.dao.UserDAO;
import com.test.sdk.core.exception.SdkException;
import com.test.sdk.core.mqsender.Sender;
import com.test.sdk.core.service.RedisService;
import com.test.sdk.core.service.UserService;
import com.test.sdk.core.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

/**
 * @author 徒有琴
 */
@Service
public class UserServiceImpl implements UserService, SdkConstants {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseTO doSendCode(String mobile) throws SdkException {
        //验证手机号是否被注册
        Integer userId = userDAO.getUserIdByNum(mobile);
        if (userId != null) {//手机号已经注册
            throw new SdkException(ErrorConstants.PHONE_ALREADY_EXIST);
        }
        String code = UserUtil.randomNum(VER_CODE_LENGTH);
        System.out.println(code);
        //调用短信接口
//        String url = "http://v.juhe.cn/sms/send?key=af51c5aa1&mobile=" + mobile + "&tpl_id=201941&tpl_value=%23code%23%3D" + code;
//        HashMap result = restTemplate.getForObject(url, HashMap.class);
//        System.out.println(result);
//        if ("0".equals(String.valueOf(result.get("error_code")))) {
        return redisService.setex(mobile, code, VER_CODE_TIMEOUT);
//        } else {
//            return ResponseTO.error("发送失败");
//        }

    }

    @Override
    public boolean doCheckCode(String mobile, String code) throws SdkException {
        ResponseTO redis = redisService.get(mobile);
        if (redis == null || redis.getBusinessResult() == null) {
            return false;
        }
        if (code.equals(redis.getBusinessResult().toString())) {
            redisService.del(mobile);//使用过的验证码失效
            return true;
        }
        return false;
    }

    @Override
    public boolean doNumRegist(String mobile, Integer channelId) throws SdkException {
        Integer userId = userDAO.getUserIdByNum(mobile);
        if (userId != null) {
            throw new SdkException(ErrorConstants.PHONE_ALREADY_EXIST);
        }
        User user = new User();
        user.setRegistDate(new Date());
        user.setNum(mobile);
        user.setChannelId(channelId);
        user.setUserType(SdkConstants.USER_TYPE_BIND);
        user.setPassword(SignUtil.getMD5(System.currentTimeMillis() + ""));
        userDAO.addUser(user);
        userDAO.addUserDynamic(user.getId());
        userDAO.addUserNum(mobile, user.getId());
        return true;
    }

    @Override
    public boolean doNameRegist(String name, String password, Integer channelId) throws SdkException {
        Integer userId = userDAO.getUserIdByName(name);
        if (userId != null) {
            throw new SdkException(ErrorConstants.ACCOUNT_ALREADY_EXIST);
        }
        User user = new User();
        user.setRegistDate(new Date());
        user.setName(name);
        user.setChannelId(channelId);
        user.setUserType(SdkConstants.USER_TYPE_BIND);
        user.setPassword(SignUtil.getMD5(password + SdkConstants.PASSWORD_SALT));
        userDAO.addUser(user);
        userDAO.addUserDynamic(user.getId());
        userDAO.addUserName(name, user.getId());
        return true;
    }

    @Override
    public User doVisitorRegist(Integer channelId) throws SdkException {
        String name = null;
        Integer userId = null;
        do {
            name = UserUtil.randomName(10);
            userId = userDAO.getUserIdByName(name);
        } while (userId != null);
        String password = UserUtil.randomName(6);
        User user = new User();
        user.setRegistDate(new Date());
        user.setName(name);
        user.setChannelId(channelId);
        user.setUserType(SdkConstants.USER_TYPE_VISITOR);
        user.setPassword(SignUtil.getMD5(password + SdkConstants.PASSWORD_SALT));
        userDAO.addUser(user);
        userDAO.addUserDynamic(user.getId());
        userDAO.addUserName(name, user.getId());
        user.setPassword(password);
        return user;
    }

    @Autowired
    private OnlineUserDAO onlineUserDAO;
    @Autowired
    private Sender sender;

    @Override
    public ResponseTO doNameLogin(String account, String password, Integer gameId) throws SdkException {
        Integer userId = userDAO.getUserIdByName(account);
        if (userId == null) {
            throw new SdkException(ErrorConstants.ACCOUNT_PASSWORD_ERROR);
        }
        User user = userDAO.getUserById(userId);
        if (!SignUtil.getMD5(password + SdkConstants.PASSWORD_SALT).equals(user.getPassword())) {
            throw new SdkException(ErrorConstants.ACCOUNT_PASSWORD_ERROR);
        }
        String ticket = SignUtil.getMD5(System.currentTimeMillis() + user.getPassword() + userId);
        ResponseTO response = ResponseTO.ok(user.getUserType());
        redisService.setex(ticket, userId.toString(), SdkConstants.USER_SESSION_TIMEOUT);
        onlineUserDAO.deleteOnlineUser(userId);//原来票据失效
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setLoginDate(new Date());
        onlineUser.setLoginAccount(account);
        onlineUser.setTicket(ticket);
        onlineUser.setLastAct(new Date());
        onlineUser.setUserId(userId);
        onlineUser.setGameId(gameId);
        onlineUserDAO.addOnlineUser(onlineUser);
        response.setTicket(TicketUtil.encode(ticket));//返回给前端的时候，带时间戳
        LoginServerLog log=new LoginServerLog();
        log.setGameId(gameId);
        log.setUserId(userId);
        log.setCreatedDate(new Date());
        sender.send(JsonUtil.getJSON(log));
        return response;
    }

    @Override
    public void updateLastAct(String ticket) {
        onlineUserDAO.updateLastAct(ticket, new Date());
    }
}
