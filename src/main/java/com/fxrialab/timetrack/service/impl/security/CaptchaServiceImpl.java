package com.fxrialab.timetrack.service.impl.security;

import com.fasterxml.jackson.annotation.*;
import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.service.intf.security.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

// guide: https://www.baeldung.com/spring-security-registration-captcha

@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private RestOperations restTemplate;

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Override
    public boolean processResponse(String response) throws ServiceException{
        if(!responseSanityCheck(response)) {
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.RESPONSE_CONTAIN_INVALID_CHARACTERS,"Response contains invalid characters");
        }

        try {
            URI verifyUri = URI.create(String.format(
                    "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                    getReCaptchaSecret(), response, getClientIP()));
            GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
            if(!googleResponse.isSuccess()) {
                throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.RESPONSE_CONTAIN_INVALID_CHARACTERS,"reCaptcha was not successfully validated");
            }
            else{
                return true;
            }
        }
        catch (UnknownHostException ex){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.UNKNOWN_HOST_EXCEPTION);
        }
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

    private String getClientIP() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }

    private String getReCaptchaSecret(){
        return "";
    }
}



