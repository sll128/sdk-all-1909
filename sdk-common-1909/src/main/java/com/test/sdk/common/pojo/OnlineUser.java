package com.test.sdk.common.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Data
public class OnlineUser {
    private String ticket;
    private Integer userId;
    private String loginAccount;
    private Date loginDate;
    private Date lastAct;
    private Integer gameId;
}
