package net.pay.you.back.request.manager.controller;


import javax.servlet.http.HttpServletResponse;

import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Email> sendEmail(@RequestParam String emailId) {
        try {
            Email email = new Email();
            email.setFrom("noreply@2payuback.com");
            email.setTo(emailId);
            email.setSubject("Test email");
            this.emailService.sendEmail(email);
            return ResponseEntity.ok().body(email);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Email service has internal issue, please try in few", exc);
        }
    }
}
