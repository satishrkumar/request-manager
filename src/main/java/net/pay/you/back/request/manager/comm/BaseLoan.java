package net.pay.you.back.request.manager.comm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.pay.you.back.request.manager.domain.User;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseLoan {
    private BigDecimal loanAmt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime repaymentDate;
    private BigDecimal apr;
    private RepaymentFrequency repayFrequency;
    private User lender;
    private User borrower;


}
