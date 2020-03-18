package com.curleyj.lesson4ifelse;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox?");
        
        String action = userInput.nextLine();
        
        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in here");
            System.out.println("Look inside or stick your hand in? ");
            action = userInput.nextLine();
            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark.  So ... so very dark.");
                System.out.println("Run away or keep looking? ");
                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue");
                }
                else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields looking very foolish.");
                    System.out.println("But you're alive.  Possibly a wise choice.");
                }
                
            }
            else if (action.equals("stick your hand in")) {
                System.out.println("You feel something inside...");
                System.out.println("Grab it or pull your hand out? ");
                action = userInput.nextLine();
                if (action.equals("Grab it")) {
                    System.out.println("You've been eaten by a grue");
                }
                else if (action.equals("pull your hand out")) {
                    System.out.println("A grue follows your hand out and eats you.");
                }
            }

        }
         else if (action.equals("go to the house")) {
                System.out.println("The house is very dark");
                System.out.println("Go inside or run away? ");
                action = userInput.nextLine();
                
                if (action.equals("Go inside")){
                    System.out.println("The house looks very nice");
                    System.out.println("Congratulations on the free house!");
                }
                else if (action.equals("run away")) {
                    System.out.println("Probably the right call.. but what if?");
                }
                    
        }
    }
}
