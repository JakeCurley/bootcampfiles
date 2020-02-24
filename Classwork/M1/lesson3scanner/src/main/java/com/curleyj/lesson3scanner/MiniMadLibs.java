package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String noun, adjective, noun2, adjective2, noun3, noun4, noun5, verb, verb2, stringNum;
        int num;
        
        System.out.println("Let's play MAD LIBS!\n");
        
        System.out.println("I need a noun: ");
        noun = myScanner.nextLine();
        System.out.println("Now an adjective: ");
        adjective = myScanner.nextLine();
        System.out.println("Another noun: ");
        noun2 = myScanner.nextLine();
        System.out.println("And a number: ");
        stringNum = myScanner.nextLine();
        num = Integer.parseInt(stringNum);
        System.out.println("Another adjective: ");
        adjective2 = myScanner.nextLine();
        System.out.println("A plural noun: ");
        noun3 = myScanner.nextLine();
        System.out.println("Another one: ");
        noun4 = myScanner.nextLine();
        System.out.println("One more: ");
        noun5 = myScanner.nextLine();
        System.out.println("A verb (infinitive form): ");
        verb = myScanner.nextLine();
        System.out.println("Same verb (past participle): ");
        verb2 = myScanner.nextLine();
        
        System.out.println("*** NOW LET'S GET MAD (libs) ***");
        System.out.println(noun + ": the " + adjective + " frontier. These are the voyages of the starship " + noun2 + ".  It's " + num + " year mission: to explore strange " + adjective2 + " " + noun3 + ", to seek out " + adjective2 + " " + noun4 + " and " + adjective2 + " " + noun4 + ", to boldly " + verb + " where no one has " + verb2 + " before.");
        
        
        
    }
}
