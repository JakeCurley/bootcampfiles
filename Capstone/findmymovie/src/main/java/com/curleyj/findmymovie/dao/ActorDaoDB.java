package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
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
public class ActorDaoDB implements ActorDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class actorMapper implements RowMapper<Actor> {
        @Override
        public Actor mapRow(ResultSet rs, int index) throws SQLException {
            Actor actor = new Actor("");
            actor.setActorName(rs.getString("actorName"));
            actor.setActorID(Integer.parseInt(rs.getString("actorID")));
            actor.setUserName(rs.getString("userName"));
            return actor;
        }
    }
    
    public static final class actorCountMapper implements RowMapper<Actor> {
        @Override
        public Actor mapRow(ResultSet rs, int index) throws SQLException {
            Actor actor = new Actor("");
            actor.setActorName(rs.getString("actorName"));
            actor.setActorCount(Integer.parseInt(rs.getString("a")));
            actor.setUserName(rs.getString("userName"));
            return actor;
        }
    }

    @Override
    public void add(Actor actor) {
        final String INSERT_ACTOR = "INSERT INTO actor (actorName, userName) VALUES (?,?)";
        
        jdbc.update(INSERT_ACTOR, actor.getActorName(), actor.getUserName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        actor.setActorID(newId); 
    }
    
    @Override
    public List<Actor> getAllActors(Movie movie) {
        final String GET_ALL_ACTORS = "SELECT actorName, userName, COUNT(actorName) a FROM actor WHERE userName = ? " +
                                        "GROUP BY actorName ORDER BY a DESC LIMIT 30";
        
        List<Actor> actors = jdbc.query(GET_ALL_ACTORS, new actorCountMapper(), movie.getUserName());
        return actors;
    }
    
}