/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class UserIOFileImpl implements UserIO {
    Scanner sc = new Scanner(System.in);
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String sAnswer = sc.nextLine();
        double answer = Double.parseDouble(sAnswer);
        
        return answer;
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        boolean valid = false;
        String sAnswer = sc.nextLine();
        double answer = Double.parseDouble(sAnswer);
        while(!valid) {
            if ((answer < max) && (answer > min)){
                valid = true;
            }
        }
        return answer;
    }
    
    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String sAnswer = sc.nextLine();
        float answer = Float.parseFloat(sAnswer);
        
        return answer;
    }
    
    @Override
    public float readFloat(String prompt, float min, float max){
        System.out.println(prompt);
        boolean valid = false;
        String sAnswer = sc.nextLine();
        float answer = Float.parseFloat(sAnswer);
        
        while(!valid) {
            if ((answer < max) && (answer > min)){
                valid = true;
            }
        }
        return answer;
    }
    
    @Override
    public int readInt(String prompt){
        
        System.out.println(prompt);
        String sAnswer = sc.nextLine();
        int answer = Integer.parseInt(sAnswer);
        
        return answer;
    }
    
    @Override
    public int readInt(String prompt, int min, int max){
        
        String sAnswer = sc.nextLine();
        int answer = Integer.parseInt(sAnswer);
        boolean valid = false;
        while(!valid) {
            
            if ((answer < max) && (answer > min)){
                valid = true;
            }
        }
        return answer;
    }
    
    @Override
    public long readLong(String prompt){
        
        System.out.println(prompt);
        String sAnswer = sc.nextLine();
        long answer = Long.parseLong(sAnswer);
        
        return answer;
    }
    
    @Override
    public long readLong(String prompt, long min, long max){
        String sAnswer = sc.nextLine();
        long answer = Long.parseLong(sAnswer);
        boolean valid = false;
        while(!valid) {
            
            if ((answer < max) && (answer > min)){
                valid = true;
            }
        }
        return answer;
    }
    
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        String answer = sc.nextLine();
        
        return answer;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        
        String answer = sc.nextLine();
        BigDecimal bAnswer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP);
        
        return bAnswer;
    }
    
    @Override
    public LocalDate readLocalDate(String prompt) {
        String date = "";
        boolean runAgain;
        
        do {
            try {
                System.out.println(prompt);
                String releaseYear = sc.nextLine();

                System.out.println("Please enter month");
                String releaseMonth = sc.nextLine();

                System.out.println("Please enter day");
                String releaseDay = sc.nextLine();

                date = releaseMonth + releaseDay + releaseYear;

                
                runAgain = false;
            }
            catch (DateTimeParseException e) {
                runAgain = true;
            }
        } while (runAgain);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, dateFormat);
        return ld;
    }
}
