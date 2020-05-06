/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.ui;

import com.curleyj.dvdlibrary.dto.dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

/**
 *re
 * @author Jake
 */
public class DvDLibraryView {
    private UserIO io;
    
    public DvDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public String printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List all DVDs");
        io.print("5. DVD Info by title");
        io.print("6. DVDs in the last X years");
        io.print("7. Find DVDs by rating");
        io.print("8. Find DVDs by director");
        io.print("9. Find DVDs by studio");
        io.print("10. Average age of DVDs in collection");
        io.print("11. Find the newest DVD");
        io.print("10. Exit");
            
        return io.readString("Please select from the above choices.");
    }
    
    public dvd getNewDvdInfo() {
        boolean runAgain = true;
        String title = io.readString("Please enter DVD title");
        //String releaseYear = io.readString("Please enter release year");
        //String releaseMonth = io.readString("Please enter release month");
        //String releaseDay = io.readString("Please enter release day");
        //String date = releaseMonth + "/" + releaseDay + "/" + releaseYear;
        //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        dvd newDvd = new dvd(title);
        
        do {
          try { 
            LocalDate inputDate = io.readLocalDate("Please enter the release year");
            newDvd.setld(inputDate);
            break;
          }
          catch (DateTimeParseException e) {
              io.print("That is not a valid date.");
          }
        } while (runAgain);
        
        String rating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Enter your rating and additional comments");
        
        
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
        io.readString("DvD added successfully.  Please hit 1 to continue.");
    }
    
    public void listDvdInfo(dvd currentDvd) {
        io.print("Title: " + currentDvd.getTitle() + "\nRelease date: " + currentDvd.getld() + "\nRating: " + currentDvd.getRating() + "\nDirector: " + currentDvd.getDirectorName() + "\nStudio: " + currentDvd.getStudio() + "\nUser rating: " + currentDvd.getUserRating());
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
        
        io.readString("Please enter 1 to continue,");
    }
    
    public void displayListDvdsBanner() {
        io.print("=== List all DvDs ===");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove DvD ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("DvD removed successfully.  Please enter 1 to continue.");
    }
    
    public void displayEditDvdBanner() {
        io.print("=== Edit DvD ===");
    }
    
    public void displayEditDvDSuccessBanner() {
        io.readString("DvD edited successfully.  Please enter 1 to continue.");
    }
    
    public String editDvdChoice() {
        boolean valid = false;
        io.print("What would you like to edit?");
        io.print("1. Release Date\n");
        io.print("2. Rating\n");
        io.print("3. Director\n");
        io.print("4. Studio\n");
        io.print("5. User Rating\n");
        
        String choice = io.readString("Please choose from the above choices");
        while (!valid) {
            if (choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")) {
                valid = true;
            }
            else {
                choice = io.readString("Please choose only from the above choices.");
            }
       }
        return choice;
    }
    
    public String editDvd(String editChoice) {
        
        if (editChoice.equals("1")) {
        String releaseYear = io.readString("Enter a new release year");
        String releaseMonth = io.readString("Enter a new release month");
        String releaseDay = io.readString("Enter a new release day");
        
        return releaseMonth + "/" + releaseDay + "/" + releaseYear;
        }
        
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
    
    public void displaylistDvdsLastNYears() {
        io.print("=== DVDs released in the last X years ===");
    }
    
    public String listDvdsLastNYears() {
       return io.readString("Enter a year to list all DvDs released since then.");
    }
    
    public void displaySuccessDvdsLastNYears(int years, List<dvd> newList) {
        io.print("=== DVDs released in the last " + years + " years. ===");
        for(dvd currentDvd : newList) {
        io.print(currentDvd.getTitle() + "\n");
        }
    }
    
    public String dvdsByRating() {
        return io.readString("Please enter a rating to sort by: ");
    }
    
    public void displaySuccessDvdByRating(String rating, List<dvd> newList) {
        io.print("Listing all DVDs with the rating of " + rating + ": ");
        for(dvd currentDvd : newList) {
        io.print(currentDvd.getTitle() + "\n");
        }
    }
    
    public String dvdsByDirector() {
        return io.readString("Please enter a director name: ");
    }
    
    public void displaySuccessDvdByDirector(String director, Map<String, List<dvd>> newMap) {
        io.print("Listing all DVDs directed by: " + director);
        Set<String> directors = newMap.keySet();
        
        directors.stream()
                .forEach(director2 -> { System.out.println("====================");
                                       newMap.get(director2).stream().forEach(s -> System.out.println(s.getRating()));
                                       newMap.get(director2).stream().forEach(s -> System.out.println(s.getTitle()));
                                       System.out.println("====================");
                });
    }
    
    public String dvdsByStudio() {
        return io.readString("Please enter a studio to search by: ");
    }
    
    public void displayDvdsByStudioSuccess(String studio, List<dvd> newList) {
        io.print("=== Listing all DVDs made by: " + studio + " ===");
        for(dvd currentDvd : newList) {
        io.print(currentDvd.getTitle() + "\n");
        }
    }
    
    public void displayAverageAge(double age) {
        io.print("=== Average age of DVDs in collection ===");
        io.print("Average age: " + age + " years");
    }
    
    public void getNewestDvd(OptionalLong oL) {
        io.print("The newest dvd is " + oL);
    }
}
