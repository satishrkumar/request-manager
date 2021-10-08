package net.pay.you.back.request.manager.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.facade.DocumentCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.pay.you.back.request.manager.util.Constants;

@Service
public class DocumentsService {

    @Autowired
    private DocumentCreatorService documentCreatorService;

    @Autowired
    private LoanService loanService;

    public ByteArrayInputStream generateAgrDocument(long loanId){
//        return documentCreatorService.createDocument(lenderEmailId);
        Loan loanDetails = loanService.findLoanById(loanId);

        if(null != loanDetails) {
            documentCreatorService.setTemplate(Constants.AGREEMENT_PDF_TMPL);
            documentCreatorService.setTemplateModel(loanDetails);
            return documentCreatorService.generatePDFFromTmpl();
        }

        // TODO: Create a new table to store PDF data

        return null;
    };
}
