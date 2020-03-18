package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String userName;
        String userYear;
        int year;
        
        System.out.println("Hey, let's play a game!  What's your name?");
        userName = myScanner.nextLine();
        
        System.out.println("Ok, " + userName + ", when were you born?");
        userYear = myScanner.nextLine();
        year = Integer.parseInt(userYear);
        
        System.out.println("Well " + userName + "...");
        if (year < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out half a decade ago?");
            if(year < 1995) {
                System.out.println("And that the first Harry Potter came out over 15 years ago!");
                if (year < 1985) {
                    System.out.println("Also, Space Jam came out not last decade, but the one before THAT.");
                }
            }
        }
        
        
        
    }
    
}
