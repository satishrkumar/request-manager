package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/documents")
public class DocumentsController {

    @Autowired
    DocumentsService documentsService;

    //PDf doc need to be stored
    //Base 64 (Raw bite need to convert in raw file)
    //
    @GetMapping(value = "/generateAgrDocuments/{lenderEmailId}",produces = {"application/pdf"})
    public ResponseEntity<InputStreamResource> generateAgrDocuments(@PathVariable String lenderEmailId) {
        ByteArrayInputStream bis = documentsService.generateAgrDocument(lenderEmailId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=agreement.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(bis));

    }


}
