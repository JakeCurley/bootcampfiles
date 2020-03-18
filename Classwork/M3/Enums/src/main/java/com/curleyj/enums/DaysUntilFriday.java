/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.enums;

import java.util.Scanner;
import com.curleyj.enums.MathOperator;
/**
 *
 * @author Jake
 */
public class DaysUntilFriday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a day of the week.");
        String input = sc.nextLine();
        int days = 0;
        switch (input) {
            case "Monday":
                days = untilFriday(MathOperator.MONDAY, input);
                break;
            case "Tuesday":
                days = untilFriday(MathOperator.TUESDAY, input);
                break;
            case "Wednesday":
                days = untilFriday(MathOperator.WEDNESDAY, input);
                break;
            case "Thursday":
                days = untilFriday(MathOperator.THURSDAY, input);
                break;
            case "Friday":
                days = untilFriday(MathOperator.FRIDAY, input);
                break;
            case "Saturday":
                days = untilFriday(MathOperator.SATURDAY, input);
                break;
            case "Sunday":
                days = untilFriday(MathOperator.SUNDAY, input);
                    
                
        }
        
        System.out.println(days);
    }
    
    public static int untilFriday(MathOperator day, String input) {
        
        int daysUntil = 0;
        switch(day) {
            case MONDAY:
                 daysUntil = 4;
                 break;
            case TUESDAY:
                 daysUntil = 3;
                 break;
            case WEDNESDAY:
                 daysUntil = 2;
                 break;
            case THURSDAY:
                 daysUntil = 1;
                 break;
            case FRIDAY:
                 daysUntil = 0;
                 break;
            case SATURDAY:
                 daysUntil = 6;
                 break;
            case SUNDAY:
                 daysUntil = 5;
                 break;
                
        }                       
        return daysUntil;
    }
}