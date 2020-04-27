/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.Dao;

import com.curleyj.guessinggame.App;
import com.curleyj.guessinggame.dto.game;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
public class gameDaoDBTest {
    
    @Autowired
    gameDaoDB gameDaoDB;
    
    @Autowired
    JdbcTemplate jdbc;
    
    public gameDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<game> gameList = gameDaoDB.getAll();
        
        for (game game : gameList) {
            final String DELETE = "DELETE FROM guess WHERE gameID = ?";
            jdbc.update(DELETE, game.getGameID());
            
            final String DELETE_GAME = "DELETE FROM game WHERE gameID = ?";
            jdbc.update(DELETE_GAME, game.getGameID());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of add method, of class gameDaoDB.
     */
    @Test //@org.junit.jupiter.api.Test
    public void testAddGetById() {
        game game = gameDaoDB.add("1234");
        
        game fromDao = gameDaoDB.gameById(game.getGameID());
        
        assertEquals(game, fromDao);
        
        try {
        game game2 = gameDaoDB.add("12345");
        fail("Should have thrown exception");
        } catch (Exception e) {
            return;
        }
        
        game fromDao3 = gameDaoDB.gameById(500);
        assertNull(fromDao3);
    }

    /**
     * Test of getAll method, of class gameDaoDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetAll() {
        game game1 = gameDaoDB.add("1234");
        game game2 = gameDaoDB.add("5678");
        
        List<game> gameList = gameDaoDB.getAll();
        
        assertEquals(game1.getAnswer(), "1234");
        assertEquals(game2.getAnswer(), "5678");
        
        assertEquals(gameList.size(), 2);
    }

    /**
     * Test of update method, of class gameDaoDB.
     */
    @org.junit.jupiter.api.Test
    public void testUpdate() {
        //Changes isFinished
        game game = gameDaoDB.add("1234");
        game.setFinished(true);
        
        assertEquals(game.isFinished(), true);
        
        gameDaoDB.update(false, game.getGameID());
        
        game = gameDaoDB.gameById(game.getGameID());
        
        assertEquals(game.isFinished(), false);
        
    }
    
}
