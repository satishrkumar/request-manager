package net.pay.you.back.request.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pay.you.back.request.manager.comm.BaseLoan;
import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.facade.impl.LoanProcessingServiceImpl;
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
        emailService.sendEmail(Email.builder()
                .name(loanRequest.getBorrower().getFirstName() !=null ? loanRequest.getBorrower().getFirstName() : "2PayUBack")
                .from("purviewemail@purviewservices.co.uk")
                .to(loanRequest.getBorrower().getEmailId())
                .content("LoanRequest submitted to  " + loanRequest.getLender().getEmailId())
                .subject(loanRequest.getReasonForBorrow())
                .model(generateModel(loanRequest, loanRequest.getReasonForBorrow()))
                .build(), "LoanRequestEmailTemplate.ftl");
        emailService.sendEmail(Email.builder()
                .name(loanRequest.getLender().getFirstName() !=null ? loanRequest.getLender().getFirstName() : "2PayUBack")
                .from("purviewemail@purviewservices.co.uk")
                .content("LoanRequest from " + loanRequest.getBorrower().getEmailId())
                .to(loanRequest.getLender().getEmailId())
                .subject(loanRequest.getReasonForBorrow())
                .model(generateModel(loanRequest, loanRequest.getReasonForBorrow()))
                .build(), "LoanRequestEmailTemplate.ftl");
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

    private Map<String, String> generateModel(BaseLoan loanDetails, String reasonForBorrow) {
        Map model = new HashMap();
        model.put("location", "London");
        model.put("signature", "https://localhost:8083/");
        model.put("purpose", reasonForBorrow);
        model.put("loan_amt", loanDetails.getLoanAmt()+"");
        model.put("repayment_frequency", loanDetails.getRepayFrequency()+"");
        model.put("repayment_date", loanDetails.getRepaymentDate()+"");
        model.put("repayment_amt", "REPAYMENT_AMT");
        model.put("repayment_amt_tot", "REPAYMENT_AMT_TOT");
        model.put("loan_req_summary_url", "www.google.com");

        return model;
    }
}
