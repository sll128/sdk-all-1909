package com.test.sdk.core.service;

import com.test.sdk.common.pojo.User;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.exception.SdkException;

public interface UserService {
    ResponseTO doSendCode(String mobile) throws SdkException;

    boolean doCheckCode(String mobile, String code) throws SdkException;

    boolean doNumRegist(String mobile, Integer channelId) throws SdkException;

    boolean doNameRegist(String name, String password, Integer channelId) throws SdkException;

    User doVisitorRegist(Integer channelId) throws SdkException;

    ResponseTO doNameLogin(String account, String password,Integer gameId) throws SdkException;

    void updateLastAct(String ticket);
}
