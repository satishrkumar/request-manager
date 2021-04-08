package net.pay.you.back.request.manager.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RepaymentCalculatorService {

    private static final Logger logger = LogManager.getLogger(RepaymentCalculatorService.class);
    public static final Integer NO_OF_MONTHS = 12;
    public static final Integer ONE = 1;


    private BigDecimal calculateMonthlyPayment(BigDecimal principal, BigDecimal apr, Integer termInYears) {
        BigDecimal monthlyInterestRate = apr.divide(BigDecimal.valueOf(NO_OF_MONTHS));
        Integer termInMonths = termInYears * NO_OF_MONTHS;
        double toDivideVal = ONE - Math.pow(ONE + monthlyInterestRate.doubleValue(), -termInMonths);
        BigDecimal monthlyPayment = (monthlyInterestRate.multiply(principal)).divide(BigDecimal.valueOf(toDivideVal));
        return monthlyPayment;
    }
//
//    private void amortize(BigDecimal principal, BigDecimal APR, int term, BigDecimal monthlyPayment) {
//        BigDecimal monthlyInterestRate = APR / 12;
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



