package org.newdevelopment.vale.controller;

import org.newdevelopment.vale.authorization.AuthHelper;
import org.newdevelopment.vale.authorization.JWTAuthService;
import org.newdevelopment.vale.data.exception.AuthenticationException;
import org.newdevelopment.vale.data.model.UserAuth;
import org.newdevelopment.vale.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private AuthHelper authHelper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthHelper authHelper) {
        this.authenticationService = authenticationService;
        this.authHelper = authHelper;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<String> createNewUser(@RequestBody UserAuth userAuth) throws AuthenticationException, InvalidKeySpecException, NoSuchAlgorithmException {

        //check if username exists in the database already. If it does, throw username exists exception
        if (authenticationService.checkUsername(userAuth.getUsername())) {
            throw new AuthenticationException(USERNAME_EXISTS, HttpStatus.BAD_REQUEST);
        }

        //call auth service which will encrypt the password and add a salt to the user
        authenticationService.createNewUser(userAuth);

        //generate new token for signed in user
        String token = authHelper.generateNewToken(userAuth);

        return ResponseEntity.ok().body(token);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody UserAuth userAuth) throws InvalidKeySpecException, NoSuchAlgorithmException, AuthenticationException {

        Boolean authenticated = authenticationService.authenticateUser(userAuth);

        if (!authenticated) {
            throw new AuthenticationException(INVALID_LOGIN, HttpStatus.BAD_REQUEST);
        }

        //generate new token for signed in user
        String token = authHelper.generateNewToken(userAuth);

        return ResponseEntity.ok().body(token);
    }
}
