package net.pay.you.back.request.manager.domain.loan;

import lombok.*;

import java.math.BigDecimal;

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
