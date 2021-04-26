package net.pay.you.back.request.manager.comm;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanEdit {
    private BigDecimal newLoanAmt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime newRepaymentDate;
    private BigDecimal newApr;
    private RepaymentFrequency newRepayFrequency;
    private User newLender;
    private User newBorrower;
    private Integer newLoanTerm;
}
