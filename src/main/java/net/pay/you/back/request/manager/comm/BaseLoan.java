package net.pay.you.back.request.manager.comm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseLoan {
    private BigDecimal loanAmt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate repaymentDate;
    private BigDecimal rateOfInterest;
    private RepaymentFrequency repayFrequency;
    private User lender;
    private User borrower;
    private Integer loanTerm;

}
