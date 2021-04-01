package net.pay.you.back.request.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pay.you.back.request.manager.comm.BaseLoan;
import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.enums.State;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loandata")
public class Loan {
    public static final String SEQUENCE_NAME = "loandata_sequence";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String lenderEmailId;
    @Indexed(unique = true)
    private String borrowerEmailId;
    private BigDecimal principal;
    private BigDecimal apr;
    private BigDecimal interest;
    private Integer term;
    private RepaymentFrequency repayFrequency;
    private LocalDateTime repaymentDate;
    private LocalDateTime approvedTimeStamp;
    private Boolean deleteInd;
    private User borrower;
    private User lender;
    private String reasonForBorrow;
    private State loanState;



    public static Loan convertFromBaseLoanRequest(LoanRequest loanRequest) {
        Loan loan = convertToLoanObj(loanRequest);
        loan.setReasonForBorrow(loanRequest.getReasonForBorrow());

        return loan;
    }

    public static Loan convertFromBaseLoanApproval(LoanApproval loanApproval) {
        Loan loan = convertToLoanObj(loanApproval);
        loan.setReasonForBorrow(loanApproval.getReasonForBorrow());
        loan.setApprovedTimeStamp(loanApproval.getApprovedTimeStamp());

        return loan;


    }

    private static Loan convertToLoanObj(BaseLoan baseLoan) {
        Loan loan = new Loan();
        loan.setLenderEmailId(baseLoan.getLender().getEmailId());
        loan.setBorrowerEmailId(baseLoan.getBorrower().getEmailId());
        loan.setPrincipal(baseLoan.getLoanAmt());
        loan.setApr(baseLoan.getApr());
        loan.setRepaymentDate(baseLoan.getRepaymentDate());
        loan.setRepayFrequency(baseLoan.getRepayFrequency());
        return loan;
    }




}
