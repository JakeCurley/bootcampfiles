/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.dao.MovieDaoDB.movieMapper;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class MovieListDaoDB implements MovieListDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class MovieListMapper implements RowMapper<MovieList> {
        @Override
        public MovieList mapRow(ResultSet rs, int index) throws SQLException {
            MovieList movieList = new MovieList();
            movieList.setListID(Integer.parseInt(rs.getString("listID")));
            movieList.setListName(rs.getString("listName"));
            return movieList;
        }
    }
    
    @Override
    public MovieList add(MovieList movieList) {
        int i = 0;
        final String SELECT_MOVIE_LIST = "SELECT * FROM movieList";
        List<MovieList> MovieLists = jdbc.query(SELECT_MOVIE_LIST, new MovieListMapper());
        for (MovieList m : MovieLists) {
            if (movieList.getListName().equals(m.getListName())) {
                i++;
            }
        }
        
        if (i == 0) {
        final String INSERT_LIST = "INSERT INTO movieList(listName) VALUES (?)";
        jdbc.update(INSERT_LIST, movieList.getListName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        movieList.setListID(newId);
        return movieList;
        }
        return null;
    }

    @Override
    public List<MovieList> getAllMovieList() {
        final String SELECT_MOVIE_LIST = "SELECT * FROM movieList";
        List<MovieList> MovieLists = jdbc.query(SELECT_MOVIE_LIST, new MovieListMapper());
        //add movies actors and genres
        
        return MovieLists;
    }

    @Override
    public MovieList movieListByName(Movie movie, String name) {
       final String SELECT_BY_NAME = "SELECT * FROM movieList WHERE listName = ?";
       MovieList movieList = jdbc.queryForObject(SELECT_BY_NAME, new MovieListMapper(), name);
        System.out.println(movieList.getListID());
       final String INSERT_MOVIE_LIST = "INSERT INTO movieListmovie (movieID, listID) VALUES (?,?)";
       jdbc.update(INSERT_MOVIE_LIST, movie.getMovieID(), movieList.getListID());
       return movieList;
    }
    
    @Override
    public List<MovieListActorGenre> getCompleteLists() {
        final String FILL_LISTS = "SELECT * FROM movie m " +
                "JOIN movieListmovie mlm ON m.movieID = mlm.movieID " +
                "JOIN movieList ml ON mlm.listID = ml.listID " +
                "WHERE ml.listID = ?";
        
        
        List<MovieList> lists = getAllMovieList();
        List<MovieListActorGenre> mlagList = new ArrayList<>();
        
        for (MovieList l : lists) {
            MovieListActorGenre mlag = new MovieListActorGenre();
            Movie movie = jdbc.queryForObject(FILL_LISTS, new movieMapper());
            mlag.setTitle(movie.getTitle());
            mlag.setRating(movie.getRating());
            mlag.setReleaseDate(movie.getReleaseDate());
            mlag.setListName(l.getListName());
            mlagList.add(mlag);
        }
        
        return mlagList;
    }
    
}