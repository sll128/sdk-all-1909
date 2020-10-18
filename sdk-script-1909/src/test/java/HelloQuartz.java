import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author 徒有琴
 */
public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(data.get("banzhang"));
    }
}
