package net.pay.you.back.request.manager.dao;

import net.pay.you.back.request.manager.domain.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanDAO extends MongoRepository<Loan, Long> {

    @Query("{ 'lenderEmailId' : ?0 }")
    List<Loan> findLoanByLenderEmailId(String emailId);

    @Query("{ 'borrowerEmailId' : ?0 }")
    Optional<Loan> findLoanByBorrowerEmailId(String emailId);

    //@Query("{ 'repaymentDate' : { $gt : ?0 } }")
    @Query("{ 'repaymentDate' : ?0 }")
    List<Loan> findLoanByRepaymentDate(LocalDateTime repaymentDate);
}
