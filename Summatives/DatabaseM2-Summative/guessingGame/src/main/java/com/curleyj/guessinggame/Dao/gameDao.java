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
public interface gameDao {
    game add(String answer);
    List<game> getAll();
    game gameById(int id);
    void update(boolean isFinished, int id);
}
