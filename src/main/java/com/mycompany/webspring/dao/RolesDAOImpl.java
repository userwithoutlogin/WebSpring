/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.dao;

import com.mycompany.webspring.entity.Role;
import java.util.List;
import javax.annotation.Resource;
 
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository("rolesDAO")
public class RolesDAOImpl implements RolesDAO{
    @Resource(name = "sqlSession")
    private SqlSession session;

    @Override
    public List<Role> findAll() {
      return session.selectList("roles.findAll");
    }

    @Override
    public Role findById(Long id) {
     return session.selectOne("roles.findById");
    }

    @Override
    public void save(Role role) {
        session.insert("roles.save", role);
         }

    @Override
    public void update(Role role) {
        session.update("roles.update", role);
       }

    @Override
    public void delete(Role role) {
        session.delete("role.delete",role);
    }

    @Override
    public Role findByRoleName(String roleName) {
       return session.selectOne("roles.findByRoleName",roleName);  
    }
    
    public List<Role> findByRoleName1(String roleName) {
       return session.selectList("roles.findByRoleName",roleName);  
    }

    @Override
    public List<Role> findSecTest(String uName) {
        return session.selectList("roles.findSecTest", uName);
    }
    
    
}
