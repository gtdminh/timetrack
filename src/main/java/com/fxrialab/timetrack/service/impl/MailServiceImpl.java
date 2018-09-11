package com.fxrialab.timetrack.service.impl;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    public JavaMailSender emailSender;

    public String sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("anh.phantuan@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        this.emailSender.send(message);

        return "Email Sent!";
    }

    public void sendWithAttachment(String email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);

        helper.setText("sendWithAttachment");

        // let's attach the infamous windows Sample file (this time copied to c:/)
        FileSystemResource file = new FileSystemResource(new File("//file path"));
        helper.addAttachment("CoolImage.jpg", file);

        emailSender.send(message);
    }

    public void sendInlineResource(String email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);

        // use the true flag to indicate the text included is HTML
        helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);

        // let's include the infamous windows Sample file (this time copied to c:/)
        FileSystemResource res = new FileSystemResource(new File("c:/Sample.jpg"));
        helper.addInline("identifier1234", res);

        emailSender.send(message);
    }

    public void sendRegisterConfirmationEmail(String email, User user) throws ServiceException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Register confirmation email from timetrack");
        message.setText("You've registered to Fxrialab");
        emailSender.send(message);
    }

}
