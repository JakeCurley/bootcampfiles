/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.service;

import com.curleyj.guessinggame.Dao.gameDaoDB;
import com.curleyj.guessinggame.Dao.guessDaoDB;
import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jake
 */
@Component
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    private final gameDaoDB gameDaoDB;
    @Autowired
    private final guessDaoDB guessDaoDB;

    public ServiceLayerImpl(gameDaoDB gameDaoDB, guessDaoDB guessDaoDB) {
        this.gameDaoDB = gameDaoDB;
        this.guessDaoDB = guessDaoDB;
    }

    @Override
    public String generateAnswer() {
        Random rand = new Random();
        int firstDigit = 0;
        int secondDigit = 0;
        int thirdDigit = 0;
        int fourthDigit = 0;
        boolean same = true;
        do {
            firstDigit = rand.nextInt(10);
            secondDigit = rand.nextInt(10);
            thirdDigit = rand.nextInt(10);
            fourthDigit = rand.nextInt(10);
            if (firstDigit != secondDigit && firstDigit != thirdDigit
                    && firstDigit != fourthDigit && secondDigit != thirdDigit
                    && secondDigit != fourthDigit && thirdDigit != fourthDigit) {
                same = false;
            }
        } while (same);
        String answer = Integer.toString(firstDigit) + Integer.toString(secondDigit) + Integer.toString(thirdDigit) + Integer.toString(fourthDigit);

        return answer;
    }

    @Override
    public List<game> getAll() {
        return gameDaoDB.getAll();
    }

    @Override
    public game add() {
        String answer = generateAnswer();
        return gameDaoDB.add(answer);
    }

    @Override
    public game gameById(int id) {
        return gameDaoDB.gameById(id);
    }

    @Override
    public List<guess> getAllGuessesById(int id) {
        return guessDaoDB.getAll(id);
    }

    @Override
    public guess validateAnswer(int gameID, String guess) {
        boolean correct = false;
        String answer = "";
        game game = new game();
        try {
            game = gameDaoDB.gameById(gameID);
            answer = game.getAnswer();
        } catch (NullPointerException e) {
            return null;
        }
        int exact = 0;
        int partial = 0;
        
        if (!guess.matches("[0-9]{4}")) {
            return null;
        }

        String answer1 = answer.substring(0, 1);
        String answer2 = answer.substring(1, 2);
        String answer3 = answer.substring(2, 3);
        String answer4 = answer.substring(3);

        String guess1 = guess.substring(0, 1);
        String guess2 = guess.substring(1, 2);
        String guess3 = guess.substring(2, 3);
        String guess4 = guess.substring(3);

        if (guess1.equals(answer1)) {
            exact++;
        } else if (guess1.equals(answer2) || guess1.equals(answer3) || guess1.equals(answer4)) {
            partial++;
        }

        if (guess2.equals(answer2)) {
            exact++;
        } else if (guess2.equals(answer1) || guess2.equals(answer3) || guess2.equals(answer4)) {
            partial++;
        }

        if (guess3.equals(answer3)) {
            exact++;
        } else if (guess3.equals(answer1) || guess3.equals(answer2) || guess3.equals(answer4)) {
            partial++;
        }

        if (guess4.equals(answer4)) {
            exact++;
        } else if (guess4.equals(answer1) || guess4.equals(answer2) || guess4.equals(answer3)) {
            partial++;
        }

        if (exact == 4) {
            boolean isFinished = true;
            correct = true;
            gameDaoDB.update(isFinished, gameID);
        }
        try {
            guess newGuess = guessDaoDB.add(game, guess, exact, partial, correct);
            return newGuess;
        } catch (Exception e) {
            return null;
        }
    }
}
