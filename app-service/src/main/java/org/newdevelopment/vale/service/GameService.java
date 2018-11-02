package org.newdevelopment.vale.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.newdevelopment.vale.data.dao.GameDao;
import org.newdevelopment.vale.data.exception.GameException;
import org.newdevelopment.vale.data.model.Game;
import org.newdevelopment.vale.data.model.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@Component
public class GameService {

    private GameDao gameDao;

    @Autowired
    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Integer createNewGame(String username, GameState gameState) throws JsonProcessingException, GameException {
        Preconditions.checkArgument(username != null, NULL_USERNAME);
        Preconditions.checkArgument(gameState != null, NULL_STATE);
        Preconditions.checkArgument(gameState.hasNoNullValues(), NULL_STATE_DATA);

        return gameDao.createNewGame(username, gameState);
    }

    public List<Game> getAllGames(String username) {
        Preconditions.checkArgument(username != null, NULL_USERNAME);

        return gameDao.getAllGames(username);
    }
}
