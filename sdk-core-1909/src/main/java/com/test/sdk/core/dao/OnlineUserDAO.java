package com.test.sdk.core.dao;

import com.test.sdk.common.pojo.OnlineUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OnlineUserDAO {

    void addOnlineUser(OnlineUser user);

    void updateLastAct(@Param("ticket") String ticket, @Param("time") Date time);

    void deleteOnlineUser(Integer userId);
}
