package org.newdevelopment.vale.service;

import jersey.repackaged.com.google.common.base.Preconditions;
import org.newdevelopment.vale.data.dao.AuthenticationDao;
import org.newdevelopment.vale.data.exception.AuthenticationException;
import org.newdevelopment.vale.data.model.UserAuth;
import org.newdevelopment.vale.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.newdevelopment.vale.data.util.AppConstants.*;

@Component
public class AuthenticationService {

    private AuthenticationDao authenticationDao;
    private PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public AuthenticationService(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
        this.passwordEncryptionService = new PasswordEncryptionService();
    }

    public Boolean checkUsername(String username) {
        Preconditions.checkArgument(username != null, NULL_USERNAME);
        return authenticationDao.checkUsername(username);
    }

    public void createNewUser(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException, AuthenticationException {
        validateInput(userAuth);

        //generate salt and encrypted password
        byte[] userSalt = passwordEncryptionService.generateSalt();
        byte[] encryptedPassword = passwordEncryptionService.getEncryptedPassword(userAuth.getPassword(), userSalt);
        authenticationDao.createUser(userAuth.getUsername(), encryptedPassword, userSalt);
    }

    public Boolean authenticateUser(UserAuth userAuth) throws InvalidKeySpecException, NoSuchAlgorithmException, AuthenticationException {
        Preconditions.checkArgument(userAuth.getUsername() != null, NULL_USERNAME);
        Preconditions.checkArgument(userAuth.getPassword() != null, NULL_PASSWORD);

        User user;
        try {
            user = authenticationDao.getUser(userAuth.getUsername());
        } catch (Exception e) {
            throw new AuthenticationException(INVALID_LOGIN, HttpStatus.BAD_REQUEST);
        }

        //test provided password and return result
        return passwordEncryptionService.authenticate(userAuth.getPassword(), user.getPassword(), user.getSalt());
    }




    //----------------------------
    //private methods
    //----------------------------
    private void validateInput(UserAuth userAuth) {
        Preconditions.checkArgument(userAuth.getUsername() != null, NULL_USERNAME);
        Preconditions.checkArgument(userAuth.getPassword() != null, NULL_PASSWORD);
        Preconditions.checkArgument(!userAuth.getPassword().isEmpty(), EMPTY_PASSWORD);
        Preconditions.checkArgument(userAuth.getPassword().length() >= 8, BAD_PASSWORD);
    }
}
