package com.test.sdk.script.pojo;

import com.sun.media.jfxmedia.events.PlayerTimeListener;
import lombok.Data;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Data
public class Scripts {
    private Integer id;
    private String name;
    private String cron;
    private String jobClass;
    private Date lastExecTime;
    private String result;
    private Integer status;
}
