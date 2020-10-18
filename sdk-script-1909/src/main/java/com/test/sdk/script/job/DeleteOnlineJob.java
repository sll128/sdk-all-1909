package com.test.sdk.script.job;

import com.test.sdk.script.dao.OnlineDAO;
import com.test.sdk.script.dao.ScriptDAO;
import com.test.sdk.script.pojo.Scripts;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 徒有琴
 */
@Component
public class DeleteOnlineJob implements Job {
    private static OnlineDAO onlineDAO;
    private static ScriptDAO scriptDAO;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Scripts scripts = new Scripts();
        scripts.setLastExecTime(new Date());
        try {
            //20分钟之前的
            onlineDAO.deleteTimeoutUser(new Date(System.currentTimeMillis() - 20 * 60 * 1000));
            scripts.setResult("成功");
        } catch (Exception e) {
            e.printStackTrace();
            scripts.setResult(e.getMessage());
        }
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        scripts.setId((Integer) data.get("id"));
        scriptDAO.updateScripts(scripts);
    }

    @Autowired
    public void setOnlineDAO(OnlineDAO onlineDAO) {
        DeleteOnlineJob.onlineDAO = onlineDAO;
    }

    @Autowired
    public void setScriptDAO(ScriptDAO scriptDAO) {
        DeleteOnlineJob.scriptDAO = scriptDAO;
    }
}
