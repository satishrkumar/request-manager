package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.Loan;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping("/requestLoan")
    public State requestLoan(@RequestBody LoanRequest loanRequest) {
        return loanService.requestLoan(loanRequest);
    }

    @PostMapping("/approveLoan")
    public State approveLoan(@RequestBody LoanApproval loanApproval) {
        return loanService.approveLoan(loanApproval);
    }

    @GetMapping("/findLoanByLenderEmailId/{emailId}")
    public List<Loan> findLoanByLenderEmailId(@PathVariable String emailId) {
        return loanService.findLoanByLenderEmailId(emailId);
    }

    @GetMapping("/findLoanByBorrowerEmailId/{emailId}")
    public Loan findLoanByBorrowerEmailId(@PathVariable String emailId) {
        return loanService.findLoanByBorrowerEmailId(emailId);
    }

    @GetMapping("/findLoanByRepaymentDate")
    public List<Loan> findLoanByRepaymentDate() {
        return loanService.findLoanByRepaymentDate();
    }


}
