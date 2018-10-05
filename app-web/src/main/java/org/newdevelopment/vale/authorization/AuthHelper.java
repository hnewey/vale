package org.newdevelopment.vale.authorization;

import org.newdevelopment.vale.authorization.util.AuthContext;
import org.newdevelopment.vale.authorization.util.ThreadLocalAuthContext;
import org.newdevelopment.vale.data.exception.AuthorizationException;
import org.springframework.http.HttpStatus;

import static org.newdevelopment.vale.data.util.AppConstants.*;

public class AuthHelper {

    public Boolean isAuthorized() throws AuthorizationException {
        Boolean authorized = getAuthContext().getAuthorizationResult();

        if (authorized == null) {
            throw new AuthorizationException(BAD_TOKEN, HttpStatus.BAD_REQUEST);
        }

        return authorized;
    }

    public String getToken() throws AuthorizationException {
        String token = getAuthContext().getToken();

        if (token == null) {
            throw new AuthorizationException(BAD_TOKEN, HttpStatus.BAD_REQUEST);
        }

        return token;
    }

    private AuthContext getAuthContext() throws AuthorizationException {
        if (ThreadLocalAuthContext.getAuthContext() == null) {
            throw new AuthorizationException(MISSING_INTERCEPTOR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ThreadLocalAuthContext.getAuthContext();
    }

}
