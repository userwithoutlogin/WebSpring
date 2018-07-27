/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.dynamicSQL;

import com.mycompany.webspring.entity.Role;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @author root
 */
public class DynamicSQL {
    public String saveUser(){
        return new SQL(){{
            INSERT_INTO("users");
            INTO_COLUMNS("first_name","last_name","user_name","password","birth_date");
            INTO_VALUES("#{firstName},#{lastName},#{userName},#{password},#{birthDate}");
        }}.toString();
    }
    public String updateRole(final Role role){
        return new SQL(){
            {
                UPDATE("roles");
                if(role.getRoleName()!=null) SET("role_name=#{roleName}");
                if(role.getEnabled()!=null) SET("enabled=#{enabled}");
            }
        }.toString();
    }
}
