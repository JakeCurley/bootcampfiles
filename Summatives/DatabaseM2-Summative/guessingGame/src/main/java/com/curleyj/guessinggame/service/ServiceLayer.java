/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.service;

import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface ServiceLayer {
    String generateAnswer();
    List<game> getAll();
    game add();
    game gameById(int id);
    guess validateAnswer(int gameID, String guess);
    List<guess> getAllGuessesById(int id);
}
