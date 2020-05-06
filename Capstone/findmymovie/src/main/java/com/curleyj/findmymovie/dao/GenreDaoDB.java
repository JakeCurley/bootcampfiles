package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jake
 */

@Repository
public class GenreDaoDB implements GenreDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class genreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int index) throws SQLException {
            Genre genre = new Genre("");
            genre.setGenreName(rs.getString("genreName"));
            genre.setGenreID(rs.getInt("genreID"));
            genre.setUserName(rs.getString("userName"));
            return genre;
        }
    }
    
    public static final class genreCountMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int index) throws SQLException {
            Genre genre = new Genre("");
            genre.setGenreName(rs.getString("genreName"));
            genre.setGenreCount(rs.getInt("g"));
            genre.setUserName(rs.getString("userName"));
            return genre;
        }
    }

    @Override
    public void add(Genre genre) {
        final String INSERT_GENRE = "INSERT INTO genre (genreName, userName) VALUE(?,?)";
        jdbc.update(INSERT_GENRE, genre.getGenreName(), genre.getUserName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        genre.setGenreID(newId);
        
    }
    
    @Override
    public List<Genre> getAllGenres(Movie movie) {
        final String GET_ALL_GENRES = "SELECT genreName, userName, COUNT(genreName) g FROM genre WHERE userName = ? " +
                                           "GROUP BY genreName ORDER BY g DESC";
        
        List<Genre> genres = jdbc.query(GET_ALL_GENRES, new genreCountMapper(), movie.getUserName());
        return genres;
    }

    
}
