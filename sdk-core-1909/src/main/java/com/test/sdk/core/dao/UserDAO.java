package com.test.sdk.core.dao;

import com.test.sdk.common.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    Integer getUserIdByNum(String num);

    Integer getUserIdByName(String name);

    void addUser(User user);

    void addUserDynamic(Integer userId);

    void addUserNum(@Param("num") String num, @Param("userId") Integer userId);

    void addUserName(@Param("name") String name, @Param("userId") Integer userId);

    User getUserById(Integer id);
}
