package com.curleyj.lesson6dowhiles;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class GuessMeFinally {
    public static void main(String[] args) {
         Random randomizer = new Random();
        int num = randomizer.nextInt(201) - 100;
        int i = 0;
        String stringGuess;
        System.out.println("I've chosen a number between -100 and 100.  Betcha can't guess it!");
        Scanner myScanner = new Scanner(System.in);
        
        
        while (i < 1){
            stringGuess = myScanner.nextLine();       
            int guess = Integer.parseInt(stringGuess);
            int guessNum = 1;
            
            if (guess == num && guessNum == 1) {
                System.out.println("Wow, nice guess!  That was it!");
                i = 1;
            }
            
            if (guess == num && guessNum > 1) {
                System.out.println("Finally, it's about time you got it!");
                i = 1;
            }

            if (guess < num) {
                System.out.println("Ha, nice try - too low!  Try again!");
                System.out.println("Your guess:" + guess);
                i = 1;
            }

            if (guess > num) {
                System.out.println("To bad, way to high.  I chose " + num);
                System.out.println("Your guess:" + guess);
                i = 1;
            }
        }
    }
}
