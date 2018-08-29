package com.fxrialab.timetrack.service.intf;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;

public interface MailService {
    void sendRegisterConfirmationEmail(String email,User user) throws ServiceException;
}
