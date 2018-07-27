/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author root
 */
@Controller
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {
    @GetMapping(value="/list")
    public String list(){
        return "list";
    }
    @GetMapping(value="/{id}")
    public String show(@PathVariable("id") Long id){
        return "show";
    }
    
    @GetMapping(params = "form")
    public String create(){
        return "save";
    }
    @GetMapping(value="/{id}",params = "form")
    public String edit(){
        return "edit";
    }
//    @GetMapping(value="/{id}",params = "form")
//    public String edit(){
//        return "edit";
//    }
}
