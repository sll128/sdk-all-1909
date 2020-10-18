package com.test.sdk.script.job;

import com.test.sdk.common.logs.OnlineUserCount;
import com.test.sdk.script.dao.OnlineDAO;
import com.test.sdk.script.dao.OnlineUserCntDAO;
import com.test.sdk.script.dao.ScriptDAO;
import com.test.sdk.script.pojo.Scripts;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 徒有琴
 */
@Component
public class OnlineUserCntJob implements Job {
    private static OnlineDAO onlineDAO;
    private static OnlineUserCntDAO onlineUserCntDAO;
    private static ScriptDAO scriptDAO;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("OnlineUserCntJob");
        Scripts scripts = new Scripts();
        scripts.setLastExecTime(new Date());
        try {
            List<OnlineUserCount> counts = onlineDAO.getOnlineUserCount();
            Date time = new Date();
            for (OnlineUserCount count : counts) {
                count.setCreatedDate(time);
                onlineUserCntDAO.addOnlineUserCnt(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            scripts.setResult(e.getMessage());
        }
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        scripts.setId((Integer) data.get("id"));
        scriptDAO.updateScripts(scripts);
    }

    @Autowired
    public void setOnlineDAO(OnlineDAO onlineDAO, OnlineUserCntDAO onlineUserCntDAO, ScriptDAO scriptDAO) {
        OnlineUserCntJob.onlineDAO = onlineDAO;
        OnlineUserCntJob.onlineUserCntDAO = onlineUserCntDAO;
        OnlineUserCntJob.scriptDAO = scriptDAO;
    }
}
