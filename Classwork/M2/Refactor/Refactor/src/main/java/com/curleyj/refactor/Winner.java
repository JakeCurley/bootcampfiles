/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.refactor;

/**
 *
 * @author Jake
 */
class Winner {
    public boolean win(String user, int computer) {
        int userPick = 0;
        boolean winner = false;
        if (user.equalsIgnoreCase("Rock")) {
            if (computer == 2) {          //Rock beats scissors return true
                winner = true;
            }        
        }
        else if (user.equalsIgnoreCase("Paper")) {
            if (computer == 0) {         //Paper beats rock return true
                winner = true;
            }
        }
        else if (user.equalsIgnoreCase("Scissors")) {
            if (computer == 1) {
                winner = true;           //Scissors beats paper return true
            }
        }                                //Otherwise winner returns false
        return winner;    
    }
   
}
