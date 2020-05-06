/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.guessId;
        hash = 29 * hash + Objects.hashCode(this.guess);
        hash = 29 * hash + Objects.hashCode(this.guessTime);
        hash = 29 * hash + (this.correctGuess ? 1 : 0);
        hash = 29 * hash + this.part;
        hash = 29 * hash + this.exact;
        hash = 29 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final guess other = (guess) obj;
        if (this.guessId != other.guessId) {
            return false;
        }
        if (this.correctGuess != other.correctGuess) {
            return false;
        }
        if (this.part != other.part) {
            return false;
        }
        if (this.exact != other.exact) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }
    
    
    
}
