/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.lesson9arrays;

/**
 *
 * @author Jake
 */
public class FruitBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        
        int oranges = 0;
        int apples = 0;
        int x = 0;
        int y = 0;
        /*for (int j = 0; j < fruit.length; j++) {
            if (fruit[j].equals("Apple")) {
                apples++;
            }
            else {
                oranges++;
            }
                
        } */

          
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].equals("Apple")) {
                apples++;
                }
            else {
                oranges++;
            }
        }
        String[] aApples = new String[apples];
        String[] aOrange  =   new String[oranges];
        for (int j = 0; j < fruit.length; j++) {
            if (fruit[j].equals("Apple")) {
                aApples[x] = "Apple";
                //System.out.println(aApples[x]);
                x++;
            }
            else {
                aOrange[y] = "Orange";
                y++;
                //System.out.println(aOrange[y]);
            }
        }
        //System.out.println("There are " + apples + " apples.");
        //System.out.println("There are " + oranges + " oranges");
        
        System.out.println("Total # of fruit in basket: " + fruit.length);
        System.out.println("Number of Apples: " + aApples.length);
        System.out.println("Number of Oranges: " + aOrange.length);
        
    
    }   
}
