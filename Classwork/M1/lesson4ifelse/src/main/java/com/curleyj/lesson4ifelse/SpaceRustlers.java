package com.curleyj.lesson4ifelse;

/**
 *
 * @author Jake
 */
public class SpaceRustlers {
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;
        
        if (aliens > spaceships) {
            System.out.println("Vrroom, vroom!  Let's get going!");
        }
        else {
            System.out.println("There aren't enough green guys to drive these ships!");
        }
        
        if (cows == spaceships) {
            System.out.println("Wow, way to plan ahead!  JUST enough room for all these walking hamburgers!");
        }
        else if (cows > spaceships) {
            System.out.println("Dangit! I don't know how we're going to fit all these cows in here!");
        }
        else {
            System.out.println("Too many ships!  Not enough cows."); //Removing this makes it possible for neither part of the if/else statement to run
        }
        
        if (aliens > cows) {
            System.out.println("Hurrah, we've got the grub!  Hamburger party on Alpha Centauri!");
        }
        else {
            System.out.println("Oh no!  The herds got restless and took over!  Looks like _we're_ the hamburger now!!");
    }   
    
}
}
