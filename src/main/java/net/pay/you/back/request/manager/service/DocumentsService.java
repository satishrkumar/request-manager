package net.pay.you.back.request.manager.service;

import net.pay.you.back.request.manager.facade.DocumentCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class DocumentsService {

    @Autowired
    private DocumentCreatorService documentCreatorService;

    public ByteArrayInputStream generateAgrDocument(String lenderEmailId){
        return documentCreatorService.createDocument(lenderEmailId);
    };
}
