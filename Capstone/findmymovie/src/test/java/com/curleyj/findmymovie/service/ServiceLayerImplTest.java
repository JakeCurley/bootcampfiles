/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.service;

import com.curleyj.findmymovie.dao.ActorDao;
import com.curleyj.findmymovie.dao.GenreDao;
import com.curleyj.findmymovie.dao.LoginInfoDao;
import com.curleyj.findmymovie.dao.MovieDao;
import com.curleyj.findmymovie.dao.MovieListDao;
import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.LoginInfo;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Jake
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ServiceLayerImplTest {
    
    @Autowired
    MovieListDao movieListDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    ActorDao actorDao;
    
    @Autowired
    GenreDao genreDao;
    
    @Autowired
    LoginInfoDao liDao;
    
    public ServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<MovieList> movieLists = movieListDao.getAllMovieList();
        try {
            for (MovieList list : movieLists) {
                movieListDao.deleteList(list);
            }
        } catch(NullPointerException e) {
        }
        
        List<LoginInfo> logins = liDao.getAllAccounts();
        
        try {
            for (LoginInfo l : logins) {
                liDao.deleteAccount(l);
            }
        } catch(NullPointerException e) {}
        
        List<Movie> movies2 = movieDao.budgetScoreChart();
        MovieList ml = new MovieList();
        ml.setListName("list1");
        movieListDao.add(ml);
        
        Movie movie2 = new Movie();
            movie2.setImdbID("1");
            movie2.setTitle("test movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setDirector("Test director");
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
        movieDao.add(movie2, ml.getListName());
        movieListDao.movieListByName(movie2, ml.getListName());
        
        Actor actor = new Actor("");
        actor.setActorName("TestActor");
        actorDao.add(actor);
        movieDao.addActorToMovie(movie2, actor);
        
        Genre genre = new Genre("");
        genre.setGenreName("GenreTest");
        genreDao.add(genre);
        movieDao.addGenreToMovie(movie2, genre);  
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllMovieList method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllMovieList() {
        List<MovieList> movieLists = movieListDao.getAllMovieList();
        assertEquals(movieLists.size(), 1);
    }

    /**
     * Test of addList method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddList() {
        MovieList newList = new MovieList();
        newList.setListName("Test List");
        movieListDao.add(newList);
        List<MovieList> lists = movieListDao.getAllMovieList();
        
        assertEquals(lists.size(), 2);
        assertNotEquals(lists.size(), 3);
        
        //Test duplicate list names
        MovieList failList = new MovieList();
        failList.setListName("list1");
        
        MovieList testFail = movieListDao.add(failList);
        assertNull(testFail);
    }

    /**
     * Test of addMovieToList method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddMovieToList() {
        
        Movie movie = new Movie();
            movie.setImdbID("1");
            movie.setTitle("testMovie");
            movie.setUserScore(5);
            movie.setReleaseDate("2015-01-01");
            movie.setRating("");
            movie.setRunTime(120);
            movie.setDirector("Test director");
            movie.setPlot("This is a test plot");
            movie.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie.setPopularity("7.9");
            movie.setBudget(50000000);
            List<MovieList> lists = movieListDao.getAllMovieList();
            
            for (MovieList list : lists) {
                movieDao.add(movie, list.getListName());
                movieListDao.movieListByName(movie, list.getListName());
            }
            
            MovieListActorGenre mlag = movieDao.getMyMovie(movie);
            assertEquals(mlag.getMovie(), movie);
            
            
            //Test that duplicate movie titles can't be added to the same list
            Movie movie2 = new Movie();
            movie2.setImdbID("1");
            movie2.setTitle("test movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setDirector("Test director");
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            
            Movie failAdd = movieDao.add(movie2, "list1");
            assertNull(failAdd);
    }

    /**
     * Test of addActor method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddActor() {
        
        Actor actor = new Actor("");
        actor.setActorName("ActorTest");
        actorDao.add(actor);
        List<Actor> actors = actorDao.getAllActors();
        assertEquals(actors.size(), 2);
        assertNotEquals(actors.size(), 3);
        
    }

    /**
     * Test of addGenre method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddGenre() {
        
        Genre genre = new Genre("");
        genre.setGenreName("TestGenre");
        genreDao.add(genre);
        List<Genre> genres = genreDao.getAllGenres();
        assertEquals(genres.size(), 2);
        assertNotEquals(genres.size(), 1);
        
    }

    /**
     * Test of getCompleteList method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetCompleteList() {
        MovieList ml = new MovieList();
        ml.setListName("list2");
        movieListDao.add(ml);
        
        Movie movie2 = new Movie();
            movie2.setImdbID("1");
            movie2.setTitle("test movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setDirector("Test director");
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
        movieDao.add(movie2, ml.getListName());
        movieListDao.movieListByName(movie2, ml.getListName());
        MovieListActorGenre daoList = movieListDao.getCompleteList(ml);
        
        assertEquals(daoList.getListName(), ml.getListName());
        assertEquals(daoList.getMovies().size(), 1);
    }

    /**
     * Test of getMyMovie method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetMyMovie() {
        
        List<Movie> movies = movieDao.budgetScoreChart();
        System.out.println(movies.size());
        
        for (Movie m : movies) {
            MovieListActorGenre mlag = movieDao.getMyMovie(m);
            assertEquals(mlag.getMovie(), m);
            assertEquals(mlag.getGenreList().size(), 1);
            assertEquals(mlag.getActorList().size(), 1);
        }
        
    }

    /**
     * Test of deleteList method, of class ServiceLayerImpl.
     */
    @Test
    public void testDeleteList() {
        MovieList list = new MovieList();
        list.setListName("deleteList");
        movieListDao.add(list);
        List<MovieList> lists = movieListDao.getAllMovieList();
        assertEquals(lists.size(),2);
        
        movieListDao.deleteList(list);
        lists = movieListDao.getAllMovieList();
        assertEquals(lists.size(), 1);
    }

    /**
     * Test of deleteMovie method, of class ServiceLayerImpl.
     */
    @Test
    public void testDeleteMovie() {
        
        Movie movie2 = new Movie();
        movie2.setImdbID("1");
            movie2.setTitle("test delete movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setDirector("Test director");
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            
        movieDao.add(movie2, "list1");
        movieListDao.movieListByName(movie2, "list1");
        List<Movie> movies = movieDao.movieLengthChart();
        assertEquals(movies.size(), 2);
        
        movieDao.deleteMovie(movie2);
        movies = movieDao.movieLengthChart();
        assertEquals(movies.size(), 1);
        
    }

    /**
     * Test of updateUserScore method, of class ServiceLayerImpl.
     */
    @Test
    public void testUpdateUserScore() {
        
        List<Movie> movies = movieDao.budgetScoreChart();
        for (Movie m : movies) {
            assertEquals(m.getUserScore(), 5);
            m.setUserScore(10);
            movieDao.updateUserScore(m);
        }
        
        movies = movieDao.budgetScoreChart();
        for (Movie m2 : movies) {
            assertEquals(m2.getUserScore(), 10);
        }
    }

    /**
     * Test of getAllGenres method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllGenres() {
        List<Genre> genres = genreDao.getAllGenres();
        assertEquals(genres.size(), 2);
    }

    /**
     * Test of getAllActors method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllActors() {
        List<Actor> actors = actorDao.getAllActors();
        assertEquals(actors.size(), 2);
    }

    /**
     * Test of register method, of class ServiceLayerImpl.
     */
    @Test
    public void testRegisterAndLogin() {
        
        LoginInfo li = new LoginInfo();
        li.setUserName("testUserName");
        li.setPassword("password");
        
        liDao.register(li);
        
        LoginInfo newLi = new LoginInfo();
        newLi.setUserName("testUserName");
        newLi.setPassword("password");
        
        LoginInfo correctLogin = liDao.verifyLoginInfo(newLi);
        assertNotNull(correctLogin);
        
        LoginInfo newLi2 = new LoginInfo();
        newLi2.setUserName("testUserName");
        newLi2.setPassword("wrongPassword");
        LoginInfo incorrectPassword = liDao.verifyLoginInfo(newLi2);
        assertNull(incorrectPassword);
        
        LoginInfo newLi3 = new LoginInfo();
        newLi3.setUserName("wrongUserName");
        newLi3.setPassword("password");
        try {
            LoginInfo wrongUserName = liDao.verifyLoginInfo(newLi3);
            fail("Should have thrown exception");
        } catch (Exception e) {
            return;
        }

    }

    /**
     * Test of scoreComparisonChart method, of class ServiceLayerImpl.
     */
    @Test
    public void testScoreComparisonChart() {
        List<Movie> movies = movieDao.scoreComparisonChart();
        assertEquals(movies.size(), 1);
    }

    /**
     * Test of budgetScoreChart method, of class ServiceLayerImpl.
     */
    @Test
    public void testBudgetScoreChart() {
        List<Movie> movies = movieDao.budgetScoreChart();
        assertEquals(movies.size(), 1);
    }

    /**
     * Test of movieLengthChart method, of class ServiceLayerImpl.
     */
    @Test
    public void testMovieLengthChart() {
        List<Movie> movies = movieDao.movieLengthChart();
        assertEquals(movies.size(), 1);
    }
    
}
