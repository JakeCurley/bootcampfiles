/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.ui;

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
        
        int answer = 0;
        boolean valid = false;
        System.out.println(prompt);
        while(!valid) {
            String sAnswer = sc.nextLine();
            try {
                answer = Integer.parseInt(sAnswer);
                valid = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                valid = false;
            }
        }
        return answer;
    }
    
    @Override
    public int readInt(String prompt, int min, int max){
        int answer = 0;
        boolean valid = false;
        while(!valid) {
            System.out.println(prompt);
            String sAnswer = sc.nextLine();
            try {
                answer = Integer.parseInt(sAnswer);
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number 1-6.");
                valid = false;
            }
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
        boolean valid = false;
        System.out.println(prompt);
        String answer = "";
        while (!valid) {
           answer = sc.nextLine();

           if (answer.contains(",")) {
               System.out.println("Please enter an input without a comma.");
               valid = false;
           }
           else {
               valid = true;
           }
        } 
        return answer;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
        System.out.println(prompt);
        boolean valid = false;
        while (!valid) {
                String answer = sc.nextLine();
                try {
                    BigDecimal bAnswer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP);
                    if (bAnswer.compareTo(min) < 0) {
                        valid = false;
                        System.out.println("Please enter a positive value");
                    }
                    else {
                        System.out.println("Please enter a positive value.");
                        return bAnswer;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number: ");
                }
        }
        return null;
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

                date = releaseMonth +  releaseDay  + releaseYear;

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
                LocalDate ld = LocalDate.parse(date, dateFormat);
                return ld;
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid input.  Please enter input in the following format - MM - dd - yyyy");
                runAgain = true;
            }
        } while (runAgain);
        
        return null;
    }
}
