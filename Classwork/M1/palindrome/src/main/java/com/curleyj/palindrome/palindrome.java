package com.curleyj.palindrome;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String input = sc.nextLine();
        
        boolean palindrome = isPalindrome(input);
        System.out.println(palindrome);
        
    }
    
    public static boolean isPalindrome(String input) {
         boolean isEqual = false;
        char[] characters = input.toCharArray();
        for (int i=0; i < characters.length-1; i++) {
           for (int j = characters.length-1; j > 0; j--) {
                if (characters[i] != characters[j]) {
                    System.out.println(i);
                    System.out.println(j);
                    System.out.println(isEqual);
                    isEqual = false;
                }
                else {
                    isEqual = true;
                }
           }
      
        }
        return isEqual;
    }
}
