package com.curleyj.leson7forloops;

/**
 *
 * @author Jake
 */
public class ForByFor {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.print("|");

            for (int j = 0; j < 1; j++) {

                for (int m = 0; m < 3; m++) {
                    System.out.print("*");
                }
                System.out.print("|");
                for (int l = 0; l < 3; l++) {
                    System.out.print("$");
                }
                System.out.print("|");
                for (int n = 0; n < 3; n++) {
                    System.out.print("*");
                }
                System.out.print("|");
            }
            System.out.println("");
        }
        for (int a = 0; a < 1; a++) {
            System.out.print("|");

            for (int b = 0; b < 1; b++) {

                for (int c = 0; c < 3; c++) {
                    System.out.print("@");
                }
                System.out.print("|");
                for (int d = 0; d < 3; d++) {
                    System.out.print("#");
                }
                System.out.print("|");
                for (int e = 0; e < 3; e++) {
                    System.out.print("@");
                }
                System.out.print("|");
            }
            System.out.println("");
        }
        for (int f = 0; f < 1; f++) {
            System.out.print("|");

            for (int g = 0; g < 1; g++) {

                for (int h = 0; h < 3; h++) {
                    System.out.print("*");
                }
                System.out.print("|");
                for (int o = 0; o < 3; o++) {
                    System.out.print("$");
                }
                System.out.print("|");
                for (int p = 0; p < 3; p++) {
                    System.out.print("*");
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
