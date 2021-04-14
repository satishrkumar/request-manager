package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EmailJob implements Job {
    private static final Logger logger = LogManager.getLogger(EmailJob.class);

    public ApplicationContext getContext(JobExecutionContext context) throws Exception {
        ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        if(ctx == null) {
            throw new JobExecutionException("No application context available in scheduler context ");
        }
        return ctx;
    }

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("Inside Execution Of Email job!");
        try {
            ApplicationContext ctx = getContext(context);
            EmailService emailService =  ctx.getBean(EmailService.class);
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            List<Email> emails = (List<Email>) jobDataMap.get("emaildata");

            emails.stream()
                    .forEach(email -> emailService.sendEmail(email));
            context.getMergedJobDataMap().put("emaildata",Collections.EMPTY_LIST);
        } catch (Exception ex) {
           logger.error("Error ccured while executing and sending email job", ex);
        }

    }
}
