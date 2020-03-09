/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dao;

import com.curleyj.dvdlibrary.dto.dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Jake
 */
public class DvDLibraryDaoFileImpl implements DvDLibraryDao {
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    LocalDate ld = LocalDate.now();
    
    private Map<String, dvd> dvds = new HashMap<>();
    
    @Override
    public dvd addDvd(String title, dvd dvd) throws DvDLibraryDaoException {
        
        loadRoster();
        dvd newDvd = dvds.put(title, dvd);
        writeRoster();
        return newDvd;
    }

    @Override
    public List<dvd> getAllDvds() throws DvDLibraryDaoException {
        loadRoster();
        return new ArrayList<dvd>(dvds.values());
    }

    @Override
    public dvd listDvdInfo(String title) {
        
        dvd currentDvd = dvds.get(title);
        return currentDvd;
    }

    @Override
    public dvd removeDvd(String title) throws DvDLibraryDaoException {
        loadRoster();
        dvd removedDvd = dvds.remove(title);
        writeRoster();
        return removedDvd;
    }

    @Override
    public dvd editDvd(String title, String editChoice, String edit) throws DvDLibraryDaoException {
        loadRoster();
        dvd editDvd = dvds.get(title);
            
        switch (editChoice) {
            case "1":
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                editDvd.setld(LocalDate.parse(edit, dateFormat));
                break;
            case "2":
                editDvd.setRating(edit);
                break;
            case "3":
                editDvd.setDirectorName(edit);
                break;
            case "4":
                editDvd.setStudio(edit);
                break;
            case "5":
                editDvd.setUserRating(edit);
        }
         
        writeRoster();
        return editDvd;
    }
    
    private dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        
        String title = dvdTokens[0];
        
        dvd dvdFromFile = new dvd(title);
        LocalDate ld = LocalDate.parse(dvdTokens[1]);
        dvdFromFile.setld(ld);
        dvdFromFile.setRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
        
        return dvdFromFile;
    }
    
    private void loadRoster() throws DvDLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }
        catch (FileNotFoundException e) {
            throw new DvDLibraryDaoException("-_- Could not load roster data into memory.", e);
        }
        
        String currentLine;
        
        dvd currentDvd;
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        
        scanner.close();
        
    }
    
    private String marshallDvd(dvd aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getld() + DELIMITER;
        dvdAsText += aDvd.getRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating() + DELIMITER;
        
        return dvdAsText;
    }
    
    public void writeRoster() throws DvDLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        catch (IOException e) {
            throw new DvDLibraryDaoException("Could not save DvD data.", e);
        }
        
        String dvdAsText;
        List<dvd> dvdList = this.getAllDvds();
        for (dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public boolean titleCheck(String title) throws DvDLibraryDaoException {
        loadRoster();
        boolean titleTest = false;
        if (dvds.containsKey(title)) {
            titleTest = true;
        }
        return titleTest;
    }
    
    @Override
    public List<dvd> getDvdLastNYears(int years) throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .filter(d -> d.getDvdAge() < years)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<dvd> getDvdByRating(String rating) throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .filter(d -> d.getRating().equalsIgnoreCase(rating))
                .collect(Collectors.toList());
    }
    @Override
    public Map<String, List<dvd>> getDvdByDirector(String director) throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .filter(d -> d.getDirectorName().equalsIgnoreCase(director))
                .collect(Collectors.groupingBy(dvd::getRating));
    }
    
    @Override
    public List<dvd> getDvdByStudio(String studio) throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .filter(d -> d.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }
    
    @Override
    public double getAverageDvdAge() throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .mapToLong(s -> s.getDvdAge())
                .average()
                .getAsDouble();
    }
    
    @Override
    public String getNewestDvd() throws DvDLibraryDaoException {
        loadRoster();
        return dvds.values()
                .stream()
                .mapToLong(s -> s.getDvdAge())
                .min()
                .toString();
    }
    
    
    
    
}
