/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dao;

/**
 *
 * @author Jake
 */
public class DvDLibraryDaoException extends Exception{

    public DvDLibraryDaoException(String message) {
        super(message);
    }
    
    public DvDLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
