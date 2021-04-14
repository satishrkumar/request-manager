package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.domain.user.User;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.facade.UserService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailJobScheduleService {
    public static final String FROM_EMAIL = "purviewemail@purviewservices.co.uk";
   // public static final String CRON_SCHEDULE = "0 38 17 * * ?";
   public static final String CRON_SCHEDULE = "0 38 17 * * ?";
    public static final String TRIGGER_EMAIL = "TriggerEmail";

    @Autowired
    private LoanProcessingService loanProcessingService;

    @Autowired
    private UserService userService;

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
        JobDataMap jobDataMap = new JobDataMap();
        List<Loan> loanDetails = loanProcessingService.findLoanDetailsByRepaymentDate();
        List<Email> senderEmails = new ArrayList<>();

        for (Loan loan : loanDetails) {

            Email email = new Email();
            String borrowerEmailId = loan.getBorrowerEmailId();
            User user = userService.findUserByEmailId(borrowerEmailId);

            email.setFrom(FROM_EMAIL);
            email.setTo(user.getEmailId());
            email.setName(user.getFirstName()+" "+user.getLastName());
            email.setSubject("Loan repayment details");
            email.setContent("Dear " + user.getFirstName() + " Your loan repayment amount is due and below are your loan details");
            senderEmails.add(email);
        }


        jobDataMap.put("emaildata", senderEmails);
        return TriggerBuilder.newTrigger().forJob(emailJobDetails)
                .withIdentity(TRIGGER_EMAIL)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_SCHEDULE))
                .usingJobData(jobDataMap)
                .build();
    }
}
