/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.dao.ActorDaoDB.ActorMapper;
import com.curleyj.findmymovie.dao.GenreDaoDB.GenreMapper;
import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jake
 */

@Repository
public class MovieDaoDB implements MovieDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class movieMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int index) throws SQLException {
            Movie movie = new Movie();
            movie.setMovieID(Integer.parseInt(rs.getString("movieID")));
            movie.setImdbID(rs.getString("imdbID"));
            movie.setTitle(rs.getString("title"));
            movie.setUserScore(rs.getInt("userScore"));
            movie.setReleaseDate(rs.getString("releaseDate"));
            movie.setRating(rs.getString("rating"));
            movie.setRunTime(rs.getString("runTime"));
            movie.setDirector(rs.getString("director"));
            movie.setPlot(rs.getString("plot"));
            movie.setPoster(rs.getString("poster"));
            
            return movie;
        }
    }

    @Override
    public Movie add(Movie movie) {
        final String INSERT_MOVIE = "INSERT INTO movie (imdbID, userScore, poster, title, releaseDate, rating, runTime, director, plot) VALUES (?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_MOVIE, movie.getImdbID(), movie.getUserScore(), movie.getPoster(), movie.getTitle(), movie.getReleaseDate(), movie.getRating(), movie.getRunTime(), movie.getDirector(), movie.getPlot());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        movie.setMovieID(newId);
        //get genres and actors
        //insert into bridge tables
        
        return movie;
    }
    
    @Override
    public void addActorToMovie(Movie movie, String actorName) {
        final String SELECT_ACTOR = "SELECT * FROM actor WHERE actorName = ?";
        Actor actor = jdbc.queryForObject(SELECT_ACTOR, new ActorMapper(), actorName);
        
        final String INSERT_MOVIE_ACTOR = "INSERT INTO movieActor (movieID, actorID) VALUES (?,?)";
        jdbc.update(INSERT_MOVIE_ACTOR, movie.getMovieID(), actor.getActorID());
    }
    
    @Override
    public void addGenreToMovie(Movie movie, String genreName) {
        final String SELECT_GENRE = "SELECT * FROM genre WHERE genreName = ?";
        Genre genre = jdbc.queryForObject(SELECT_GENRE, new GenreMapper(), genreName);
        
        final String INSERT_MOVIE_GENRE = "INSERT INTO movieGenre (movieID, genreName) VALUES (?,?)";
        jdbc.update(INSERT_MOVIE_GENRE, movie.getMovieID(), genre.getGenreName());
    }

}
