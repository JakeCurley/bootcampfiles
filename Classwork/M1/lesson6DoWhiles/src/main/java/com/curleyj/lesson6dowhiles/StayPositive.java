package com.curleyj.lesson6dowhiles;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class StayPositive {
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("What number should I count down from?");
        String sNum = myScanner.nextLine();
        int num = Integer.parseInt(sNum);
        int line = 1;
        
        System.out.println("Here goes!\n");
        while (num >= 0) {
            
            if (line < 10) {
                System.out.print(num + "  ");
            }
            
            else if (line == 10) {
                    System.out.println(num); 
                    line = 0;
                 }
        line++;
        num--;
        }
        System.out.println("\nWhew, better stop there...!");
        
    }
}
