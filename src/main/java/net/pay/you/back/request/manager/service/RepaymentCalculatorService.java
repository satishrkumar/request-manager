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
    public static final Integer NO_OF_MONTHS_IN_QUARTER = 3;
    public static final Integer NO_OF_DAYS_IN_YEAR = 365;
    public static final Integer NO_OF_QUARTERS_IN_YEAR = 4;
    public static final Integer ONE = 1;
    public static final BigDecimal MONTHLY_FEE= new BigDecimal("5.99");


    public LoanRepayment calculateMonthlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating monthly repayments ");
        BigDecimal principal = loan.getPrincipal();
        BigDecimal apr = loan.getApr().divide(BigDecimal.valueOf(100),4,RoundingMode.HALF_UP);
        BigDecimal monthlyInterestRate = apr.divide(BigDecimal.valueOf(NO_OF_MONTHS_IN_YEAR), 4, RoundingMode.HALF_UP);
        Integer termInMonths = loan.getTerm();
        double toDivideVal = ONE - Math.pow(ONE + monthlyInterestRate.doubleValue(), -termInMonths);
        BigDecimal monthlyPayment = (monthlyInterestRate.multiply(principal)).divide(BigDecimal.valueOf(toDivideVal), 4, RoundingMode.HALF_UP);
       // monthlyPayment = monthlyPayment.add(MONTHLY_FEE);
        BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(termInMonths));
        BigDecimal totalInterest = totalPayment.subtract(principal);
        LoanRepayment loanRepayment = getLoanRepaymentValues(monthlyPayment, totalPayment,totalInterest,principal);

        return loanRepayment;
    }

    private LoanRepayment getLoanRepaymentValues(BigDecimal paymentAmt, BigDecimal totalPayment, BigDecimal totalInterest, BigDecimal principal) {
        LoanRepayment loanRepayment = new LoanRepayment();
        loanRepayment.setRepaymentAmount(paymentAmt);
        loanRepayment.setTotalInterest(totalInterest);
        loanRepayment.setRepaymentFrequency(RepaymentFrequency.Monthly);
        loanRepayment.setTotalLoanPayable(totalPayment);
        loanRepayment.setPrincipalAmount(principal);
        return loanRepayment;
    }

    public LoanRepayment calculateYearlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating yearly repayments ");
        Integer termInMonths = loan.getTerm()*NO_OF_MONTHS_IN_YEAR;
        loan.setTerm(termInMonths);
        LoanRepayment loanRepayment = calculateMonthlyPayment(loan);
        BigDecimal monthlyRepaymentAmount = loanRepayment.getRepaymentAmount();
        BigDecimal yearlyPayment = monthlyRepaymentAmount.multiply(BigDecimal.valueOf(NO_OF_MONTHS_IN_YEAR));
        loanRepayment.setRepaymentAmount(yearlyPayment);
        loanRepayment.setRepaymentFrequency(RepaymentFrequency.Annualy);
        return loanRepayment;
    }

    public LoanRepayment calculateDailyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating daily repayments ");
        BigDecimal principal = loan.getPrincipal();
        BigDecimal apr = loan.getApr().divide(BigDecimal.valueOf(100),4,RoundingMode.HALF_UP);
        BigDecimal dailyInterestRate = apr.divide(BigDecimal.valueOf(NO_OF_DAYS_IN_YEAR), 8, RoundingMode.HALF_UP);
        Integer termInDays = loan.getTerm();
        double toDivideVal = ONE - Math.pow(ONE + dailyInterestRate.doubleValue(), -termInDays);
        BigDecimal dailyPayment = (dailyInterestRate.multiply(principal)).divide(BigDecimal.valueOf(toDivideVal), 2, RoundingMode.UP
        );
        BigDecimal totalPayment = dailyPayment.multiply(BigDecimal.valueOf(termInDays));
        BigDecimal totalInterest = totalPayment.subtract(principal);
        LoanRepayment loanRepayment = getLoanRepaymentValues(dailyPayment, totalPayment,totalInterest,principal);
        loanRepayment.setRepaymentFrequency(RepaymentFrequency.Daily);
        return loanRepayment;

    }

    public LoanRepayment calculateQuarterlyPayment(Loan loan) {
        //BigDecimal principal, BigDecimal apr, Integer termInYears
        logger.info("Calculating quaterly repayments ");
        Integer termInMonths = loan.getTerm()*NO_OF_MONTHS_IN_QUARTER;
        loan.setTerm(termInMonths);
        LoanRepayment loanRepayment = calculateMonthlyPayment(loan);
        BigDecimal monthlyRepaymentAmount  = loanRepayment.getRepaymentAmount();
        BigDecimal quarterlyPayment =  monthlyRepaymentAmount.multiply(BigDecimal.valueOf(NO_OF_MONTHS_IN_QUARTER));
        loanRepayment.setRepaymentAmount(quarterlyPayment);
        loanRepayment.setRepaymentFrequency(RepaymentFrequency.Quarterly);
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



