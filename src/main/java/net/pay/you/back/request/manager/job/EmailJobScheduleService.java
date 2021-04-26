package net.pay.you.back.request.manager.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailJobScheduleService {
     public static final String CRON_SCHEDULE = "0 38 17 * * ?";
  //  public static final String CRON_SCHEDULE = "0 0/5 * * * ?";
    public static final String TRIGGER_EMAIL = "TriggerEmail";

    @Autowired
    private SchedulerFactoryBean customSchedulerFactoryBean;

    @PostConstruct
    private void initialize() throws Exception {
        customSchedulerFactoryBean.getScheduler().addJob(emailJobDetails(), true, true);
        if (!customSchedulerFactoryBean.getScheduler().checkExists(new TriggerKey(TRIGGER_EMAIL))) {
            customSchedulerFactoryBean.getScheduler().scheduleJob(triggerEmail(emailJobDetails()));
        }

    }

    @Bean
    public JobDetail emailJobDetails() {
        return JobBuilder.newJob(EmailJob.class).withIdentity("EmailJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger triggerEmail(JobDetail emailJobDetails) {
           return TriggerBuilder.newTrigger().forJob(emailJobDetails)
                .withIdentity(TRIGGER_EMAIL)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_SCHEDULE))
                .build();
    }


}
