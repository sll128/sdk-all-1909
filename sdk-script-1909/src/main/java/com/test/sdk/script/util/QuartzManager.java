package com.test.sdk.script.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

/**
 * @author 徒有琴
 */
public class QuartzManager {
    private static Scheduler scheduler = null;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void addJob(String cron, Class jobClass, String triggerName, String jobName, JobDataMap data) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        //做什么
        JobBuilder builder = JobBuilder.newJob(jobClass)
                .withIdentity(jobName);
        if (data != null) {
            builder.usingJobData(data);
        }
        JobDetail job = builder.build();
        try {
            scheduler.scheduleJob(job, trigger);//绑定什么时间做什么
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jobName
     * @param triggerName
     * @Description: 移除一个任务
     */
    public static void removeJob(String jobName,
                                 String triggerName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyJobTime(
            String triggerName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            //表达式不一样再修改，否则不用修改
            if (!trigger.getCronExpression().equals(cron)) {
                //使用新的表达式，创建一个新的触发器
                Trigger newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerName)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
                scheduler.rescheduleJob(triggerKey, newTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
