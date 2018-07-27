/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.service;

import com.mycompany.webspring.entity.Role;
import com.mycompany.webspring.entity.User;
import com.mycompany.webspring.mappers.UserRoleMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository("userRoleService")
public class UserRoleService {
    @Resource(name = "userRoleMapper")
    UserRoleMapper  mapper;
    
    public List<User> findAllUsers(){
        return mapper.findAllUsers();
    }
    
     
    public List<Role> findAllRoles(){
        return mapper.findAllRoles();
    }
      
    public List<Role> findAllRolesOfUser(Long id){
        return mapper.findAllRolesOfUser(id);
    }
    
     
    public List<User> findAllUsersWithRole(Long id){
        return mapper.findAllUsersWithRole(id);
    }
    public void saveUser(User user){
        mapper.saveUser(user);
    }
    public void saveRole(Role role){
        mapper.saveRole(role);
    }
    
    public void updateUser(User user){
        mapper.updateUser(user);
    }
    public void updateRole(Role role){
        mapper.updateRole(role);
    }
    public void deleteUser(User user){
       mapper.deleteUser(user);
    }
    public void deleteUserById(Long id){
        mapper.deleteUserById(id);
    }
    
    public void deleteRole(Role role){
       mapper.deleteRole(role);
    }
    public void deleteRoleById(Long id){
        mapper.deleteRoleById(id);
    }
}
