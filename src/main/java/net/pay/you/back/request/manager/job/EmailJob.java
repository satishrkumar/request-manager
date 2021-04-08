package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailJob implements Job {
    private static final Logger logger = LogManager.getLogger(EmailJob.class);

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext context)  {
        logger.info("Inside Execution Of Email job!");
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        Email email = (Email) jobDataMap.get("emaildata");
        emailService.sendEmail(email);
    }
}
