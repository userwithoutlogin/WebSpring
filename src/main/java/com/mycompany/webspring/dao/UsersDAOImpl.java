/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.dao;

import com.mycompany.webspring.entity.User;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository("usersDAO")
public class UsersDAOImpl implements UsersDAO{
    @Resource(name = "sqlSession")
    SqlSession session;
    
    
    @Override
    public List<User> findAll(){
        return session.selectList("users.findAll");
    }
    @Override
    public User findByUserName(String userName){
        return session.selectOne("users.findByUserName",userName);
    } 
    @Override
    public User findById(Long id) {
      return  session.selectOne("users.findById",id);
    }
    @Override
    public void save(User user){
        session.insert("users.save", user);
    } 
    @Override
    public void update(User user){
        session.update("users.update",user);
    }
    @Override
    public void delete(User user){
        session.delete("users.delete",user);
    }

     
}
