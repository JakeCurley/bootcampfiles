
package com.curleyj.windows;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class windows {
    public static void main(String[] args) {
        float height;
        float width;
        String stringHeight;
        String stringWidth;
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;
        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the window height:");
        stringHeight = myScanner.nextLine();
        System.out.println("Please enter the window width:");
        stringWidth = myScanner.nextLine();
        
        height = Float.parseFloat(stringHeight);
        width = Float.parseFloat(stringWidth);
        
        areaOfWindow = height*width;
        perimeterOfWindow = 2 * (height + width);
        
        cost = ((3.50f * areaOfWindow) + (2.25f * perimeterOfWindow));
        
        System.out.println("Window height = " + stringHeight);
        System.out.println("Window width = " + stringWidth);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total cost = " + cost);
        
        
    }
}
