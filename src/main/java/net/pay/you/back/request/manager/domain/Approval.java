package net.pay.you.back.request.manager.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.domain.user.User;

@Builder
@Data
public class Approval {
    private User borrower;
    private User lender;
    private Loan loan;
    private LocalDate approvalDateTime;
    private State state;


}
