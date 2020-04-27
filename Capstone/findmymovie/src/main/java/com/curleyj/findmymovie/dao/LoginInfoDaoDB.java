/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.LoginInfo;
import com.curleyj.findmymovie.security.PassEnc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jake
 */

@Repository
public class LoginInfoDaoDB implements LoginInfoDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class loginMapper implements RowMapper<LoginInfo> {
        @Override
        public LoginInfo mapRow(ResultSet rs, int index) throws SQLException {
            LoginInfo li = new LoginInfo();
            li.setUserName(rs.getString("userName"));
            li.setEnc(rs.getString("enc"));
            li.setSalt(rs.getString("salt"));
            return li;
        }
    }
    
    @Override
    public LoginInfo register(LoginInfo li) {
        final String SELECT_ALL_LOGINS = "SELECT * FROM login";
        final String INSERT_USER = "INSERT INTO login (userName, enc, salt) VALUES (?,?,?) ";
        
        List<LoginInfo> logins = jdbc.query(SELECT_ALL_LOGINS, new loginMapper());
        try {
            for (LoginInfo l : logins) {
                if (l.getUserName().equals(li.getUserName())) {
                    System.out.println("works");
                    return null;
                }
            }
        } catch(NullPointerException e) {}
        
        String salt = PassEnc.getSalt(30);
        String encPass = PassEnc.generateSecurePassword(li.getPassword(), salt);
        
        jdbc.update(INSERT_USER, li.getUserName(), encPass, salt);
        
        return li;
    }
    
    @Override
    public LoginInfo verifyLoginInfo(LoginInfo li) {
        
        final String VERIFY = "SELECT * FROM login WHERE userName = ?";
        LoginInfo newLi = jdbc.queryForObject(VERIFY, new loginMapper(), li.getUserName());   
        System.out.println(newLi.getEnc()+ " " + newLi.getSalt());
        boolean passwordMatch = PassEnc.verifyUserPassword(li.getPassword(), newLi.getEnc(), newLi.getSalt());       
        if(passwordMatch) 
        {
            System.out.println("Provided user password " + li.getPassword() + " is correct.");
            newLi.setPassword(li.getPassword());
            return newLi;
        } else {
            System.out.println("Provided password is incorrect");
        }
        
        return null;
    }
    
    @Override
    public void deleteAccount(LoginInfo li) {
        final String DELETE_ACCOUNT = "DELETE FROM login WHERE userName = ?";
        jdbc.update(DELETE_ACCOUNT, li.getUserName());
    }
    
    @Override
    public List<LoginInfo> getAllAccounts() {
        final String GET_ALL_ACCOUNTS = "SELECT * FROM login";
        List<LoginInfo> logins = jdbc.query(GET_ALL_ACCOUNTS, new loginMapper());
        return logins;
    }
}
