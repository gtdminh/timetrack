package com.fxrialab.timetrack.service.intf.security;

import com.fxrialab.timetrack.common.ServiceException;

public interface CaptchaService {
    void processResponse(String response) throws ServiceException;
}
