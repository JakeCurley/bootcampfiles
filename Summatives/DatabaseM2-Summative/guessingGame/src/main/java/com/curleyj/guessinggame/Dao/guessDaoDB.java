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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jake
 */
@Repository
public class guessDaoDB implements guessDao {
    
    private final JdbcTemplate jdbc;
    
    public guessDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    public static final class guessMapper implements RowMapper<guess> {
        @Override
        public guess mapRow(ResultSet rs, int index) throws SQLException {
            guess guess = new guess();
            guess.setGuessId(rs.getInt("guessID"));
            guess.setGuess(rs.getString("guess"));
            guess.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            guess.setExact(rs.getInt("exact"));
            guess.setPart(rs.getInt("part"));
            guess.setCorrectGuess(rs.getBoolean("correctGuess"));
            return guess;
        }
    }

    @Override
    public guess add(game game, String guess, int exact, int partial, boolean correct) {
        guess newGuess = new guess();
        newGuess.setGameId(game.getGameID());
        newGuess.setGuess(guess);
        newGuess.setExact(exact);
        newGuess.setPart(partial);
        newGuess.setGuessTime(LocalDateTime.now());
        newGuess.setCorrectGuess(correct);
        final String sql = "INSERT INTO guess (guess, guessTime, part, exact, correctGuess, gameID) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, newGuess.getGuess(), Timestamp.valueOf(newGuess.getGuessTime()), newGuess.getPart(), newGuess.getExact(), newGuess.isCorrectGuess(), newGuess.getGameId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        newGuess.setGuessId(newId);
        
        return newGuess;
    }

    @Override  //Return all guesses for a given gameID
    public List<guess> getAll(int id) {
        final String sql = "SELECT * FROM guess JOIN game ON guess.gameID = game.gameID WHERE game.gameID = ?";
        return jdbc.query(sql, new guessMapper(), id);
    }
}
