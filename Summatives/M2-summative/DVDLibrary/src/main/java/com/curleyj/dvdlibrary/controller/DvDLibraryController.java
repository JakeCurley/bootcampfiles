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

import java.util.List;

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
        int menuSelection = 0;
        
        try {
            while (keepGoing) {
            
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listAllDvds();
                        break;
                    case 5:
                        listDvdInfo();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
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
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDvd() throws DvDLibraryDaoException {
        view.displayAddDvDBanner();
        dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayAddSuccessBanner();
    }
    
    private void listDvdInfo() throws DvDLibraryDaoException {
        view.displayDvdInfoBanner();
        String title = view.dvdTitle();
        dvd currentDvd = dao.listDvdInfo(title);
        view.listDvdInfo(currentDvd);
    }
    
    private void listAllDvds() throws DvDLibraryDaoException {
        view.displayListDvdsBanner();
        List<dvd> dvdList = dao.getAllDvds();
        view.listAllDvds(dvdList);
    }
    
    private void removeDvd() throws DvDLibraryDaoException {
        view.displayRemoveDvdBanner();
        String currentDvd = view.dvdTitle();
        dao.removeDvd(currentDvd);
        view.displayRemoveSuccessBanner();
    }
    
    private dvd editDvd() throws DvDLibraryDaoException {
        view.displayEditDvdBanner();
        String currentDvd = view.dvdTitle();
        int editChoice = view.editDvdChoice();
        String edit = view.editDvd();
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
}
