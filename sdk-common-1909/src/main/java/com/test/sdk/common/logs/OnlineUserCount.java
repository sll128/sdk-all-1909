package com.test.sdk.common.logs;

import lombok.Data;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Data
public class OnlineUserCount {
    private Integer id;
    private Integer cnt;
    private Integer gameId;
    private Date createdDate;
}
