package net.pay.you.back.request.manager.facade.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import net.pay.you.back.request.manager.dao.LoanDAO;
import net.pay.you.back.request.manager.domain.enums.State;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.facade.LoanProcessingService;
import net.pay.you.back.request.manager.facade.UserService;
import net.pay.you.back.request.manager.job.EmailJob;
import net.pay.you.back.request.manager.service.SequenceGeneratorService;
import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LoanProcessingServiceImpl implements LoanProcessingService {
    private static final Logger logger = LogManager.getLogger(LoanProcessingServiceImpl.class);
    @Autowired
    LoanDAO loanDAO;

    @Autowired
    UserService userService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    private String loanStatus = "";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    UUID uuid = UUID.randomUUID();

    @Override
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = (null == loanStatus) ? "" : loanStatus;
    }

    @Override
    public Loan createLoan(Loan loan) {
        loan.setId(sequenceGeneratorService.generateSequence(Loan.SEQUENCE_NAME));
        return loanDAO.save(loan);
    }

    @Override
    public List<Loan> findAll() {
        List<Loan> loanList = (EnumUtils.isValidEnum(State.class, this.loanStatus))
                ? loanDAO.findLoanByStatus(this.loanStatus) : loanDAO.findAll();

        this.loanStatus = "";
        if (null != loanList && !loanList.isEmpty()) {
            return loanList;
        } else {
            logger.error("No Loan data available");
//            throw new RuntimeException("No Loan data available");
        }
        return new ArrayList<>();
    }

    @Override
    public Loan findLoanDetailsById(long id) {
        Optional<Loan> loanModelOptional = loanDAO.findLoanById(id);

        if (loanModelOptional.isPresent()) {
            loanModelOptional.get().setBorrower(userService.findUserByEmailId(loanModelOptional.get().getBorrowerEmailId()));
            loanModelOptional.get().setLender(userService.findUserByEmailId(loanModelOptional.get().getLenderEmailId()));
            return loanModelOptional.get();
        } else {
            logger.error("Loan details not found for loan id " + id);
//            throw new RuntimeException("Loan details not found for loan id " + id);
        }
        return null;
    }

    @Override
    public List<Loan> findLoanDetailsByLenderEmailId(final String emailId) {
        List<Loan> loanDetails = (EnumUtils.isValidEnum(State.class, this.loanStatus))
                ? loanDAO.findLoanByLenderEmailIdAndStatus(emailId, this.loanStatus)
                : loanDAO.findLoanByLenderEmailId(emailId);

        this.loanStatus = "";
        if (null != loanDetails && !loanDetails.isEmpty()) {
            return loanDetails;
        } else {
            logger.error("Loan details not found for lender email id " + emailId);
//            throw new RuntimeException("Loan details not found for lender email id " + emailId);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Loan> findLoanDetailsByBorrowerEmailId(final String emailId) {
        List<Loan> loanDetails = (EnumUtils.isValidEnum(State.class, this.loanStatus))
                ? loanDAO.findLoanByBorrowerEmailIdAndStatus(emailId, this.loanStatus)
                : loanDAO.findLoanByBorrowerEmailId(emailId);

        this.loanStatus = "";
        if (null != loanDetails && !loanDetails.isEmpty()) {
            return loanDetails;
        } else {
            logger.error("Loan details not found for borrower email id " + emailId);
//            throw new RuntimeException("Loan details not found for borrower email id " + emailId);
        }
        return new ArrayList<>();
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
            logger.error("Loan details not found for todays date having repayment date " + repaymentDate.format(formatter));
//            throw new RuntimeException("Loan details not found for todays date having repayment date " + repaymentDate.format(formatter));
        }
        return new ArrayList<>();
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
            logger.error("Loan details not found for email id " + emailId);
//            throw new RuntimeException("Loan details not found for email id " + emailId);
        }
        return null;
    }

}
