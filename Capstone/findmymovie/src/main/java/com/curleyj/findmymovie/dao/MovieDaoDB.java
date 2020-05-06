/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.dao.ActorDaoDB.actorMapper;
import com.curleyj.findmymovie.dao.GenreDaoDB.genreMapper;
import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
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
public class MovieDaoDB implements MovieDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class movieMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int index) throws SQLException {
            Movie movie = new Movie();
            movie.setMovieID(rs.getInt("movieID"));
            movie.setImdbID(rs.getString("imdbID"));
            movie.setTitle(rs.getString("title"));
            movie.setUserScore(rs.getInt("userScore"));
            movie.setReleaseDate(rs.getString("releaseDate"));
            movie.setRating(rs.getString("rating"));
            movie.setRunTime(rs.getInt("runTime"));
            movie.setPlot(rs.getString("plot"));
            movie.setPoster(rs.getString("poster"));
            movie.setPopularity(rs.getString("popularity"));
            movie.setBudget(rs.getInt("budget"));
            
            return movie;
        }
    }
    
    public static final class ratingMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int index) throws SQLException {
            Movie movie = new Movie();
            movie.setRating(rs.getString("rating"));
            movie.setRatingCount(rs.getInt("r"));
            
            return movie;
        }
    }

    @Override
    public Movie add(Movie movie, String listName) {
        final String SELECT_MOVIES_IN_LIST = "SELECT * FROM movie m JOIN movieListmovie mlm ON m.movieID = mlm.movieID " +
                                                "JOIN movieList ml ON mlm.listID = ml.listID " +
                                                    "WHERE ml.listName = ? AND m.userName = ?";
        
        List<Movie> movies = jdbc.query(SELECT_MOVIES_IN_LIST, new movieMapper(), listName, movie.getUserName());
        for (Movie newMovie : movies) {
            if (newMovie.getTitle().equals(movie.getTitle())) {
                return null;
            }
        }
        
        final String INSERT_MOVIE = "INSERT INTO movie (userName, imdbID, userScore, poster, title, releaseDate, rating, runTime, plot, popularity, budget) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_MOVIE, movie.getUserName(), movie.getImdbID(), movie.getUserScore(), movie.getPoster(), movie.getTitle(), movie.getReleaseDate(), movie.getRating(), movie.getRunTime(), movie.getPlot(), movie.getPopularity(), movie.getBudget());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        movie.setMovieID(newId);
        
        return movie;
    }
    
    @Override
    public void addActorToMovie(Movie movie, Actor actor) {    
        final String INSERT_MOVIE_ACTOR = "INSERT INTO movieActor (movieID, actorID) VALUES (?,?)";
        jdbc.update(INSERT_MOVIE_ACTOR, movie.getMovieID(), actor.getActorID());
    }
    
    @Override
    public void addGenreToMovie(Movie movie, Genre genre) {
        final String SELECT_GENRE = "SELECT * FROM genre WHERE genreID = ?";
        Genre newGenre = jdbc.queryForObject(SELECT_GENRE, new genreMapper(), genre.getGenreID());
        
        final String INSERT_MOVIE_GENRE = "INSERT INTO movieGenre (movieID, genreID) VALUES (?,?)";
        jdbc.update(INSERT_MOVIE_GENRE, movie.getMovieID(), newGenre.getGenreID());
    }
    
    @Override
    public MovieListActorGenre getMyMovie(Movie movie) {
        final String SELECT_MOVIE = "SELECT * FROM movie WHERE movieID = ? ";
        final String SELECT_ACTORS = "SELECT * FROM actor a JOIN movieActor ma ON a.actorID = ma.actorID " +
                                        "JOIN movie m ON ma.movieID = m.movieID WHERE m.movieID = ? ";
        final String SELECT_GENRES = "SELECT * FROM genre g JOIN movieGenre mg ON g.genreID = mg.genreID " +
                                        "JOIN movie m ON mg.movieID = m.movieID WHERE m.movieID = ?";
        
        MovieListActorGenre mlag = new MovieListActorGenre();
        Movie myMovie= jdbc.queryForObject(SELECT_MOVIE, new movieMapper(), movie.getMovieID());
        
        mlag.setGenreList(jdbc.query(SELECT_GENRES, new genreMapper(), movie.getMovieID()));
        mlag.setActorList(jdbc.query(SELECT_ACTORS, new actorMapper(), movie.getMovieID()));
        mlag.setMovie(myMovie);
        
        return mlag;
    }
    
    @Override
    public void deleteMovie(Movie movie) {
        final String DELETE_ACTORS = "DELETE actor FROM actor JOIN movieActor ma ON actor.actorID = ma.actorID " +
                                        "JOIN movie m ON ma.movieID = m.movieID WHERE m.movieID = ?";
        
        final String DELETE_GENRES = "DELETE genre FROM genre JOIN movieGenre mg ON genre.genreID = mg.genreID " +
                                        "JOIN movie m on mg.movieID = m.movieID WHERE m.movieID = ?";
        
        final String DELETE_MOVIE = "DELETE movie FROM movie JOIN movieListmovie mlm ON movie.movieID = mlm.movieID " +
                                        "JOIN movieList ml ON mlm.listID = ml.listID WHERE movie.movieID = ?";
        
        jdbc.update(DELETE_ACTORS, movie.getMovieID());
        jdbc.update(DELETE_GENRES, movie.getMovieID());
        jdbc.update(DELETE_MOVIE, movie.getMovieID());
    }
    
    @Override
    public void updateUserScore(Movie movie) {
        final String UPDATE_USER_SCORE = "UPDATE movie SET userScore = ? WHERE movie.movieID = ?";
        
        jdbc.update(UPDATE_USER_SCORE, movie.getUserScore(), movie.getMovieID());
    }
    
    @Override
    public List<Movie> scoreComparisonChart(Movie movie) {
        final String GET_MOVIES = "SELECT * FROM movie WHERE userName = ? ORDER BY userScore DESC LIMIT 30";
        List<Movie> movies = jdbc.query(GET_MOVIES, new movieMapper(), movie.getUserName());
        
        return movies;
    }
    
    @Override
    public List<Movie> budgetScoreChart(Movie movie) {
        final String GET_MOVIES = "SELECT * FROM movie WHERE userName = ? ORDER BY budget DESC";
        List<Movie> movies = jdbc.query(GET_MOVIES, new movieMapper(), movie.getUserName());
        return movies;
    }
    
    @Override
    public List<Movie> movieLengthChart(Movie movie) {
        final String GET_MOVIES = "SELECT * FROM movie WHERE userName = ? ORDER BY runTime DESC";
        List<Movie> movies = jdbc.query(GET_MOVIES, new movieMapper(), movie.getUserName());
        return movies;
    }
    
    public List<Movie> getAllRatings(Movie movie) {
        final String GET_ALL_RATINGS = "SELECT rating, COUNT(rating) r FROM movie WHERE userName = ? " +
                                           "GROUP BY rating ORDER BY r DESC";
        
        List<Movie> movies = jdbc.query(GET_ALL_RATINGS, new ratingMapper(), movie.getUserName());
        return movies;
    }

}
