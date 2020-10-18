package com.test.sdk.common.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Data
public class User {
    private Integer id;
    private String num;
    private String name;
    private String password;
    private Integer channelId;
    private Date registDate;
    private Integer userType;
}
