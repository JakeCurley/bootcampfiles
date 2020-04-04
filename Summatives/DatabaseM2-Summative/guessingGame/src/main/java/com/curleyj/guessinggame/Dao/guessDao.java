/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.Dao;

import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface guessDao {
    guess add(game game, String guess, int exact, int partial, boolean correct);
    List<guess> getAll(int id);
    guess guessById(int id);  
    void insertGame(game game, guess guess);
}
