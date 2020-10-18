package com.test.sdk.script.dao;

import com.test.sdk.common.logs.OnlineUserCount;

import java.util.Date;
import java.util.List;

public interface OnlineDAO {
    void deleteTimeoutUser(Date date);

    List<OnlineUserCount> getOnlineUserCount();
}
