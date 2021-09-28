package net.pay.you.back.request.manager.domain.loan;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanRepayment {
    private BigDecimal repaymentAmount;
    private BigDecimal totalInterest;
    private RepaymentFrequency repaymentFrequency;
    private BigDecimal totalLoanPayable;
    private BigDecimal principalAmount;
}
