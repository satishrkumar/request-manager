package net.pay.you.back.request.manager.domain.loan;

import lombok.*;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;

import java.math.BigDecimal;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanRepayment {
    RepaymentFrequency repaymentFrequency;
    BigDecimal repaymentAmount;
    BigDecimal totalInterest;
    BigDecimal totalLoanPayable;
    BigDecimal principalAmount;
}
