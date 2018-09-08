package com.fxrialab.timetrack.service.impl;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.MailService;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {
    public void sendRegisterConfirmationEmail(String email,User user) throws ServiceException{

    }

//    @Autowired
//    public JavaMailSender emailSender;
//
//    public String sendSimpleEmail() {
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo("anh.phantuan@gmail.com");
//        message.setSubject("Test Simple Email");
//        message.setText("Hello, Im testing Simple Email");
//
//        this.emailSender.send(message);
//
//        return "Email Sent!";
//    }
//
//    public void sendRegisterConfirmationEmail(String email, User user) throws ServiceException {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Register confirmation email from timetrack");
//        message.setText("You've registered to Fxrialab");
//        emailSender.send(message);
//    }

}
