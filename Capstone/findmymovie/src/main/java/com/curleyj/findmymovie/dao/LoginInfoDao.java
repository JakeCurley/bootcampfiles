/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.LoginInfo;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface LoginInfoDao {
    LoginInfo register(LoginInfo li);
    LoginInfo verifyLoginInfo(LoginInfo li);
    void deleteAccount(LoginInfo li);
    List<LoginInfo> getAllAccounts();
}
