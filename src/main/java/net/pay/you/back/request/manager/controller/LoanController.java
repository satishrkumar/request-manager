package net.pay.you.back.request.manager.controller;

import java.util.List;

import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.comm.LoanRequestPayload;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.service.LoanService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    LoanService loanService;
    @Autowired
    DocumentsController documentsController;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Service is up!", HttpStatus.OK);
    }

    @PostMapping("/requestLoan")
    public ResponseEntity<Loan> requestLoan(@RequestBody LoanRequestPayload loanRequest) {
        Loan loan = loanService.requestLoan(loanRequest.getLoanRequest());
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/approveLoan/{id}")
    public ResponseEntity<Loan> approveLoan(@PathVariable long id) {
        Loan loan = loanService.editLoanStatus(id, State.APPROVED);
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/editLoan")
    public State editLoan(@RequestBody LoanRequest loanRequest) {
        return loanService.editLoan(loanRequest);
    }

    @GetMapping("/rejectLoan/{id}")
    public ResponseEntity<Loan> rejectLoan(@PathVariable long id) {
        Loan loan = loanService.editLoanStatus(id, State.REJECTED);
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fetchAllLoans")
    public ResponseEntity<List<Loan>> fetchAllLoans(@RequestParam(required = false) String status) {
        return new ResponseEntity<>(loanService.fetchAllLoans(status), HttpStatus.OK);
    }

    @GetMapping("/fetchUnarchivedLoans")
    public ResponseEntity<List<Loan>> fetchUnarchivedLoans() {
        return new ResponseEntity<>(loanService.fetchUnarchivedLoans(), HttpStatus.OK);
    }

    @GetMapping("/findLoanById/{id}")
    public ResponseEntity<Loan> findLoanById(@PathVariable long id) {
        Loan loan = loanService.findLoanById(id);
        return new ResponseEntity<>(loan,
                (null != loan) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findLoanByLenderEmailId/{emailId}")
    public ResponseEntity<List<Loan>> findLoanByLenderEmailId(@PathVariable String emailId, @RequestParam(required = false) String status) {
        return new ResponseEntity<>(loanService.findLoanByLenderEmailId(emailId, status),
                HttpStatus.OK);
    }

    @GetMapping("/findLoanByBorrowerEmailId/{emailId}")
    public ResponseEntity<List<Loan>> findLoanByBorrowerEmailId(@PathVariable String emailId, @RequestParam(required = false) String status) {
        return new ResponseEntity<>(loanService.findLoanByBorrowerEmailId(emailId, status),
                HttpStatus.OK);
    }

    @GetMapping("/findLoanByRepaymentDate")
    public ResponseEntity<List<Loan>> findLoanByRepaymentDate() {
        return new ResponseEntity<>(loanService.findLoanByRepaymentDate(), HttpStatus.OK);
    }
}
