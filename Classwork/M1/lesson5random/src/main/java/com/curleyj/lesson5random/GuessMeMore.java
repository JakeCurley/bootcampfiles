package com.curleyj.lesson5random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class GuessMeMore {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int num = randomizer.nextInt(201) - 100;
        
        System.out.println("I've chosen a number.  Betcha can't guess it!");
        Scanner myScanner = new Scanner(System.in);
        String stringGuess = myScanner.nextLine();
        
        int guess = Integer.parseInt(stringGuess);
        
        System.out.println("Your guess:" + guess);
        if (guess == num) {
            System.out.println("Wow, nice guess!  That was it!");
        }
        
        if (guess < num) {
            System.out.println("Ha, nice try - too low!  I chose " + num);
        }
        
        if (guess > num) {
            System.out.println("To bad, way to high.  I chose " + num);
        }
    }
}
