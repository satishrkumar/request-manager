package net.pay.you.back.request.manager.domain.loan;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanPayment {
    private String userEmailId;
    private String paymentId;
    private Integer installmentNo;
    private BigDecimal installmentAmt;
    private BigDecimal penaltyAmt;
    private BigDecimal previousOutstanding;
    private BigDecimal totalReceivable;
    private BigDecimal amountReceived;
}
