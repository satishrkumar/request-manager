package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.comm.BaseLoan;
import net.pay.you.back.request.manager.domain.loan.LoanRepayment;
import net.pay.you.back.request.manager.service.RepaymentCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.pay.you.back.request.manager.domain.loan.Loan.convertToLoanObj;


@RestController
@RequestMapping("/repayment")
public class RepaymentController {
    @Autowired
    RepaymentCalculatorService repaymentCalculatorService;

    @PostMapping("/calculateMonthlyPayment")
    public LoanRepayment calculateMonthlyPayment(@RequestBody BaseLoan baseLoan) {
        return repaymentCalculatorService.calculateMonthlyPayment(convertToLoanObj(baseLoan));
    }

    @PostMapping("/calculateQuarterlyPayment")
    public LoanRepayment calculateQuarterlyPayment(@RequestBody BaseLoan baseLoan) {
        return repaymentCalculatorService.calculateQuarterlyPayment(convertToLoanObj(baseLoan));
    }

    @PostMapping("/calculateDailyPayment")
    public LoanRepayment calculateDailyPayment(@RequestBody BaseLoan baseLoan) {
        return repaymentCalculatorService.calculateDailyPayment(convertToLoanObj(baseLoan));
    }

    @PostMapping("/calculateYearlyPayment")
    public LoanRepayment calculateYearlyPayment(@RequestBody BaseLoan baseLoan) {
        return repaymentCalculatorService.calculateYearlyPayment(convertToLoanObj(baseLoan));
    }
}


