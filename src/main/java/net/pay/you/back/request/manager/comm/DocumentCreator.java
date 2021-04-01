package net.pay.you.back.request.manager.comm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreator {
    private String lenderName;
    private String borrowerName;
    private BigDecimal interestRate;
    private LocalDate agreementDate;
    private LocalDate completionDate;
    private String repaymentFrequency;



}
