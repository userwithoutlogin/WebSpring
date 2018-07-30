/*+
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.dao;

import com.mycompany.webspring.entity.Role;
import java.util.List;
 

/**
 *
 * @author root
 */
public interface RolesDAO extends DAO<Role>{
     public Role findByRoleName(String roleName);
     public List<Role> findByRoleName1(String roleName);
     public List<Role> findSecTest(String uName);
}
