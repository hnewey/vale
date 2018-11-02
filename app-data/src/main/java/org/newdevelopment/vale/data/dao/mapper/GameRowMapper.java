package org.newdevelopment.vale.data.dao.mapper;

import org.newdevelopment.vale.data.model.Game;
import org.newdevelopment.vale.data.model.GameState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.newdevelopment.vale.data.util.AppConstants.*;

public class GameRowMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setGameId(rs.getString(GAME_ID));
        game.setUsername(rs.getString(USER_USERNAME));
        //TODO: Implement a json to GameState method
        game.setGameState(new GameState());

        return game;
    }
}
