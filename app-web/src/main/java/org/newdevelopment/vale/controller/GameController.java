package org.newdevelopment.vale.controller;

import org.newdevelopment.vale.authorization.AuthHelper;
import org.newdevelopment.vale.data.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@RestController
@RequestMapping(value = "game")
public class GameController {

    private AuthHelper authHelper;

    @Autowired
    public GameController() {
        this.authHelper = new AuthHelper();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity newGame() throws AuthorizationException {
        if (!authHelper.isAuthorized()) {
            throw new AuthorizationException(UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().build();
    }

}
