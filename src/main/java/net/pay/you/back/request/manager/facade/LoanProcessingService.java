package net.pay.you.back.request.manager.facade;

import net.pay.you.back.request.manager.domain.loan.Loan;

import java.util.List;

public interface LoanProcessingService {
    Loan createLoan(Loan loan);

    List<Loan> findLoanDetailsByLenderEmailId(String emailId);

    Loan findLoanDetailsByBorrowerEmailId(String emailId);

    List<Loan> findLoanDetailsByRepaymentDate();

    Loan updateExistingLoan(Loan loan);

    String deleteLoan(String emailId);
}
