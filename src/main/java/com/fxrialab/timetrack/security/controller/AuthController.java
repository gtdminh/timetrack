package com.fxrialab.timetrack.security.controller;

import com.fxrialab.timetrack.security.model.User;
import com.fxrialab.timetrack.security.persistence.UserService;
import com.fxrialab.timetrack.utils.EmailUtils;
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

    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(String email){
        User user = userService.findByUsernameOrEmail(email);
        if (user == null){
            try{
                User newUser = userService.createInactivatedUser(email);
                EmailUtils.sendRegisterConfirmationEmail(email, newUser);
            }
            catch (Exception ex){
                return "fail";
            }

            return "success";
        }
        else
        {
            return "email_existing";
        }
    }

    @GetMapping("/checkemail")
    public String checkemail(){
        return "checkemail";
    }
    @GetMapping("/registerconfirm/{activationcode}")
    public void registerConfirm(@PathVariable("activationcode") String activationcode){
        userService.registerConfirm(activationcode);
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
