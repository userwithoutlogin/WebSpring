/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.utils;

/**
 *
 * @author root
 */
public class CheckPassword {
     private String oldPassword;
     private String retriedOldPassword;
     private String newPassword;
     private String retriedNewPassword;

    public CheckPassword(String newPassword, String retriedNewPassword) {
        this.newPassword = newPassword;
        this.retriedNewPassword = retriedNewPassword;
    }

    public CheckPassword(String oldPassword, String retriedOldPassword, String newPassword, String retriedNewPassword) {
        this.oldPassword = oldPassword;
        this.retriedOldPassword = retriedOldPassword;
        this.newPassword = newPassword;
        this.retriedNewPassword = retriedNewPassword;
    }

     
     public boolean check(){
         boolean validOldPassword = false;
         boolean validNewPassword = false;
         if( retriedOldPassword!=null)
             if(!retriedOldPassword.equals(""))
             validOldPassword =  checkOldPass();
         if((!retriedNewPassword.equals(""))&(!newPassword.equals("")))
             validNewPassword = checkNewPass();
         return validNewPassword | validOldPassword;
     }
     public boolean checkOldPass(){
         return (oldPassword.equals(retriedOldPassword ));
     }
     public boolean checkNewPass(){
         return (newPassword.equals(retriedNewPassword ));
     }
     
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRetriedNewPassword() {
        return retriedNewPassword;
    }

    public void setRetriedNewPassword(String retriedNewPassword) {
        this.retriedNewPassword = retriedNewPassword;
    }

     
     
     

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getRetriedOldPassword() {
        return retriedOldPassword;
    }

    public void setRetriedOldPassword(String retriedOldPassword) {
        this.retriedOldPassword = retriedOldPassword;
    }
    
}
