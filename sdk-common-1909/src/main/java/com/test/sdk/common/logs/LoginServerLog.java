package com.test.sdk.common.logs;

import lombok.Data;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Data
public class LoginServerLog {
    private Integer id;

    private Integer userId;// 用户Id

    private Integer serverId;// 服务器ID

    private Integer gameId;// 游戏ID

    private Integer promotionChannelId;// 推广渠道ID

    private String macAddress;// 设备地址

    private String imei;// 手机编码

    private String ip;

    private String ua;// 机型

    private Date createdDate;// 创建时间

    private Long roleId;
}
