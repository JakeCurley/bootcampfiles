/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.Dao;

import com.curleyj.guessinggame.App;
import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jake
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class guessDaoDBTest {
    
    @Autowired
    guessDaoDB guessDaoDB;
    
    @Autowired
    gameDaoDB gameDaoDB;
    
    @Autowired
    JdbcTemplate jdbc;
    
    
    public guessDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<game> gameList = gameDaoDB.getAll();
        for (game game : gameList) {
            final String DELETE = "DELETE FROM guess WHERE gameID = ?";
            jdbc.update(DELETE, game.getGameID());
            
            final String DELETE_GAME = "DELETE from game WHERE gameID = ?";
            jdbc.update(DELETE_GAME, game.getGameID());
        }      
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class guessDaoDB.
     */
    @Test
    public void testAddGetAll() {
        
        game game = gameDaoDB.add("1234");
        game game2 = gameDaoDB.add("5678");
        
        guess guess = guessDaoDB.add(game, "5678", 0, 0, false);
        guess guess2 = guessDaoDB.add(game, "8657", 0, 0, false);
        guess guess3 = guessDaoDB.add(game2, "1234", 0, 0, false);
        guess guess4 = guessDaoDB.add(game2, "5678", 0, 0, true);
        
        List<guess> fromGame = guessDaoDB.getAll(game.getGameID());
        List<guess> fromGame2 = guessDaoDB.getAll(game2.getGameID());
        
        assertEquals(fromGame.size(), 2);
        
        assertNotEquals(fromGame, fromGame2);

        try {
            guess guess5 = guessDaoDB.add(game, "12345", 0, 0, false);
            fail("Should have thrown exception");
        } catch (Exception e) {
            return;
        }
        
    }
}
