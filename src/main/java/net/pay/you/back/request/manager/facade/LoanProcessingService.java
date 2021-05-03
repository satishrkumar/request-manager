package net.pay.you.back.request.manager.facade;

import java.util.List;

import net.pay.you.back.request.manager.domain.loan.Loan;

public interface LoanProcessingService {
    Loan createLoan(Loan loan);

    List<Loan> findLoanDetailsByLenderEmailId(String emailId);

    Loan findLoanDetailsByBorrowerEmailId(String emailId);

    List<Loan> findLoanDetailsByRepaymentDate();

    Loan updateExistingLoan(Loan loan);

    String deleteLoan(String emailId);
}
