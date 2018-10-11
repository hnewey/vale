package org.newdevelopment.vale.data.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.newdevelopment.vale.data.exception.GameException;
import org.newdevelopment.vale.data.model.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@Component
public class GameDao {
    
    private JdbcTemplate jdbcTemplate;
    private ObjectMapper objectMapper;
    
    @Autowired
    public GameDao(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
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
}
