package com.curleyj.leson7forloops;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class ForTimes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What times table shall I recite? ");
        String sNum = sc.nextLine();
        int num = Integer.parseInt(sNum);
        
        for (int i = 1; i <= 15; i++) {
            System.out.println(i + " * " + num + " is: " + i * num);
        }
    }
}
