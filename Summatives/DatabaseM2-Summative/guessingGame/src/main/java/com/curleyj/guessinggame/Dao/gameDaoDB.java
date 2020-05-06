/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.Dao;

import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.guess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jake
 */
@Repository
public class gameDaoDB implements gameDao {
    
    private final JdbcTemplate jdbc;    
    @Autowired
    public gameDaoDB (JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    private static final class gameMapper implements RowMapper<game> {
        @Override
        public game mapRow(ResultSet rs, int index) throws SQLException {
            game game = new game();
            game.setGameID(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("isFinished"));
            return game;
        }
    }

    @Override
    public game add(String answer) {
        game game = new game();
        game.setAnswer(answer);
        game.setFinished(false);
        final String sql = "INSERT INTO game (answer, isFinished) VALUES (?, ?)";
        jdbc.update(sql, game.getAnswer(), game.isFinished());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameID(newId);
        return game;
    }

    @Override
    public List<game> getAll() {
        final String sql = "SELECT game.gameID, answer, isFinished FROM game";
        return jdbc.query(sql, new gameMapper());
    }

    @Override
    public game gameById(int id) {
        try {
        final String sql = "SELECT * FROM game WHERE gameID = ?";
        game game = jdbc.queryForObject(sql, new gameMapper(), id);
        return game;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public void update(boolean isFinished, int id) {
        final String GET_GAME = "SELECT * FROM game WHERE gameID = ?";
        game newGame = jdbc.queryForObject(GET_GAME, new gameMapper(), id);
        final String sql = "UPDATE game SET isFinished = ? WHERE gameID = ?";
        jdbc.update(sql, isFinished, newGame.getGameID());
    }
    
}
