import com.test.sdk.script.util.QuartzManager;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author 徒有琴
 */
public class QuartzTest {
    public static void main(String[] args) throws Exception {
//        Class clazz=Class.forName("ClassA");
//        ClassA a2=(ClassA)clazz.newInstance();
//        System.out.println(a2.getClassB());
//        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring.xml");
//        ClassA a=applicationContext.getBean(ClassA.class);
//        System.out.println(a.getClassB());
//        ClassA a3=new ClassA();
//        System.out.println(a3.getClassB());
//        System.out.println("准备添加任务");
//        Thread.sleep(2000);
        JobDataMap data = new JobDataMap();
        data.put("banzhang", "hehehe");
        QuartzManager.addJob("0 */5 * * * ?", HelloQuartz.class, "tr", "j1", data);
//        System.out.println("添加成功");
//        Thread.sleep(15000);
//        System.out.println("修改时间");
//        QuartzManager.modifyJobTime("tr", "*/1 * 11 * * ?");
//        Thread.sleep(10000);
//        System.out.println("删除任务");
//        QuartzManager.removeJob("j1", "tr");
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        //定义什么时间做
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("t1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * 11 * * ?")).build();
//             //   .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
//        //做什么
//        JobDetail job = JobBuilder.newJob(HelloQuartz.class)
//                .withIdentity("job1").usingJobData("banzhang","jiazezhong").build();
//        scheduler.scheduleJob(job, trigger);//绑定什么时间做什么
//        scheduler.start();
    }
}
