package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.Loan;
import net.pay.you.back.request.manager.domain.User;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.facade.UserService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailJobService {
    public static final String FROM_EMAIL = "purviewemail@purviewservices.co.uk";
    public static final String CRON_SCHEDULE = "0 15 08 * * ? *";

    @Autowired
    private LoanProcessingService loanProcessingService;

    @Autowired
    private UserService userService;



    @Bean
    public JobDetail emailJobDetails() {
        return JobBuilder.newJob(EmailJob.class).withIdentity("EmailJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger triggerEmail(JobDetail emailJobDetails) {
        JobDataMap jobDataMap = new JobDataMap();
        List<Loan> loanDetails = loanProcessingService.findLoanDetailsByRepaymentDate();

        for (Loan loan : loanDetails) {

            Email email = new Email();
            List<Email> senderEmails = new ArrayList<>();
            String borrowerEmailId = loan.getBorrowerEmailId();
            User user = userService.findUserByEmailId(borrowerEmailId);

            email.setFrom(FROM_EMAIL);
            email.setTo(user.getEmailId());
            email.setName(user.getFirstName()+" "+user.getLastName());
            email.setSubject("Loan repayment details");
            email.setContent("Dear " + user.getFirstName() + " Your loan repayment amount is due and below are your loan details");
            senderEmails.add(email);

            jobDataMap.put("emaildata", senderEmails);
        }

        return TriggerBuilder.newTrigger().forJob(emailJobDetails)
                .withIdentity("TriggerEmail")
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_SCHEDULE))
                .usingJobData(jobDataMap)
                .build();
    }
}
