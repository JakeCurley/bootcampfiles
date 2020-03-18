package com.curleyj.leson7forloops;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class TheCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        
        System.out.println("I LOVE TO COUNT!  LET ME SHARE MY COUNTING WITH YOU!");
        
        System.out.println("Start at: ");
        String sStart = sc.nextLine();
        int start = Integer.parseInt(sStart);
        
        System.out.println("Count by: ");
        String sInc = sc.nextLine();
        int inc = Integer.parseInt(sInc);
        
        System.out.println("Stop at: ");
        String sEnd = sc.nextLine();
        int end = Integer.parseInt(sEnd);
        
        for (int i = start; i < end; i += inc) {
            
            if (counter < 3) {
            System.out.print(i + " ");
            counter++;
            }
            else {
                System.out.println("ah ah ah!");
                counter = 0;
            }
        }
    }
 
}
