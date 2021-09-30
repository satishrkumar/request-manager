package net.pay.you.back.request.manager.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pay.you.back.request.manager.comm.BaseLoan;
import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.EmailModel;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.facade.impl.LoanProcessingServiceImpl;
import net.pay.you.back.request.manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoanService {
    @Autowired
    private LoanProcessingServiceImpl loanProcessingService;

    @Autowired
    EmailService emailService;

    public State requestLoan(LoanRequest loanRequest) {
        Loan loan = Loan.convertFromBaseLoanRequest(loanRequest);
        loan.setLoanState(State.PENDING);
        loanProcessingService.createLoan(loan);
        EmailModel emailModel = generateModel(loanRequest, loanRequest.getReasonForBorrow());

        emailModel.setName(loanRequest.getBorrower().getFirstName() !=null ? loanRequest.getBorrower().getFirstName() : "2PayUBack");
        emailModel.setContent("LoanRequest submitted to  " + loanRequest.getLender().getEmailId());
        emailService.sendEmail(Email.builder()
                .from("purviewemail@purviewservices.co.uk")
                .to(loanRequest.getBorrower().getEmailId())
                .subject(loanRequest.getReasonForBorrow())
                .model(emailModel)
                .build(), Constants.LOAN_REQUEST_EMAIL_TMPL);

        emailModel.setName(loanRequest.getLender().getFirstName() !=null ? loanRequest.getLender().getFirstName() : "2PayUBack");
        emailModel.setContent("LoanRequest from " + loanRequest.getBorrower().getEmailId());
        emailService.sendEmail(Email.builder()
                .from("purviewemail@purviewservices.co.uk")
                .to(loanRequest.getLender().getEmailId())
                .subject(loanRequest.getReasonForBorrow())
                .model(emailModel)
                .build(), Constants.LOAN_REQUEST_EMAIL_TMPL);
        return State.PENDING;

   }

    public State approveLoan(LoanApproval loanApproval) {
        Loan loan = Loan.convertFromBaseLoanApproval(loanApproval);
        loan.setLoanState(State.IN_PROCESS);
        loanProcessingService.createLoan(loan);
        return State.IN_PROCESS;
    }

    public State editLoan(LoanRequest loanRequest) {
        Loan loan = Loan.convertFromBaseLoanRequest(loanRequest);
        loan.setLoanState(State.PENDING);
        loanProcessingService.createLoan(loan);
        return State.PENDING;

    }

    public List<Loan> findLoanByLenderEmailId(String emailId) {
        return loanProcessingService.findLoanDetailsByLenderEmailId(emailId);
    }

    public Loan findLoanByBorrowerEmailId(String emailId) {
        return loanProcessingService.findLoanDetailsByBorrowerEmailId(emailId);
    }

    public List<Loan> findLoanByRepaymentDate() {
        return loanProcessingService.findLoanDetailsByRepaymentDate();
    }

    private EmailModel generateModel(BaseLoan loanDetails, String reasonForBorrow) {
        EmailModel model = new EmailModel();
        model.setLocation("London");
        model.setSignature("https://localhost:8083/");
        model.setPurpose(reasonForBorrow);
        model.setLoan_amt(loanDetails.getLoanAmt());
        model.setRepayment_frequency(loanDetails.getRepayFrequency());
        model.setRepayment_date(loanDetails.getRepaymentDate());
        model.setRepayment_amt(new BigDecimal(1000));
        model.setRepayment_amt_tot(new BigDecimal(1000));
        model.setLoan_req_summary_url("www.google.com");

        return model;
    }
}
