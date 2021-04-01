package net.pay.you.back.request.manager.service;

import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.Loan;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.facade.LoanProcessingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanService {
    @Autowired
    private LoanProcessingServiceImpl loanServiceFacade;

    public State requestLoan(LoanRequest loanRequest) {
        Loan loan = Loan.convertFromBaseLoanRequest(loanRequest);
        loan.setLoanState(State.PENDING);
        loanServiceFacade.createLoan(loan);
        return State.PENDING;
   }

    public State approveLoan(LoanApproval loanApproval) {
        Loan loan = Loan.convertFromBaseLoanApproval(loanApproval);
        loan.setLoanState(State.IN_PROCESS);
        loanServiceFacade.createLoan(loan);
        return State.IN_PROCESS;
    }

    public List<Loan> findLoanByLenderEmailId(String emailId) {
        return loanServiceFacade.findLoanDetailsByLenderEmailId(emailId);
    }

    public Loan findLoanByBorrowerEmailId(String emailId) {
        return loanServiceFacade.findLoanDetailsByBorrowerEmailId(emailId);
    }
}
