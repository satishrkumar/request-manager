package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class EmailJobService {
    public static final String FROM_EMAIL = "purviewemail@purviewservices.co.uk";
    public static final String CRON_SCHEDULE = "0 45 16 8 4 ? 2021";


    @Bean
    public JobDetail emailJobDetails() {
        return JobBuilder.newJob(EmailJob.class).withIdentity("EmailJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger triggerEmail(JobDetail emailJobDetails) {
        JobDataMap jobDataMap = new JobDataMap();
        Email email = new Email();
        email.setFrom(FROM_EMAIL);
        email.setTo("gaurav.udar@purviewservices.com");
        email.setName("Gaurav Udar");
        email.setSubject("Loan repayment details");
        email.setContent("Your loan repayment amount is due and you need to pay 50 GBP before due date");

        jobDataMap.put("emaildata", email);

        return TriggerBuilder.newTrigger().forJob(emailJobDetails)
                .withIdentity("TriggerEmail")
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_SCHEDULE))
                .usingJobData(jobDataMap)
                .build();
    }
}
