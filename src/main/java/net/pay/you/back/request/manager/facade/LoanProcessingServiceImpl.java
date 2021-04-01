package net.pay.you.back.request.manager.facade;


import net.pay.you.back.request.manager.dao.LoanDAO;
import net.pay.you.back.request.manager.domain.Loan;
import net.pay.you.back.request.manager.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class LoanProcessingServiceImpl implements LoanProcessingService {
    @Autowired
    LoanDAO loanDAO;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    UUID uuid = UUID.randomUUID();

    @Override
    public Loan createLoan(Loan loan) {
        loan.setId(sequenceGeneratorService.generateSequence(Loan.SEQUENCE_NAME));
        return loanDAO.save(loan);
    }

    @Override
    public List<Loan> findLoanDetailsByLenderEmailId(final String emailId){
        List<Loan> loanDetails = loanDAO.findLoanByLenderEmailId(emailId);
        if (null != loanDetails && !loanDetails.isEmpty()) {
            return loanDetails;
        }else {
            throw new RuntimeException("Loan details not found for lender email id " + emailId);
        }
    }

    @Override
    public Loan
    findLoanDetailsByBorrowerEmailId(final String emailId){
        Optional<Loan> loanModelOptional = loanDAO.findLoanByBorrowerEmailId(emailId);
        if (loanModelOptional.isPresent()) {
            return loanModelOptional.get();
        }else {
            throw new RuntimeException("Loan details not found for borrower email id " + emailId);
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
