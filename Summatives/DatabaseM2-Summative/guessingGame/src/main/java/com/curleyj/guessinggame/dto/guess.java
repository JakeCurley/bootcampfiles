/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

/**
 *
 * @author Jake
 */
public class guess {
    @JsonIgnore
    int guessId;
    String guess;
    LocalDateTime guessTime;
    boolean correctGuess;
    int part;
    int exact;
    @JsonIgnore
    int gameId;

    public int getGuessId() {
        return guessId;
    }

    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getExact() {
        return exact;
    }

    public void setExact(int exact) {
        this.exact = exact;
    }

    public boolean isCorrectGuess() {
        return correctGuess;
    }

    public void setCorrectGuess(boolean correctGuess) {
        this.correctGuess = correctGuess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    
    
}
