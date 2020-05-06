/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.controller;

import com.curleyj.dvdlibrary.dao.DvDLibraryDao;
import com.curleyj.dvdlibrary.dao.DvDLibraryDaoException;
import com.curleyj.dvdlibrary.dto.dvd;
import com.curleyj.dvdlibrary.ui.DvDLibraryView;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

/**
 *
 * @author Jake
 */
public class DvDLibraryController {
    DvDLibraryView view;
    DvDLibraryDao dao;
    
    public DvDLibraryController (DvDLibraryDao dao, DvDLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        String menuSelection = "";
        
        try {
            while (keepGoing) {
            
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case "1":
                        addDvd();
                        break;
                    case "2":
                        removeDvd();
                        break;
                    case "3":
                        editDvd();
                        break;
                    case "4":
                        listAllDvds();
                        break;
                    case "5":
                        listDvdInfo();
                        break;
                    case "6":
                        getDvdsLastNYears();
                        break;
                    case "7":
                        getDvdsByRating();
                        break;
                    case "8":
                        getDvdsByDirector();
                        break;
                    case "9":
                        getDvdByStudio();
                        break;
                    case "10":
                        getAverageDvdAge();
                        break;
                    case "11":
                        getNewestDvd();
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }
        catch (DvDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private String getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDvd() throws DvDLibraryDaoException {
        view.displayAddDvDBanner();
        dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayAddSuccessBanner();
    }
    
    private void listDvdInfo() throws DvDLibraryDaoException {
        boolean titleTest = true;
        view.displayDvdInfoBanner();
        String title = "";
        while(titleTest) {
            title = view.dvdTitle();
            titleTest = dao.titleCheck(title);
            if (!titleTest) {
                view.displayErrorMessage("That is not a title.  Please re-input now.");
                titleTest = true;
            }
            else {
                break;
            }
        }
        dvd currentDvd = dao.listDvdInfo(title);
        view.listDvdInfo(currentDvd);
    }
    
    private void listAllDvds() throws DvDLibraryDaoException {
        view.displayListDvdsBanner();
        List<dvd> dvdList = dao.getAllDvds();
        view.listAllDvds(dvdList);
    }
    
    private void removeDvd() throws DvDLibraryDaoException {
        boolean titleTest = true;
        String title = "";
        view.displayRemoveDvdBanner();
        while(titleTest) {
            String currentDvd = view.dvdTitle();
            titleTest = dao.titleCheck(currentDvd);
            if (!titleTest) {
                view.displayErrorMessage("That is not a title.  Please re-input now.");
                titleTest = true;
            }
            else {
                dao.removeDvd(currentDvd);
                break;
            }
        }
        
        view.displayRemoveSuccessBanner();
    }
    
    private dvd editDvd() throws DvDLibraryDaoException {
        boolean titleTest = true;
        view.displayEditDvdBanner();
        String currentDvd = "";
        
        while(titleTest) {
            currentDvd = view.dvdTitle();
            titleTest = dao.titleCheck(currentDvd);
            if (!titleTest) {
                view.displayErrorMessage("That is not a title.  Please re-input now.");
                titleTest = true;
            }
            else {
                titleTest = false;
            }
        }
        String editChoice = view.editDvdChoice();
        String edit = view.editDvd(editChoice);
        dvd editDvd = dao.editDvd(currentDvd, editChoice, edit);
        view.displayEditDvDSuccessBanner();
        return editDvd;
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void getDvdsLastNYears() throws DvDLibraryDaoException {
       view.displaylistDvdsLastNYears();
       String sYear = view.listDvdsLastNYears();
       int year = Integer.parseInt(sYear);
       dao.getDvdLastNYears(year);
       List<dvd> newList = dao.getDvdLastNYears(year);
       view.displaySuccessDvdsLastNYears(year, newList); 
    }
    
    private void getDvdsByRating() throws DvDLibraryDaoException {
        String rating = view.dvdsByRating();
        List<dvd> newList = dao.getDvdByRating(rating);
        view.displaySuccessDvdByRating(rating, newList);
    }
    
    private void getDvdsByDirector() throws DvDLibraryDaoException {
        String director = view.dvdsByDirector();
        Map<String, List<dvd>> dvdMap = dao.getDvdByDirector(director);
        view.displaySuccessDvdByDirector(director, dvdMap);
    }
    
    private void getDvdByStudio() throws DvDLibraryDaoException {
        String studio = view.dvdsByStudio();
        List<dvd> newList = dao.getDvdByStudio(studio);
        view.displayDvdsByStudioSuccess(studio, newList);
    }
    
    private void getAverageDvdAge() throws DvDLibraryDaoException {
        Double age = dao.getAverageDvdAge();
        view.displayAverageAge(age);
    }
    
    private void getNewestDvd() throws DvDLibraryDaoException {
       String dvdTitle =  dao.getNewestDvd();
       
       view.getNewestDvd(dvdTitle);
    }
    
}
