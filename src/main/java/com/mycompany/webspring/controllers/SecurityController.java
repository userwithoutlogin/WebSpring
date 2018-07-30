/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.controllers;

import com.mycompany.webspring.utils.Message;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author root
 */
@Controller

public class SecurityController {
     @Autowired
     MessageSource messageSource;
     
    @RequestMapping(value="/login",params =  "error",method = RequestMethod.GET)
    public String loginfail(Model model ,Locale locale){
     model.addAttribute(
            "message",
             new Message(
                "error" , messageSource.getMessage("login_fail", new Object[]{}, locale)
             )
     );
        return "index";
    }
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(){
        return "index";
    }
    

}
