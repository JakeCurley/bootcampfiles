package com.curleyj.fourinarow;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class FourInARow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random newR = new Random();
        int choice = 0;
        String sChoice = "";
        System.out.println("Player #1, please enter your name: ");
        String playerOne = sc.nextLine();
        System.out.println("Hello, " + playerOne);
        
        System.out.println("Player #2, enter your name: ");
        String playerTwo = sc.nextLine();
        System.out.println("Hello, " + playerTwo);
        
        
        
        String[][] board = new String[6][7];
        for (int k = 0; k < 6; k++) {
            for (int l = 0; l < 7; l++) {
                board[k][l] = "-";
            }
        }
        boolean gameOver = true;
        boolean firstTurn = newR.nextBoolean();
        
        do {
        if (firstTurn) {
            System.out.println("It's " + playerOne + "'s turn.");
            //method to print board
            System.out.println(playerOne + ", choose a column: ");
            sChoice = sc.nextLine();
            choice = Integer.parseInt(sChoice);
            firstTurn = false;
        }
        else {
            System.out.println("It's " + playerTwo + "'s turn.");
            System.out.println(playerOne + ", choose a column: ");
            sChoice = sc.nextLine();
            choice = Integer.parseInt(sChoice);
            board = addToBoard(board[][], choice);
            firstTurn = true;
        }
            
        } while (gameOver);
        
        

        
    }
    
    public static String[][] addToBoard(String[][] board, int choice) {
        
        for (int i = 5; i > 0; i--) {
            if (board[i][choice].equals("x")) {
                continue;
            }
            else {
                board[i][choice] = "x";
                break;
            }   
        }
        
        return board;
    }
    
    public static void printBoard(String[][] board) {
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 7; b++) {
                System.out.print(board[a][b] + "");
            }
            System.out.println();
        }
    }
}
