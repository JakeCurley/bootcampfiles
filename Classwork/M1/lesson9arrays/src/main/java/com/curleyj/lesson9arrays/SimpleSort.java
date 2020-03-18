package com.curleyj.lesson9arrays;

/**
 *
 * @author Jake
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};
        int [] wholeNumbers = new int[24];
        int x =12;
        int temp = 0;
        
        for (int i = 0; i < firstHalf.length; i++) {
            wholeNumbers[i] = firstHalf[i];
            wholeNumbers[x] = secondHalf[i];
            x++;
        }
        
        for (int j = 0; j < wholeNumbers.length; j++) {
            for (int k = 0; k < wholeNumbers.length-1; k++) {
                if (wholeNumbers[k] > wholeNumbers[k+1]) {
                    temp = wholeNumbers[k];
                    wholeNumbers[k] = wholeNumbers[k+1];
                    wholeNumbers[k+1] = temp;
                }
            }
        }
        for (int l = 0; l < wholeNumbers.length; l++) {
            System.out.println(wholeNumbers[l]);
        }
    }
}
