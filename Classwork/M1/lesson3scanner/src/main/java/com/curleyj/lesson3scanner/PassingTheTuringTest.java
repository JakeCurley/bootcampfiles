package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class PassingTheTuringTest {
    public static void main(String[] args) {
        String userName;
        String userColor;
        String userFood;
        String stringNumber;
        int userNumber;
        int total;
        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("What is your name?");
        userName = myScanner.nextLine();
        System.out.println("Hi, " + userName + " what is your faovorite color?");
        userColor = myScanner.nextLine();
        System.out.println("Huh, " + userColor + "? Mine's Electric Lime.\n");
        System.out.println("I really like limes.  They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + userName + "?");
        userFood = myScanner.nextLine();
        System.out.println("Really? " + userFood + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        stringNumber = myScanner.nextLine();
        userNumber = Integer.parseInt(stringNumber);
        
        total = userNumber * -7;
        System.out.println(userNumber + " is a cool number.  Mine's -7.");
        System.out.println("Did you know " + userNumber + " * -7 is " + total + "?  That's a cool number too!");
        System.out.println("Well, thanks for talking to me, " + userName);
    }
}
