/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.ui;

import com.curleyj.dvdlibrary.dto.dvd;
import java.util.List;

/**
 *
 * @author Jake
 */
public class DvDLibraryView {
    private UserIO io;
    
    public DvDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List all DVDs");
        io.print("5. DVD Info by title");
        io.print("6. Exit");
            
        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public dvd getNewDvdInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String rating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Enter your rating and additional comments");
        
        dvd newDvd = new dvd(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setRating(rating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserRating(userRating);
        
        return newDvd;
    }
    
    public void displayAddDvDBanner() {
        io.print("=== Add DvD ===");
    }
    
    public void displayAddSuccessBanner() {
        io.readString("DvD added successfully.  Please hit enter to continue.");
    }
    
    public void listDvdInfo(dvd currentDvd) {
        io.print("Title: " + currentDvd.getTitle() + "\nRelease date: " + currentDvd.getReleaseDate() + "\nDirector: " + currentDvd.getDirectorName() + "\nStudio: " + currentDvd.getStudio() + "\nUser rating: " + currentDvd.getUserRating());
    }
    
    public String dvdTitle() {
        return io.readString("Please enter DvD title");
    }
    
    public void displayDvdInfoBanner() {
        io.print("=== DvD Information ===");
    }
    
    public void listAllDvds(List<dvd> dvdList) {
        for( dvd currentDvd : dvdList) {
        io.print(currentDvd.getTitle() + "\n");
        }
        
        io.readString("Please hit enter to continue,");
    }
    
    public void displayListDvdsBanner() {
        io.print("=== List all DvDs ===");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove DvD ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("DvD removed successfully.  Please hit enter to continue.");
    }
    
    public void displayEditDvdBanner() {
        io.print("=== Edit DvD ===");
    }
    
    public void displayEditDvDSuccessBanner() {
        io.readString("DvD edited successfully.  Please hit enter to continue.");
    }
    
    public int editDvdChoice() {
        io.print("What would you like to edit?");
        io.print("1. Release Date\n");
        io.print("2. Rating\n");
        io.print("3. Director\n");
        io.print("4. Studio\n");
        io.print("5. User Rating\n");
        
        return io.readInt("Please choose from the above choices", 1, 5);
    }
    
    public String editDvd() {
       String edit = io.readString("Please enter the new information");
        
        return edit;
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
