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
        MovieList forUser = new MovieList();
        forUser.setUserName("admin");
        List<MovieList> movieLists = movieListDao.getAllMovieList(forUser);
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
        
        LoginInfo newLogin = new LoginInfo();
        newLogin.setUserName("admin");
        newLogin.setPassword("password");
        liDao.register(newLogin);
        
        Movie movieForUser = new Movie();
        movieForUser.setUserName("admin");
        List<Movie> movies2 = movieDao.budgetScoreChart(movieForUser);
        MovieList ml = new MovieList();
        ml.setListName("list1");
        ml.setUserName("admin");
        movieListDao.add(ml);
        
        Movie movie2 = new Movie();
            movie2.setImdbID("1");
            movie2.setTitle("test movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            movie2.setUserName("admin");
            
        movieDao.add(movie2, ml.getListName());
        movieListDao.movieListByName(movie2, ml.getListName());
        
        Actor actor = new Actor("");
        actor.setActorName("TestActor");
        actor.setUserName("admin");
        actorDao.add(actor);
        movieDao.addActorToMovie(movie2, actor);
        
        Genre genre = new Genre("");
        genre.setGenreName("GenreTest");
        genre.setUserName("admin");
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
        MovieList ml = new MovieList();
        ml.setUserName("admin");
        List<MovieList> movieLists = movieListDao.getAllMovieList(ml);
        assertEquals(movieLists.size(), 1);
    }

    /**
     * Test of addList method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddList() {
        MovieList newList = new MovieList();
        newList.setListName("Test List");
        newList.setUserName("admin");
        movieListDao.add(newList);
        List<MovieList> lists = movieListDao.getAllMovieList(newList);
        
        assertEquals(lists.size(), 2);
        assertNotEquals(lists.size(), 3);
        
        //Test duplicate list names
        MovieList failList = new MovieList();
        failList.setUserName("admin");
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
            movie.setPlot("This is a test plot");
            movie.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie.setPopularity("7.9");
            movie.setBudget(50000000);
            movie.setUserName("admin");
            
            MovieList ml = new MovieList();
            ml.setUserName("admin");
            List<MovieList> lists = movieListDao.getAllMovieList(ml);
            
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
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            movie2.setUserName("admin");
            
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
        
        Movie forUserName = new Movie();
        forUserName.setUserName("admin");
        actorDao.add(actor);
        List<Actor> actors = actorDao.getAllActors(forUserName);
        assertEquals(actors.size(), 1);
        assertNotEquals(actors.size(), 2);
        
    }

    /**
     * Test of addGenre method, of class ServiceLayerImpl.
     */
    @Test
    public void testAddGenre() {
        
        Genre genre = new Genre("");
        genre.setGenreName("TestGenre");
        genreDao.add(genre);
        
        Movie forUserName = new Movie();
        forUserName.setUserName("admin");
        List<Genre> genres = genreDao.getAllGenres(forUserName);
        assertEquals(genres.size(), 1);
        assertNotEquals(genres.size(), 2);
        
    }

    /**
     * Test of getCompleteList method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetCompleteList() {
        MovieList ml = new MovieList();
        ml.setListName("list2");
        ml.setUserName("admin");
        movieListDao.add(ml);
        
        Movie movie2 = new Movie();
            movie2.setImdbID("1");
            movie2.setTitle("test movie");
            movie2.setUserScore(5);
            movie2.setReleaseDate("2015-01-01");
            movie2.setRating("");
            movie2.setRunTime(120);
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            movie2.setUserName("admin");
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
        Movie forUserName = new Movie();
        forUserName.setUserName("admin");
        List<Movie> movies = movieDao.budgetScoreChart(forUserName);
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
        list.setUserName("admin");
        movieListDao.add(list);
        List<MovieList> lists = movieListDao.getAllMovieList(list);
        assertEquals(lists.size(),2);
        
        movieListDao.deleteList(list);
        lists = movieListDao.getAllMovieList(list);
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
            movie2.setPlot("This is a test plot");
            movie2.setPoster("iGoXIpQb7Pot00EEdwpwPajheZ5.jpg");
            movie2.setPopularity("7.9");
            movie2.setBudget(50000000);
            movie2.setUserName("admin");
            
        movieDao.add(movie2, "list1");
        movieListDao.movieListByName(movie2, "list1");
        List<Movie> movies = movieDao.movieLengthChart(movie2);
        assertEquals(movies.size(), 2);
        
        movieDao.deleteMovie(movie2);
        movies = movieDao.movieLengthChart(movie2);
        assertEquals(movies.size(), 1);
        
    }

    /**
     * Test of updateUserScore method, of class ServiceLayerImpl.
     */
    @Test
    public void testUpdateUserScore() {
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Movie> movies = movieDao.budgetScoreChart(movie);
        for (Movie m : movies) {
            assertEquals(m.getUserScore(), 5);
            m.setUserScore(10);
            movieDao.updateUserScore(m);
        }
        
        movies = movieDao.budgetScoreChart(movie);
        for (Movie m2 : movies) {
            assertEquals(m2.getUserScore(), 10);
        }
    }

    /**
     * Test of getAllGenres method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllGenres() {
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Genre> genres = genreDao.getAllGenres(movie);
        assertEquals(genres.size(), 1);
    }

    /**
     * Test of getAllActors method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllActors() {
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Actor> actors = actorDao.getAllActors(movie);
        assertEquals(actors.size(), 1);
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
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Movie> movies = movieDao.scoreComparisonChart(movie);
        assertEquals(movies.size(), 1);
    }

    /**
     * Test of budgetScoreChart method, of class ServiceLayerImpl.
     */
    @Test
    public void testBudgetScoreChart() {
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Movie> movies = movieDao.budgetScoreChart(movie);
        assertEquals(movies.size(), 1);
    }

    /**
     * Test of movieLengthChart method, of class ServiceLayerImpl.
     */
    @Test
    public void testMovieLengthChart() {
        Movie movie = new Movie();
        movie.setUserName("admin");
        List<Movie> movies = movieDao.movieLengthChart(movie);
        assertEquals(movies.size(), 1);
    }
    
}
