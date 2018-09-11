package com.fxrialab.timetrack.controller.security;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.MailService;
import com.fxrialab.timetrack.service.intf.security.CaptchaService;
import com.fxrialab.timetrack.service.intf.security.UserService;
import com.fxrialab.timetrack.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
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
    @Autowired
    private CaptchaService captchaService;

    /************* Login Section *************************/

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");
        return "login";
    }


    @GetMapping("/salt/{username:.+}")
    @ResponseBody
    public Map<String, Object> getSalt(@PathVariable("username") String username) throws NoSuchAlgorithmException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("length", 256);
        map.put("iteration",1000);
        map.put("algorithm","nl");

        User user = userService.findByUsernameOrEmail(username);
        if(user != null){
            map.put("salt", user.getSalt());
        }
        else{
            map.put("salt", SecurityUtils.generateSalt());
        }

        return map;
    }


    /************* Sign up Section *************************/

    @GetMapping("/signup")
    public String signup(){
        return "signup/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signupPost(String email){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signup/robothumanverify");
        mav.addObject("email",email);

        return mav;
    }

    @PostMapping("/email-submit")
    public ModelAndView emailSubmit(String email, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try{
            String captchaResponse = request.getParameter("g-recaptcha-response");
            if (captchaService.processResponse(captchaResponse)){
                User newUser = userService.registerNewUser(email);
                mailService.sendRegisterConfirmationEmail(email,newUser);
                mav.setViewName("/signup/inputemailconfirm");
            }
        }
        catch (ServiceException ex){
            switch (ex.getExceptionCode()){
                case RESPONSE_CONTAIN_INVALID_CHARACTERS:
                case RECAPTCHA_NOT_SUCCESSFULLY_VALIDATED:
                    mav.addObject("message","Invalid Captcha!");
                    mav.setViewName("/signup/signup");

                    break;
                case UNKNOWN_HOST_EXCEPTION:
                    mav.setViewName("/signup/signup");
                    mav.addObject("message","There is a problem with server, please contact admin for detail.");
                    log.error(ex.toString());
                    break;
                case USER_EXISTING:
                    mav.addObject("message","There is an existing email signed up.");
                    mav.setViewName("/signup/signup");

                    break;
                case SEND_MAIL_FAIL:
                    mav.setViewName("/signup/signup");
                    mav.addObject("message","There is a problem with sending mail.");
                    break;
            }

        }
        return mav;

    }

    @GetMapping("/robot-human-verify")
    public String robotHumanVerify(){
        return "signup/robothumanverify";
    }

    @GetMapping("/do-activation/{activationcode}")
    public ModelAndView registerConfirm(@PathVariable("activationcode") String activationCode){
        ModelAndView model = new ModelAndView();

        model.addObject("activationCode",activationCode);
        try{
            User user = userService.checkUserWithActivationCode(activationCode);
            model.addObject("code","OK");
            model.addObject("userstatus",user.getStatus());
            model.addObject("message","");
            model.setViewName("signup/informationconfirm");
        }
        catch (ServiceException ex){
            model.addObject("code","FAIL");
            model.setViewName("login");
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

    @RequestMapping(value = "/information-submit/{activationcode}",
            method = RequestMethod.POST, produces = "text/html")
    public ModelAndView informationSubmit(@PathVariable("activationcode") String activationCode, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try{

            String managingType = request.getParameter("managingtype");
            String projectType = request.getParameter("projecttype");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String companyName = request.getParameter("companyname");
            String companySize = request.getParameter("companysize");
            User newUser = userService.registerUserInformation(activationCode,fullname, password, managingType, projectType
            ,companyName, companySize);
            mav.setViewName("redirect:/auth/login");
        }
        catch (ServiceException ex){
            mav.addObject("code","FAIL");
            mav.setViewName("login");
            switch (ex.getExceptionCode()){
                case NO_CONFIRMATION_CODE:
                case NO_WAITING_FOR_CONFIRMATION:
                    mav.addObject("message","There is no account for activation!");
                case USER_HAS_BEEN_ACTIVATED:
                    mav.addObject("message","Your account has been activated!");
                case INVALID_CREATE_PASSWORD:
                    mav.addObject("message","Can not create password!");
            }
        }

        return mav;
    }

}
