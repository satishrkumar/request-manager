package net.pay.you.back.request.manager.facade;


import java.io.ByteArrayInputStream;
import java.util.Map;

import net.pay.you.back.request.manager.comm.DocumentCreator;
import net.pay.you.back.request.manager.domain.loan.Loan;
import org.apache.kafka.common.protocol.types.Field;

public interface DocumentCreatorService {
    void setTemplate(String template);

    void setTemplateModel(Loan templateModel);

    ByteArrayInputStream generatePDFFromTmpl();

    ByteArrayInputStream createDocument(String lenderEmailId);

    DocumentCreator findDocumentByEmailId(String emailId);

    DocumentCreator updateExistingDocument(DocumentCreator documentCreator);

    String deleteDocument(String emailId);
}
