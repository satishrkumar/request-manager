package net.pay.you.back.request.manager.domain;

import lombok.*;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class EmailModel {
    private String name;
    private String content;
    private String location;
    private String signature;
    private String purpose;
    private BigDecimal loan_amt;
    private BigDecimal interest;
    private RepaymentFrequency repayment_frequency;
    private LocalDate repayment_date;
    private BigDecimal repayment_amt;
    private BigDecimal repayment_amt_tot;
    private String loan_req_summary_url;
}
