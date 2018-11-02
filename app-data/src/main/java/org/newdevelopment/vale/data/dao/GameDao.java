package org.newdevelopment.vale.data.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.newdevelopment.vale.data.dao.mapper.GameRowMapper;
import org.newdevelopment.vale.data.exception.GameException;
import org.newdevelopment.vale.data.model.Game;
import org.newdevelopment.vale.data.model.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.newdevelopment.vale.data.util.AppConstants.*;
import static org.newdevelopment.vale.data.util.sql.GameSql.GET_ALL_GAMES;

@Component
public class GameDao {
    
    private JdbcTemplate jdbcTemplate;
    private ObjectMapper objectMapper;
    private GameRowMapper gameRowMapper;
    
    @Autowired
    public GameDao(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.gameRowMapper = new GameRowMapper();
    }
    
    public Integer createNewGame(String username, GameState gameState) throws JsonProcessingException, GameException {
        
        String marshalledGameState = objectMapper.writeValueAsString(gameState);

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(GAME_TABLE).usingGeneratedKeyColumns(GAME_ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(GAME_USERNAME, username);
        parameters.put(GAME_STATE, marshalledGameState);

        try {
            return (Integer) jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        } catch(Exception e){
            throw new GameException(String.format("Error creating new game. error: %s", e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Game> getAllGames(String username) {
        return jdbcTemplate.query(GET_ALL_GAMES, new Object[]{username}, gameRowMapper);
    }
}
