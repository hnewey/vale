package org.newdevelopment.vale.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.newdevelopment.vale.authorization.AuthHelper;
import org.newdevelopment.vale.data.exception.AuthorizationException;
import org.newdevelopment.vale.data.exception.GameException;
import org.newdevelopment.vale.data.model.Game;
import org.newdevelopment.vale.data.model.GameState;
import org.newdevelopment.vale.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private GameService gameService;
    private AuthHelper authHelper;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
        this.authHelper = new AuthHelper();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity newGame(@RequestBody GameState gameState) throws AuthorizationException,
                                                                            JsonProcessingException,
                                                                            GameException {
        String username = authHelper.getUsername();
        Integer gameId = gameService.createNewGame(username, gameState);

        return ResponseEntity.ok().body(gameId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Game> getAllGames() throws AuthorizationException {

        return gameService.getAllGames(authHelper.getUsername());
    }

}
