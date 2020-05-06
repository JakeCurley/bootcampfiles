package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static final class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int index) throws SQLException {
            Genre genre = new Genre("");
            genre.setGenreName(rs.getString("genreName"));
            return genre;
        }
    }

    @Override
    public void add(Genre genre) {
        int i = 0;
        final String SELECT_ALL_GENRE = "SELECT * FROM genre";
        List<Genre> genres = jdbc.query(SELECT_ALL_GENRE, new GenreMapper());
        for (Genre g : genres) {
            if (g.getGenreName().equals(genre.getGenreName())) {
                i++;
            }
        }
        if (i == 0) {
            final String INSERT_GENRE = "INSERT INTO genre VALUE(?)";
            jdbc.update(INSERT_GENRE, genre.getGenreName());
        }
        
    }
    
}
