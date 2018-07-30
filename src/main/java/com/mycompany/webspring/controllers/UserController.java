/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.controllers;

import com.mycompany.webspring.dao.UsersDAO;
import com.mycompany.webspring.entity.User;
import com.mycompany.webspring.utils.CheckPassword;
import com.mycompany.webspring.utils.Message;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author root
 */
@Controller
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {
    
    @Autowired
    MessageSource messageSource;
    
    @Resource(name="usersDAO")
    UsersDAO userDAO;
    
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", userDAO.findAll());
        return "list";
    }
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id,Model model,
            SecurityContextHolderAwareRequestWrapper request)
    {
        boolean openEdit = request.isUserInRole("ROLE_ADMIN");
        model.addAttribute("user", userDAO.findById(id));
        model.addAttribute("openEdit", openEdit);
        return "show";
    }
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String profile(Model model/*,SecurityContextHolderAwareRequestWrapper request*/){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         boolean openEdit = true;
        model.addAttribute("user", userDAO.findByUserName(authentication.getName()));
        model.addAttribute("openEdit", openEdit);
        return "show";
    }
    
     
    
    @RequestMapping(value="/{id}",params = "form",method = RequestMethod.GET)
    public String editForm(Model model,@PathVariable("id")Long id){
        model.addAttribute("user", userDAO.findById(id));
        return "edit";
    }
   @RequestMapping(value="/{id}/edit"  ,method = RequestMethod.POST)   
    public String edit( User user,
            Locale locale,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
           Model model,
           @RequestParam(name = "file",required = false) Part file,
             @RequestParam(value = "oldPassword",required = false)String oldPassword,
             @RequestParam(value = "retriedOldPassword",required = false)String retriedOldPassword,
            @RequestParam(value = "newPassword",required = false)String newPassword,
            @RequestParam(value = "retriedNewPassword",required = false)String retriedNewPassword
            
    )
            
            
    {
        if(bindingResult.hasErrors()){
            addMessageToModel(model,"error","user_edit_fail",locale);      
            return "edit";
        }  
         addMessageToRedirectAttr(redirectAttributes,"success","user_edit_success",locale);               
         user.setPhoto(loadImage(file));
        
         CheckPassword checkPassword = new CheckPassword(oldPassword, retriedOldPassword, newPassword, retriedNewPassword);
        if(checkPassword.check())
             user.setPassword(newPassword);
        else
             user.setPassword(oldPassword);
        userDAO.update(user);
        return "redirect:/user/"+user.getId();
    }
    
   @RequestMapping(value="/create"  ,method = RequestMethod.POST)
    
    public String create(  User user,             
            Locale locale,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            @RequestParam(value = "file",required = false)Part file,
             
            @RequestParam(value = "newPassword",required = true)String newPassword,
            @RequestParam(value = "retriedNewPassword",required = true)String retriedNewPassword
            
    ){
        if(bindingResult.hasErrors()){
            addMessageToModel(model,"error","user_create_fail",locale);            
            model.addAttribute("user", user);
            return "create";
        }  
        addMessageToRedirectAttr(redirectAttributes,"success","user_create_success",locale);       
        user.setPhoto(loadImage(file));        
       
        CheckPassword checkPassword = new CheckPassword(newPassword, retriedNewPassword);
        if(checkPassword.check())
             user.setPassword(newPassword);
         
        userDAO.save(user);
        return "redirect:/user/"+user.getId();
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form")
    public String createForm(Model model ){
        User user = new User();
        model.addAttribute("user", user);
        return "save";
    }
    
    @GetMapping("/{id}/photo")
    @ResponseBody
    public byte[] photo(@PathVariable("id")Long id){
        
        User u = userDAO.findById(id);
        return userDAO.findById(id).getPhoto();
    }
    
    private byte[] loadImage(Part file){
        if(file != null){
            byte[] photo = null;
            try {
                InputStream is = file.getInputStream();
                photo = IOUtils.toByteArray(is);
                return photo;
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
    }
    private void addMessageToModel(Model model,String messageType,String messageTitle,Locale locale){
         model.addAttribute("message", 
                new Message(messageType,messageSource.getMessage(messageTitle, 
                             new Object[]{}, 
                             locale)
                )    
            );
    }
    private void addMessageToRedirectAttr(RedirectAttributes redirectAttributes,String messageType,String messageTitle,Locale locale){
        
          redirectAttributes.addFlashAttribute("message", 
                new Message(messageType,messageSource.getMessage(messageTitle,
                            new Object[]{}, 
                            locale)
        ));
    }
}
