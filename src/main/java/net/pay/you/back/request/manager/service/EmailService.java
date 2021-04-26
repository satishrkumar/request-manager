package net.pay.you.back.request.manager.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import net.pay.you.back.request.manager.domain.Email;
import net.pay.you.back.request.manager.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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


    public void sendEmail(Email email) {

        Map model = new HashMap();
        model.put("name", email.getName());
        model.put("location", "London");
        model.put("signature", "https://localhost:8083/");
        model.put("content", email.getContent());


        email.setModel(model);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
            Template template = emailConfig.getTemplate("test.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getModel());

            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setBcc(email.getFrom());
            mimeMessageHelper.setText(html, true);
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
        javaMailSender.send(message);

    }

    private void throwEmailRuntimeException(final Exception e) {
        e.fillInStackTrace();
        throw new EmailException(e.getMessage());
    }
}
