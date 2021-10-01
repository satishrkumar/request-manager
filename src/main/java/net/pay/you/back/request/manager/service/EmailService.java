package net.pay.you.back.request.manager.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import net.pay.you.back.request.manager.comm.LoanRequest;
import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.domain.EmailModel;
import net.pay.you.back.request.manager.domain.loan.Loan;
import net.pay.you.back.request.manager.exception.EmailException;
import net.pay.you.back.request.manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("freeMarkerConfigurationFactoryBean")
    private Configuration emailConfig;

    @Autowired
    @Qualifier("webApplicationContext")
    private ResourceLoader resourceLoader;

    public void sendEmail(Email email) {

        EmailModel model = new EmailModel();
        model.setLocation("London");
        model.setSignature("https://localhost:8083/");

        email.setModel(model);
        sendEmail(email, Constants.TEST_EMAIL_TMPL);
    }

    public void sendEmail(Email email, String email_template) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
            Template template;
            if(null == email_template || email_template.length()==0)
                template = emailConfig.getTemplate(Constants.TEST_EMAIL_TMPL);
            else
                template = emailConfig.getTemplate(email_template); //emailConfig.getTemplate("test.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getModel());

            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setBcc(email.getFrom());
            mimeMessageHelper.setText(html, true);
            Resource resource = resourceLoader.getResource("classpath:app_logo.png");
//            mimeMessageHelper.addInline("app_logo", new ClassPathResource("app_logo.png").getInputStream());
            mimeMessageHelper.addInline("app_logo", resource);
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setFrom(email.getFrom());
        } catch (MessagingException e) {
            throwEmailRuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throwEmailRuntimeException(e);
        } catch (IOException e) {
            throwEmailRuntimeException(e);
        } catch (TemplateException e) {
            throwEmailRuntimeException(e);
        }

        try {
            javaMailSender.send(message);
        }
        catch (MailException e) {
            throwEmailRuntimeException(e);
        }
    }

    private void throwEmailRuntimeException(final Exception e) {
        e.fillInStackTrace();
        throw new EmailException(e.getMessage());
    }
}
