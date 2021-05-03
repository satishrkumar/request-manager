package net.pay.you.back.request.manager.domain.loan;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanReceipt {
    private String loanPaymentId;
    private BigDecimal paymentAmt;
    //placeholder
    private String otherPaymentDetails;
}
