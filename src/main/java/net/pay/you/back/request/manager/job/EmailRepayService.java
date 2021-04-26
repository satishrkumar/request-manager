package net.pay.you.back.request.manager.job;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.domain.user.User;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailRepayService {
    public static final String FROM_EMAIL = "purviewemail@purviewservices.co.uk";
    @Autowired
    private LoanProcessingService loanProcessingService;

    @Autowired
    private UserService userService;

    public List<Email> getEmailsForRepayment() {
        List<Loan> loanDetails = loanProcessingService.findLoanDetailsByRepaymentDate();
        List<Email> senderEmails = new ArrayList<>();

        for (Loan loan : loanDetails) {

            Email email = new Email();
            String borrowerEmailId = loan.getBorrowerEmailId();
            User user = userService.findUserByEmailId(borrowerEmailId);

            email.setFrom(FROM_EMAIL);
            email.setTo(user.getEmailId());
            email.setName(user.getFirstName() + " " + user.getLastName());
            email.setSubject("Loan repayment details");
            email.setContent("Dear " + user.getFirstName() + " Your loan repayment amount is due and below are your loan details");
            senderEmails.add(email);
        }
        return senderEmails;
    }
}
