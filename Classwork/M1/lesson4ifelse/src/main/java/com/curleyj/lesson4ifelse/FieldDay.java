package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class FieldDay {
    public static void main(String[] args) {
        String Baggins = "Baggins";
        String Dresden = "Dresden";
        String Howl = "Howl";
        String Potter = "Potter";
        String Vimes = "Vimes";
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("What's your last name? ");
        String name = myScanner.nextLine();
        
        
        if (name.compareTo(Baggins) < 0) {
            System.out.println("Aha You're on the team 'Red Dragons'");
            System.out.println("Good luck in the games!");
        }
        
        else if (name.compareTo(Baggins) > 0 && name.compareTo(Dresden) < 0) {
            System.out.println("Aha You're on the team 'Dark Wizards'");
            System.out.println("Good luck in the games!");
        }
        else if (name.compareTo(Dresden) > 0 && name.compareTo(Howl) < 0) {
            System.out.println("Aha You're on the team 'Moving Castles'");
            System.out.println("Good luck in the games!");
        }
        else if (name.compareTo(Howl) > 0 && name.compareTo(Potter) < 0) {
            System.out.println("Aha You're on the team 'Golden Snitches'");
            System.out.println("Good luck in the games!");
        }
        else if (name.compareTo(Potter) > 0 && name.compareTo(Vimes) < 0) {
            System.out.println("Aha You're on the team 'Night Guards'");
            System.out.println("Good luck in the games!");
        }
        else {
            System.out.println("Aha You're on the team 'Black Holes'");
            System.out.println("Good luck in the games!");
        }
        
        
             
    }
}
