package net.pay.you.back.request.manager.comm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer loanTerm;

}
