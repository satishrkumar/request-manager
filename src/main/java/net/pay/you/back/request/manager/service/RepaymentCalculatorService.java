package net.pay.you.back.request.manager.service;

import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.domain.loan.LoanRepayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RepaymentCalculatorService {

    private static final Logger logger = LogManager.getLogger(RepaymentCalculatorService.class);
    public static final Integer NO_OF_MONTHS_IN_YEAR = 12;
    public static final Integer NO_OF_DAYS_IN_YEAR = 365;
    public static final Integer NO_OF_QUARTERS_IN_YEAR = 4;
    public static final Integer ONE = 1;
    public static final BigDecimal MONTHLY_FEE= new BigDecimal("5.99");


    public LoanRepayment calculateMonthlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating monthly repayments ");
        BigDecimal principal = loan.getPrincipal();
        BigDecimal apr = loan.getApr().divide(BigDecimal.valueOf(100),4,RoundingMode.HALF_UP);
        Integer termInYears = loan.getTerm()==null?3:loan.getTerm();
        BigDecimal monthlyInterestRate = apr.divide(BigDecimal.valueOf(NO_OF_MONTHS_IN_YEAR), 4, RoundingMode.HALF_UP);
        Integer termInMonths = termInYears * NO_OF_MONTHS_IN_YEAR;
        double toDivideVal = ONE - Math.pow(ONE + monthlyInterestRate.doubleValue(), -termInMonths);
        BigDecimal monthlyPayment = (monthlyInterestRate.multiply(principal)).divide(BigDecimal.valueOf(toDivideVal), 4, RoundingMode.HALF_UP);
       // monthlyPayment = monthlyPayment.add(MONTHLY_FEE);
        BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(termInMonths));
        LoanRepayment loanRepayment = getLoanRepaymentValues(monthlyPayment, totalPayment);

        return loanRepayment;
    }

    private LoanRepayment getLoanRepaymentValues(BigDecimal monthlyPayment, BigDecimal totalPayment) {
        LoanRepayment loanRepayment = new LoanRepayment();
        loanRepayment.setRepaymentAmount(monthlyPayment);
        loanRepayment.setLoanServiceCharge(MONTHLY_FEE);
        loanRepayment.setRepaymentFrequency(RepaymentFrequency.Monthly);
        loanRepayment.setTotalLoanPayable(totalPayment);
        return loanRepayment;
    }

    public LoanRepayment calculateYearlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating yearly repayments ");
        LoanRepayment loanRepayment = calculateMonthlyPayment(loan);
        BigDecimal monthlyRepaymentAmount = loanRepayment.getRepaymentAmount();
        BigDecimal yearlyPayment = monthlyRepaymentAmount.multiply(BigDecimal.valueOf(NO_OF_MONTHS_IN_YEAR));
        loanRepayment.setRepaymentAmount(yearlyPayment);
        return loanRepayment;
    }

    public LoanRepayment calculateDailyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating daily repayments ");
        LoanRepayment loanRepayment = calculateYearlyPayment(loan);
        BigDecimal yearlyPayment = loanRepayment.getRepaymentAmount();
        BigDecimal dailyPayment = yearlyPayment.divide(BigDecimal.valueOf(NO_OF_DAYS_IN_YEAR));
        loanRepayment.setRepaymentAmount(dailyPayment);
        return loanRepayment;
    }

    public LoanRepayment calculateQuarterlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating quaterly repayments ");
        LoanRepayment loanRepayment = calculateYearlyPayment(loan);
        BigDecimal yearlyPayment = loanRepayment.getRepaymentAmount();
        BigDecimal quarterlyPayment = yearlyPayment.divide(BigDecimal.valueOf(NO_OF_QUARTERS_IN_YEAR));
        loanRepayment.setRepaymentAmount(quarterlyPayment);
        return loanRepayment;
    }
//
//    private void amortize(BigDecimal principal, BigDecimal APR, int term, BigDecimal monthlyPayment) {
//        BigDecimal monthlyIntere
// stRate = APR / 12;
//        BigDecimal totalPayment = monthlyPayment * term;
//        int payment = 1;
//        BigDecimal balance = principal;
//
//        //print information
//        System.out.println("Loan Amount: $" + principal);
//        System.out.println("Number of Payments: " + term);
//        System.out.println("Interest Rate: " + APR + "%");
//        System.out.println("Monthly Payment: $" + df.format(monthlyPayment));
//        System.out.println("Total Payment: $" + df.format(totalPayment));
//        System.out.println();
//        System.out.println("Payment#\tInterest\tPrincipal\tBalance");
//
//        do {
//            BigDecimal monthlyInterest = monthlyInterestRate * balance;
//            principal = monthlyPayment - monthlyInterest;
//            balance -= principal;
//
//            //Show Amortization Schedule
//            System.out.print(df.format(payment));
//            System.out.print("\t        ");
//            System.out.print(df.format(monthlyInterest));
//            System.out.print("\t        ");
//            System.out.print(df.format(principal));
//            System.out.print("\t        ");
//            System.out.print(df.format(balance));
//            System.out.println();
//            payment++;
//        }
//        while (payment <= term);
//    }
}



