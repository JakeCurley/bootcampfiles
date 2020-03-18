/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.refactor;

import java.util.Random;

/**
 *
 * @author Jake
 */
class Rolls {
    public void roll(int bet) {
        Random nr = new Random();
        int rolls = 0;
        int max = 0;
        int rollsAtMax = 0;
        do {
            int die1 = nr.nextInt (6) + 1;
            int die2 = nr.nextInt(6) + 1;
            rolls++;
            
            if ((die1 + die2) == 7) {
                bet += 4;
            }
            
            else {
                bet--;
            }
            
            if (bet >= max) {
                max = bet;
                rollsAtMax = rolls;
            }
            
        } while (bet > 0);
        
        System.out.println("You are broke after " + rolls + " rolls.");
        System.out.println("You should have quit after " + rollsAtMax + " when you had $" + max);
    }
}
