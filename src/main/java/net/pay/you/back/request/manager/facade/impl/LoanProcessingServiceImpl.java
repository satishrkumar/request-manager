package net.pay.you.back.request.manager.facade.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import net.pay.you.back.request.manager.dao.LoanDAO;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanProcessingServiceImpl implements LoanProcessingService {
    @Autowired
    LoanDAO loanDAO;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    UUID uuid = UUID.randomUUID();

    @Override
    public Loan createLoan(Loan loan) {
        loan.setId(sequenceGeneratorService.generateSequence(Loan.SEQUENCE_NAME));
        return loanDAO.save(loan);
    }

    @Override
    public List<Loan> findLoanDetailsByLenderEmailId(final String emailId) {
        List<Loan> loanDetails = loanDAO.findLoanByLenderEmailId(emailId);
        if (null != loanDetails && !loanDetails.isEmpty()) {
            return loanDetails;
        } else {
            throw new RuntimeException("Loan details not found for lender email id " + emailId);
        }
    }

    @Override
    public Loan findLoanDetailsByBorrowerEmailId(final String emailId) {
        Optional<Loan> loanModelOptional = loanDAO.findLoanByBorrowerEmailId(emailId);
        if (loanModelOptional.isPresent()) {
            return loanModelOptional.get();
        } else {
            throw new RuntimeException("Loan details not found for borrower email id " + emailId);
        }
    }

    @Override
    public List<Loan> findLoanDetailsByRepaymentDate() {
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime repaymentDate = fromDate.plusDays(3L);
        List<Loan> loanDetails = loanDAO.findLoanByRepaymentDate(fromDate, fromDate.plusDays(4L));
        if (null != loanDetails && !loanDetails.isEmpty()) {
            return loanDetails.stream()
                    .filter(loan -> loan.getRepaymentDate().format(formatter).equals(repaymentDate.format(formatter)))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Loan details not found for todays date having repayment date " + repaymentDate.format(formatter));
        }
    }

    @Override
    public Loan updateExistingLoan(final Loan loan) {
        return loanDAO.save(loan);

    }

    @Override
    public String deleteLoan(final String emailId) {
        List<Loan> loanDetails = loanDAO.findLoanByLenderEmailId(emailId);
        if (null != loanDetails && !loanDetails.isEmpty()) {
            loanDetails.stream()
                    .forEach(deletedLoan -> {
                        deletedLoan.setDeleteInd(true);
                        loanDAO.save(deletedLoan);
                    });
            return "Loan deleted with emailId " + emailId;
        } else {
            throw new RuntimeException("Loan details not found for email id " + emailId);
        }
    }

}
