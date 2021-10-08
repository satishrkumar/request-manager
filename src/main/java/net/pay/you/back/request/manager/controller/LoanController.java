package net.pay.you.back.request.manager.controller;

import java.util.List;

import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.comm.LoanRequestPayload;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping("/requestLoan")
    public ResponseEntity<Loan> requestLoan(@RequestBody LoanRequestPayload loanRequest) {
        Loan loan = loanService.requestLoan(loanRequest.getLoanRequest());
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/approveLoan/{id}")
    public ResponseEntity<Loan> approveLoan(@PathVariable long id) {
        Loan loan = loanService.approveLoan(id);
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/editLoan")
    public State editLoan(@RequestBody LoanRequest loanRequest) {
        return loanService.editLoan(loanRequest);
    }

    @GetMapping("/fetchAllLoans")
    public ResponseEntity<List<Loan>> fetchAllLoans() {
        return new ResponseEntity<>(loanService.fetchAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/findLoanById/{id}")
    public ResponseEntity<Loan> findLoanById(@PathVariable long id) {
        Loan loan = loanService.findLoanById(id);
        return new ResponseEntity<>(loanService.findLoanById(id),
                (null != loan) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findLoanByLenderEmailId/{emailId}")
    public ResponseEntity<List<Loan>> findLoanByLenderEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(loanService.findLoanByLenderEmailId(emailId),
                HttpStatus.OK);
    }

    @GetMapping("/findLoanByBorrowerEmailId/{emailId}")
    public ResponseEntity<List<Loan>> findLoanByBorrowerEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(loanService.findLoanByBorrowerEmailId(emailId),
                HttpStatus.OK);
    }

    @GetMapping("/findLoanByRepaymentDate")
    public ResponseEntity<List<Loan>> findLoanByRepaymentDate() {
        return new ResponseEntity<>(loanService.findLoanByRepaymentDate(), HttpStatus.OK);
    }


}
