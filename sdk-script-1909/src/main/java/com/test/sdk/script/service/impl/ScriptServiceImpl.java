package com.test.sdk.script.service.impl;

import com.test.sdk.script.dao.ScriptDAO;
import com.test.sdk.script.pojo.Scripts;
import com.test.sdk.script.service.ScriptService;
import com.test.sdk.script.util.QuartzManager;
import com.test.sdk.script.util.ScriptConstants;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author 徒有琴
 */
@Service
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    private ScriptDAO scriptDAO;

    @PostConstruct
    public void init() {
        List<Scripts> scripts = scriptDAO.getScriptsList(ScriptConstants.STATUS_RUNNING);
        for (Scripts script : scripts) {
            String name = script.getJobClass();
            try {
                Class jobClass = Class.forName(name);
                JobDataMap data=new JobDataMap();
                data.put("id",script.getId());
                QuartzManager.addJob(script.getCron(), jobClass, name, name, data);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Scripts> getScriptList(Integer status) {
        return scriptDAO.getScriptsList(status);
    }

    @Override
    public void updateScripts(Scripts scripts) {
        QuartzManager.modifyJobTime(scripts.getJobClass(),scripts.getCron());
        scriptDAO.updateScripts(scripts);
    }

    @Override
    public void deleteJob(Integer id) {
        Scripts scripts=new Scripts();
        scripts.setId(id);
        scripts.setStatus(ScriptConstants.STATUS_STOP);
        scriptDAO.updateScripts(scripts);
        scripts=scriptDAO.getScriptById(id);
        QuartzManager.removeJob(scripts.getJobClass(),scripts.getJobClass());
    }
}
