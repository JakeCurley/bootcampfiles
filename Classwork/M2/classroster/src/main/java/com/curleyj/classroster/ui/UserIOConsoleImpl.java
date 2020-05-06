/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.classroster.ui;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class UserIOConsoleImpl implements UserIO {
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
            
            if ((answer <= max) && (answer >= min)){
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
}
