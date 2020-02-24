package com.curleyj.fourinarow;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class FourInARow {
    public static void main(String[] args) {
        boolean playAgain = false;
        do {
            Scanner sc = new Scanner(System.in);
            Random newR = new Random();
            int choice = 0;
            int turnCount = 0;
            boolean valid = false;
            String answer = "";
            System.out.println("Player #1, please enter your name: ");    
            String playerOne = sc.nextLine();                               //Set playerOne to input name
            System.out.println("Hello, " + playerOne);

            System.out.println("Player #2, enter your name: ");
            String playerTwo = sc.nextLine();                               //Set playerTwo to input name
            System.out.println("Hello, " + playerTwo);



            String[][] board = new String[7][8];                            //Initialize board
            for (int k = 0; k < 7; k++) {                                   //Set all elements to "-"
                for (int l = 0; l < 8; l++) {
                    board[k][l] = "-";
                    board[0][l] = Integer.toString(l+1);
                }
            }

            boolean winner = false;                                        //Game runs until this value turns to true
            boolean firstTurn = newR.nextBoolean();                        //Randomizes who goes first - then iterates between the two players based off that

            do {                                                           //Game loop - runs until there is a winner
            if (firstTurn) {                                               //playerOne goes
                System.out.println("It's " + playerOne + "'s turn.");
                System.out.println(playerOne + ", choose a column: ");
                choice = sc.nextInt()- 1;
                do {
                    if (choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
                        valid = true;
                    } 
                    else {
                        valid = false;
                        System.out.println("Your answer must be 1-7 - Choose again.");
                        choice = sc.nextInt() - 1;
                    }
                } while (!valid);
                
                firstTurn = false;                                         //Changes firstTurn so that playerTwo goes next
                turnCount++;
            }
            else if (!firstTurn && !winner) {
                System.out.println("It's " + playerTwo + "'s turn.");      //playerTwo goes
                System.out.println(playerTwo + ", choose a column: ");
                choice = sc.nextInt() - 1;
                do {
                    if (choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
                        valid = true;
                    } 
                    else {
                        valid = false;
                        System.out.println("Your answer must be 1-7 - Choose again.");
                        choice = sc.nextInt() - 1;
                    }
                } while (!valid);
                firstTurn = true;                                          //Changes firstTurn so that playerOne goes next
                turnCount++;
            }
            addToBoard(board, choice, firstTurn);                         //Inputs "x" or "o" into chosen column based on which player's turn it is
            printBoard(board);                                            //Prints out the board after each choice
            winner = testWinner(board, firstTurn);                        //Test board - if either player has 4 in a row it sets winner to true and ends the loop

            
            if (turnCount == 42) {
                System.out.println("Draw!!");
                System.out.println("Would you like to play again? (y/n)");
                answer = sc.nextLine();
                if (answer.equals("y")) {
                winner = true;
                playAgain = true;
            }
               
                
            }
            } while (!winner);

            if (winner && !playAgain) {
                System.out.println("Game over! Winner!");
                System.out.println("Would you like to play again? (y/n)");
                answer = sc.nextLine();
            }
            
            if (answer.equals("y")) {
                playAgain = true;
            }
            
        } while (playAgain);
        
    }
    
    public static String[][] addToBoard(String[][] board, int choice, boolean firstTurn) {
        Scanner sc = new Scanner(System.in);
        boolean correct = false;
        do {
            for (int i = 7; i > 0; i--) {
                if (i == 1) {
                    System.out.println("Column is full - make another choice.");
                    choice = sc.nextInt() - 1;
                    correct = true;
                    break;
                }
                if (board[i-1][choice].equals("x") || board[i-1][choice].equals("o")) {
                    correct = false;
                    continue;
                }
                else {
                    if (firstTurn) {
                        board[i-1][choice] = "o";
                        correct = false;
                        break;
                    }
                    else {
                        board[i-1][choice] = "x";
                        correct = false;
                        break;
                    }

                }   
            }
        } while(correct);
        return board;
    }
    
    public static void printBoard(String[][] board) {
        for (int a = 0; a < 7; a++) {
            for (int b = 0; b < 7; b++) {
                System.out.print(board[a][b] + " ");
            }
            System.out.println();
        }
    }
    
    public static boolean testWinner(String[][] board, boolean player) {
        boolean winner = false;
        int diagonal = 0;
        int diagonalTwo = 0;
        int vertical = 0;
        int verticalTwo = 0;
        int horizontal = 0;
        int horizontalTwo = 0;
        for (int a = 6;a > 0; a--) {
            for (int b = 0; b < 7; b++) {
                if (board[a][b].equals("o")) {
                        if (board[a][b].equals(board[a-1][b])) {
                            verticalTwo++;                          
                        }
                        else {
                            verticalTwo = 0;
                        }
                        if (board[a][b].equals(board[a][b+1])) {
                            horizontalTwo++;
                        }
                        else {
                            horizontalTwo = 0;
                        }
                        if (board[a][b].equals(board[a-1][b+1])) {
                            diagonalTwo++;
                        }
                        else {
                            diagonalTwo = 0;
                        }
                        if (verticalTwo == 3 || horizontalTwo == 3 || diagonalTwo == 3) {
                            winner = true;
                        }
                }
                
                if (board[a][b].equals("x")) {
                        if (board[a][b].equals(board[a-1][b])) {
                            vertical++;                          
                        }
                        else {
                            vertical = 0;
                        }
                        if (board[a][b].equals(board[a][b+1])) {
                            horizontal++;
                        }
                        else {
                            horizontal = 0;
                        }
                        if (board[a][b].equals(board[a-1][b+1])) {
                            diagonal++;
                        }
                        else {
                           diagonal = 0;
                        }
                            
                        if (vertical == 3 || horizontal == 3 || diagonal == 3) {
                            winner = true;
                        }
                    }
                }
                
            }
       
        return winner;
    }
}
