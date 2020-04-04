package com.curleyj.lesson5random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class HighRoller {
    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("How many sides does the die have? ");
        String sSides = myScanner.nextLine();
        int sides = Integer.parseInt(sSides);
        
        int rollResult = diceRoller.nextInt(sides) + 1;
        
        System.out.println("TIME TO ROLL THE DICE!");
        System.out.println("I rolled a " + rollResult);
        
        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        }
        
        if (rollResult == (sides)) {
            System.out.println("You rolled a critical!  Nice job!");
        }
    }
}
