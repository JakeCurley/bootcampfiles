package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class GuessMe {
    public static void main(String[] args) {
        
        int num=4;
        
        System.out.println("I've chosen a number.  Betcha can't guess it!");
        Scanner myScanner = new Scanner(System.in);
        String stringGuess = myScanner.nextLine();
        
        int guess = Integer.parseInt(stringGuess);
        
        
        if (guess == 4) {
            System.out.println("Wow, nice guess!  That was it!");
        }
        
        if (guess < 4) {
            System.out.println("Ha, nice try - too low!  I chose " + num);
        }
        
        if (guess > 4) {
            System.out.println("To bad, way to high.  I chose " + num);
        }
    }
}
