package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class TriviaNight {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String sAnswer1, sAnswer2, sAnswer3;
        int answer1, answer2, answer3;
        int total = 0;
        
        System.out.println("It's TRIVIA NIGHT!  Are you ready?!");
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code\t 2)Assembly Language");
        System.out.println("3) C#\t 4)Machine Code");
        sAnswer1 = myScanner.nextLine();
        System.out.println("YOUR ANSWER: " + sAnswer1);
        answer1 = Integer.parseInt(sAnswer1);
        
        System.out.println("SECOND QUESTION");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work Of?");
        System.out.println("1) Grace Hopper\t 2) Alan Turing");
        System.out.println("3) Charles Babbage\t 4) Larry Page");
        sAnswer2 = myScanner.nextLine();
        System.out.println("YOUR ANSWER: " + sAnswer2);
        answer2 = Integer.parseInt(sAnswer2);
        
        System.out.println("LAST QUESTION");
        System.out.println("Which of these sci-fi ships was once slated for a full-size replica in Las Vegas?");
        System.out.println("1) Serenity\t 2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise\t 4) The Millennium Falcon");
        sAnswer3 = myScanner.nextLine();
        System.out.println("YOUR ANSWER: " + sAnswer3);
        answer3 = Integer.parseInt(sAnswer3);
        
        
        if (answer1 == 4) {
            total += 1;
        }
        if (answer2 == 2) {
            total += 1;
        }
        if (answer3 == 3) {
            total += 1;
        }
        
        if (total == 3) {
            System.out.println("Congratulations!  You got them all correct!");
        }
        else if (total == 0) {
            System.out.println("You got them all wrong!");
        }
        else{
            System.out.println("Nice job - you got " + total + " correct!");
        }
    }
}
