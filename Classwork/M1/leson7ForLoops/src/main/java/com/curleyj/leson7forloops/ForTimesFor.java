package com.curleyj.leson7forloops;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class ForTimesFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What times table shall I recite? ");
        String sNum = sc.nextLine();
        int num = Integer.parseInt(sNum);
        int points = 0;
        
        for (int i = 1; i <= 15; i++) {
            System.out.println(i + " * " + num + " is: ");
            String sAnswer = sc.nextLine();
            int answer  = Integer.parseInt(sAnswer);
            
            if (answer == (i * num)) {
                System.out.println("Correct!");
                points++;
            }
            
            else {
                System.out.println("Sorry no, the answer is: " + i*num);
            }
        }
        if (points > 13) {
            System.out.println("Congratulations!  You got over 90%");
        }
        else if (points < 8) {
            System.out.println("You need to study more.");
        }
                
        System.out.println("You got " + points + " correct");
    }
}
