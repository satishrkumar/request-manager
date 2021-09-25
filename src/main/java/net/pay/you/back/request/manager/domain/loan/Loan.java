package net.pay.you.back.request.manager.domain.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pay.you.back.request.manager.comm.BaseLoan;
import net.pay.you.back.request.manager.comm.LoanApproval;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.enums.RepaymentFrequency;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate repaymentDate;
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

    public static Loan convertToLoanObj(BaseLoan baseLoan) {
        Loan loan = new Loan();
        System.out.println(loan);
        System.out.println(baseLoan);
        loan.setLenderEmailId(baseLoan.getLender()!= null ? baseLoan.getLender().getEmailId() : "gaurav.udar@purviewservices.com");
        loan.setBorrowerEmailId(baseLoan.getBorrower()!= null  ? baseLoan.getBorrower().getEmailId() : "satish.kumar@purviewservices.com");
        loan.setPrincipal(baseLoan.getLoanAmt());
        loan.setApr(baseLoan.getRateOfInterest());
        System.out.println("*********************");
        System.out.println(baseLoan.getRepaymentDate());

        loan.setRepaymentDate(baseLoan.getRepaymentDate());
        loan.setTerm(baseLoan.getLoanTerm());
        loan.setRepayFrequency(baseLoan.getRepayFrequency() != null ? baseLoan.getRepayFrequency() : RepaymentFrequency.Monthly);

        return loan;
    }




}

