/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dao;

import com.curleyj.dvdlibrary.dto.dvd;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface DvDLibraryDao {
    dvd addDvd (String title, dvd dvd) throws DvDLibraryDaoException;
    
    List<dvd> getAllDvds() throws DvDLibraryDaoException;
    
    dvd listDvdInfo(String title);
    
    dvd removeDvd(String title) throws DvDLibraryDaoException;
    
    dvd editDvd(String dvd, int editChoice, String edit) throws DvDLibraryDaoException;
    
}
