package com.fxrialab.timetrack.service.impl;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.MailService;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

    public void sendRegisterConfirmationEmail(String email,User user) throws ServiceException{

    }

}
