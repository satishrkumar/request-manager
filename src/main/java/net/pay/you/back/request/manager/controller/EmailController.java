package net.pay.you.back.request.manager.controller;


import javax.servlet.http.HttpServletResponse;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService email;

    @PostMapping
    public ResponseEntity<Email> sendEmail(@RequestBody Email email, HttpServletResponse response) {
        try {
            this.email.sendEmail(email);
            return ResponseEntity.ok().body(email);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Email service has internal issue, please try in few", exc);
        }
    }
}
