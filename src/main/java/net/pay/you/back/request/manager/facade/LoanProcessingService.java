package net.pay.you.back.request.manager.facade;

import java.util.List;

import net.pay.you.back.request.manager.domain.loan.Loan;

public interface LoanProcessingService {
    void setLoanStatus(String loanStatus);

    Loan createLoan(Loan loan);

    List<Loan> findAll();

    List<Loan> findUnarchivedLoans();

    Loan findLoanDetailsById(long id);

    List<Loan> findLoanDetailsByLenderEmailId(String emailId);

    List<Loan> findLoanDetailsByBorrowerEmailId(String emailId);

    List<Loan> findLoanDetailsByRepaymentDate();

    Loan updateExistingLoan(Loan loan);

    String deleteLoan(String emailId);
}
