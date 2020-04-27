/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.service;

import com.curleyj.guessinggame.App;
import com.curleyj.guessinggame.Dao.gameDaoDB;
import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jake
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ServiceLayerImplTest {
    
    @Autowired
    gameDaoDB gameDaoDB;

    
    @Autowired
    ServiceLayerImpl service;
    
    public ServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of generateAnswer method, of class ServiceLayerImpl.
     */
    @Test
    public void testGenerateAnswer() {
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
        
        assertEquals(answer.length(), 4);
        assertNotEquals(firstDigit, secondDigit);
        assertNotEquals(firstDigit, thirdDigit);
        assertNotEquals(firstDigit, fourthDigit);
        assertNotEquals(secondDigit, thirdDigit);
        assertNotEquals(secondDigit, fourthDigit);
        assertNotEquals(thirdDigit, fourthDigit);
    }

    /**
     * Test of validateAnswer method, of class ServiceLayerImpl.
     */
    @Test
    public void testValidateAnswer() {
        game game = gameDaoDB.add("1234");
        
        String stringGuess = "5678";
        guess guess = service.validateAnswer(game.getGameID(), stringGuess);
        assertEquals(guess.getPart(), 0);
        assertEquals(guess.getExact(), 0);
        assertFalse(guess.isCorrectGuess());
        game = gameDaoDB.gameById(game.getGameID());
        assertFalse(game.isFinished());
        
        String stringGuess2 = "4567";
        guess guess2 = service.validateAnswer(game.getGameID(), stringGuess2);
        assertEquals(guess2.getPart(), 1);
        assertEquals(guess2.getExact(), 0);
        assertFalse(guess2.isCorrectGuess());
        game = gameDaoDB.gameById(game.getGameID());
        assertFalse(game.isFinished());
        
        String stringGuess3 = "1235";
        guess guess3 = service.validateAnswer(game.getGameID(), stringGuess3);
        assertEquals(guess3.getPart(), 0);
        assertEquals(guess3.getExact(), 3);
        assertFalse(guess3.isCorrectGuess());
        game = gameDaoDB.gameById(game.getGameID());
        assertFalse(game.isFinished());
        
        String stringGuess4 = "1234";
        guess guess4 = service.validateAnswer(game.getGameID(), stringGuess4);
        assertEquals(guess4.getPart(), 0);
        assertEquals(guess4.getExact(), 4);
        assertTrue(guess4.isCorrectGuess());
        game = gameDaoDB.gameById(game.getGameID());
        assertTrue(game.isFinished());
        
    }
    
}
