package com.curleyj.lesson9arrays;

/**
 *
 * @author Jake
 */
public class FruitSalad {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
    
        String[] fruitSalad = new String[12];
        int x = 0;
        int y = 0;
        int z = 0;
        int r = 0;
        
        for (int i = 0; i < fruit.length && x < fruitSalad.length; i++) {
            if (fruit[i].contains("berry") && x < 12) {
                fruitSalad[x] = fruit[i];
                x++;
            }
            else if (fruit[i].contains("Apple") && y <= 3 && x < 12) {
                fruitSalad[x] = fruit[i];
                y++;
                x++;
                
            }
            else if (fruit[i].contains("Orange") && z <= 2 && x < 12) {
                fruitSalad[x] = fruit[i];
                z++;
                x++;
                
            }
            else if (y<=3 && z <= 2 && x < 12 && !fruit[i].contains("Tomato")) {
                fruitSalad[x] = fruit[i];
                x++;
            }
        }
        
        for (int l = 0; l < fruitSalad.length; l++) {
            System.out.println(fruitSalad[l]);
        }
    }
}
