package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Actor;
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
public class ActorDaoDB implements ActorDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class ActorMapper implements RowMapper<Actor> {
        @Override
        public Actor mapRow(ResultSet rs, int index) throws SQLException {
            Actor actor = new Actor("");
            actor.setActorName(rs.getString("actorName"));
            actor.setActorID(Integer.parseInt(rs.getString("actorID")));
            return actor;
        }
    }

    @Override
    public void add(Actor actor) {
        int i = 0;
        final String SELECT_ALL_ACTORS = "SELECT * FROM actor";
        List<Actor> actors = jdbc.query(SELECT_ALL_ACTORS, new ActorMapper());
        
        for (Actor a : actors) {
            if (actor.getActorName().equals(a.getActorName())) {
                i++;
            }
        }
        if (i == 0) {
           final String INSERT_ACTOR = "INSERT INTO actor (actorName) VALUES (?)";
        
           jdbc.update(INSERT_ACTOR, actor.getActorName());
           int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
           actor.setActorID(newId); 
        }
    }
    
}
