package com.fxrialab.timetrack.controller.security;

import com.fxrialab.timetrack.common.ResponseCode;
import com.fxrialab.timetrack.common.RestResponse;
import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.MailService;
import com.fxrialab.timetrack.service.intf.security.UserService;
import com.fxrialab.timetrack.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RestResponse register(String email){

        try{
            User newUser = userService.registerNewUser(email);
            mailService.sendRegisterConfirmationEmail(email,newUser);
        }
        catch (ServiceException ex){
            switch (ex.getExceptionCode()){
                case USER_EXISTING:
                    return new RestResponse(ResponseCode.EMAIL_EXISTING);
                case SEND_MAIL_FAIL:
                    return new RestResponse(ResponseCode.SEND_MAIL_FAIL);
            }
        }

        return new RestResponse(ResponseCode.SUCCESS);
    }

    @GetMapping("/checkemail")
    public String checkemail(){
        return "checkemail";
    }

    @GetMapping("/registerconfirm/{activationcode}")
    public ModelAndView registerConfirm(@PathVariable("activationcode") String activationCode){
        ModelAndView model = new ModelAndView();
        model.setViewName("registerconfirm");
        model.addObject("activationCode",activationCode);
        try{
            User user = userService.checkUserWithActivationCode(activationCode);
            model.addObject("code","OK");
            model.addObject("userstatus",user.getStatus());
            model.addObject("message","");
        }
        catch (ServiceException ex){
            model.addObject("code","FAIL");
            switch (ex.getExceptionCode()){
                case NO_CONFIRMATION_CODE:
                case NO_WAITING_FOR_CONFIRMATION:
                    model.addObject("message","There is no account for activation!");
                    break;
                case USER_HAS_BEEN_ACTIVATED:
                    model.addObject("message","Your account has been activated!");
                    break;
            }
        }
        return model;
    }

    @RequestMapping(value = "/registerNameAndPassword/{activationcode}",
            method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RestResponse registerNameAndPassword(@PathVariable("activationcode") String activationCode
            ,@RequestParam("fullname") String fullname,@RequestParam("password") String password){

        try{
            User newUser = userService.registerNameAndPassword(activationCode,fullname, password);
        }
        catch (ServiceException ex){
            switch (ex.getExceptionCode()){
                case NO_CONFIRMATION_CODE:
                case NO_WAITING_FOR_CONFIRMATION:
                    return new RestResponse(ResponseCode.NO_LEGAL_ACTIVATION_CODE);
                case USER_HAS_BEEN_ACTIVATED:
                    return new RestResponse(ResponseCode.USER_HAS_BEEN_ACTIVATED);
            }
        }

        return new RestResponse(ResponseCode.SUCCESS);
    }

    @GetMapping("/salt/{username}")
    @ResponseBody
    public Map<String, Object> getSalt(@PathVariable("username") String username)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("length", 256);
        map.put("iteration",1000);
        map.put("algorithm","Pbkdf2WithSha1");

        User user = userService.findByUsernameOrEmail(username);
        if(user != null){
            map.put("salt", user.getSalt());

        }
        else{
            map.put("salt", SecurityUtils.generateSalt());
        }

        return map;
    }
}
