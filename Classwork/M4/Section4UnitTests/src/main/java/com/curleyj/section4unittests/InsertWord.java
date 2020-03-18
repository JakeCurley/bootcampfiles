/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section4unittests;

/**
 *
 * @author Jake
 */
public class InsertWord {
        // Given an "out" String length 4, such as "<<>>", and a 
    // word, return a new String where the word is in the middle 
    // of the out String, e.g. "<<word>>".
    //
    // Hint: SubStrings are your friend here 
    //
    // insertWord("<<>>", "Yay") -> "<<Yay>>"
    // insertWord("<<>>", "WooHoo") -> "<<WooHoo>>"
    // insertWord("[[]]", "word") -> "[[word]]"
    public String insertWord(String container, String word) {

           String cont1 = container.substring(0, 2);
           String cont2 = container.substring(2, 4);
           
           return cont1+word+cont2;
    }

}
