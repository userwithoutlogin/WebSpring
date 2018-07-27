/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webspring.mappers;

import com.mycompany.webspring.dynamicSQL.DynamicSQL;
import com.mycompany.webspring.entity.Role;
import com.mycompany.webspring.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service("userRoleMapper")
public interface UserRoleMapper {
    @Results(id="userMap",
             value = {
              @Result(column = "id",property = "id"),  
              @Result(column = "first_name",property = "firstName"),  
              @Result(column = "last_name",property = "lastName"),  
              @Result(column = "user_name",property = "userName"),  
              @Result(column = "password",property = "password"),  
              @Result(column = "birth_date",property = "birthDate",javaType = Date.class,jdbcType = JdbcType.DATE) , 
              @Result(column = "id",property = "roles",javaType = ArrayList.class,
                      many = @Many(select = "findAllRolesOfUser"))  
                     
            })
    @Select("select * from users")
    public List<User> findAllUsers();
    
    @Select({"select * from roles where id in (",
            "select role_id from user_role where user_id=#{user_id}",
            ")"})
    @ResultMap(value = "roleMap")
    public List<Role> findAllRolesOfUser(@Param("user_id")Long id);
    
    
    
    @Results(id="roleMap",
            value = {
                @Result(column = "id",property = "id"),
                @Result(column="role_name",property="roleName"),
                @Result(column = "enabled",property = "enabled",javaType = Boolean.class,
                        jdbcType = JdbcType.TINYINT),
                @Result(column = "id",property = "users",javaType = ArrayList.class,
                        many = @Many(select = "findAllUsersWithRole"))
            })    
    @Select("select * from roles")
    public List<Role> findAllRoles();
    
    
    
    
    @Select({"select * from users where id in(",
             "select user_id from user_role where role_id=#{role_id})"})
    @ResultMap(value = "userMap")
    public List<User> findAllUsersWithRole(@Param("role_id") Long id);
    
    @InsertProvider(type = DynamicSQL.class,method = "saveUser")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void saveUser(User user);
    
    @Insert({"insert into roles(role_name,enabled) values ",
            "(#{roleName},#{enabled})"
    })
    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void saveRole(Role role);
    
    @Update({
        "<script>",
         "update users ",
            "<set>",
                "<if test='#{firstName}!=null'>first_name=#{firstName}</if>",
                "<if test='#{lastName}!=null'>,last_name=#{lastName}</if>",
                "<if test='#{userName}!=null'>,user_name=#{userName}</if>",
                "<if test='#{password}!=null'>,password=#{password}</if>",
                "<if test='#{birthDate}!=null'>,birth_date=#{birthDate}</if>",
            "</set>",
            "where id=#{id}",
        "</script>"    
    })
    public void updateUser(User user);
    
    @UpdateProvider(type = DynamicSQL.class, method = "updateRole")
    public void updateRole(Role role);
    
    @Delete("delete from users where id = #{user.id}")
    public void deleteUser(@Param("user")User user);
    
    @Delete("delete from users where id = #{user.id}")
    public void deleteUserById(@Param("id")Long id);
    
    @Delete("delete from roles where id=#{role.id}")
    public void deleteRole(@Param("role")Role role);
    @Delete("delete from roles where id=#{id}")
    public void deleteRoleById(@Param("id")Long id);
} 
