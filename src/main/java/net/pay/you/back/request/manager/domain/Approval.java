package net.pay.you.back.request.manager.domain;

import lombok.Builder;
import lombok.Data;
import net.pay.you.back.request.manager.domain.enums.State;

import java.time.LocalDate;

@Builder
@Data
public class Approval {
    private User borrower;
    private User lender;
    private Loan loan;
    private LocalDate approvalDateTime;
    private State state;


}
