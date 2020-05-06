/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dao;

import com.curleyj.dvdlibrary.dto.dvd;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

/**
 *
 * @author Jake
 */
public interface DvDLibraryDao {
    dvd addDvd (String title, dvd dvd) throws DvDLibraryDaoException;
    
    List<dvd> getAllDvds() throws DvDLibraryDaoException;
    
    dvd listDvdInfo(String title);
    
    dvd removeDvd(String title) throws DvDLibraryDaoException;
    
    dvd editDvd(String dvd, String editChoice, String edit) throws DvDLibraryDaoException;

    boolean titleCheck(String title) throws DvDLibraryDaoException;
    
    List<dvd> getDvdLastNYears(int years) throws DvDLibraryDaoException;
    
    List<dvd> getDvdByRating(String rating) throws DvDLibraryDaoException;
     
    Map<String, List<dvd>> getDvdByDirector(String director) throws DvDLibraryDaoException;
    
    List<dvd> getDvdByStudio(String studio) throws DvDLibraryDaoException;
    
    double getAverageDvdAge() throws DvDLibraryDaoException;
    
    String getNewestDvd() throws DvDLibraryDaoException ;
}
