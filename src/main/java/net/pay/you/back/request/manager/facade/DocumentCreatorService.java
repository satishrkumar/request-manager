package net.pay.you.back.request.manager.facade;


import java.io.ByteArrayInputStream;

import net.pay.you.back.request.manager.comm.DocumentCreator;

public interface DocumentCreatorService {
    ByteArrayInputStream createDocument(String lenderEmailId);

    DocumentCreator findDocumentByEmailId(String emailId);

    DocumentCreator updateExistingDocument(DocumentCreator documentCreator);

    String deleteDocument(String emailId);
}
