/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.dao;

import com.mycompany.webspring.entity.User;
import java.util.List;

/**
 *
 * @author root
 */
public interface DAO<T> {
     public List<T> findAll();
       
    public T findById(Long id);    
    public void save(T entity);
    public void update(T user);
    public void delete(T user);
}
