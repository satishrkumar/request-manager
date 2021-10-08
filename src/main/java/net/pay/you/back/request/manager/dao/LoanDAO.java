package net.pay.you.back.request.manager.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import net.pay.you.back.request.manager.domain.loan.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDAO extends MongoRepository<Loan, Long> {

    @Query("{ 'loanState' : ?0 }")
    List<Loan> findLoanByStatus(String status);

    @Query("{ 'id' : ?0 }")
    Optional<Loan> findLoanById(long id);

    @Query("{ 'lenderEmailId' : ?0 }")
    List<Loan> findLoanByLenderEmailId(String emailId);

    @Query("{ 'lenderEmailId' : ?0, 'loanState' : ?1 }")
    List<Loan> findLoanByLenderEmailIdAndStatus(String emailId, String status);

    @Query("{ 'borrowerEmailId' : ?0 }")
    List<Loan> findLoanByBorrowerEmailId(String emailId);

    @Query("{ 'borrowerEmailId' : ?0, 'loanState' : ?1 }")
    List<Loan> findLoanByBorrowerEmailIdAndStatus(String emailId, String status);

    @Query("{ 'repaymentDate' : { $gt : ?0 , $lte : ?1 } }")
   // @Query("{ {'repaymentDate' } : ?0 }")
    List<Loan> findLoanByRepaymentDate(LocalDateTime todaysDate,LocalDateTime repaymentDate);
}
