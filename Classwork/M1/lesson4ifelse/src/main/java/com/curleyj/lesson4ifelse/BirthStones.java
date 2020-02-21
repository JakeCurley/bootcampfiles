package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class BirthStones {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("What month's birthstone are you wanting to know?");
        String stringNum = myScanner.nextLine();
        int num = Integer.parseInt(stringNum);
        
        if (num == 1) {
            System.out.println("1 - January - Garnet");
        }
        else if (num == 2) {
            System.out.println("2 - February - Amethyst");
        }
        else if (num == 3) {
            System.out.println("3 - March - Aquamarine");
        }
        else if (num == 4) {
            System.out.println("4 - April - Diamond");
        }
        else if (num == 5) {
            System.out.println("5 - May - Emerald");
        }
        else if (num == 6) {
            System.out.println("6 - June - Pearl");
        }
        else if (num == 7) {
            System.out.println("7 - July - Ruby");
        }
        else if (num == 8) {
            System.out.println("8 - August - Peridot");
        }
        else if (num == 9) {
            System.out.println("9 - September - Sapphire");
        }
        else if (num == 10) {
            System.out.println("10 - October - Opal");
        }
        else if (num == 11) {
            System.out.println("11 - November - Topaz");
        }
        else if (num == 12) {
            System.out.println("12 - December = Turqoise");
        }
        else {
            System.out.println("I think you must be confused, " + num + " doesn't match a month");
        }
        
    }
}
