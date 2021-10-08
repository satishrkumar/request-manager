package net.pay.you.back.request.manager.controller;

import java.io.*;

import net.pay.you.back.request.manager.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentsController {

    @Autowired
    DocumentsService documentsService;

    //PDf doc need to be stored
    //Base 64 (Raw bite need to convert in raw file)
    @GetMapping(value = "/generateAgrDocuments/{loanId}", produces = {"application/pdf"})
    public ResponseEntity<InputStreamResource> generateAgrDocuments(@PathVariable long loanId) {
        ByteArrayInputStream bis = documentsService.generateAgrDocument(loanId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=agreement.pdf");

        if (null != bis) {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        }
        return ResponseEntity.ok().body(null);

    }
}
