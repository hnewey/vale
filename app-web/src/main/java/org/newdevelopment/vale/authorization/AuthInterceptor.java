package org.newdevelopment.vale.authorization;

import org.jetbrains.annotations.NotNull;
import org.newdevelopment.vale.authorization.util.AuthContext;
import org.newdevelopment.vale.authorization.util.ThreadLocalAuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Enumeration;
import java.util.Locale;

/**
 * <p>A Spring Interceptor for handling authentication tokens.</p>
 *
 * <p>The session token is pulled from the <code>Authorization</code> header as a
 * Bearer token. No other authentication methods are supported.
 *
 * @author Hayden Newey
 */
@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private JWTAuthService jwtAuthService;

    private static final String BEARER = "bearer ";
    private static final String UNAUTHORIZED_MESSAGE = "Missing or invalid Session";

    @Autowired
    public AuthInterceptor() {
        this.jwtAuthService = JWTAuthService.getInstance();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String sessionToken = null;

        // Try the OAuth 2 Authorization header as defined by http://tools.ietf.org/html/draft-ietf-oauth-v2-bearer
        Enumeration authHeaders = httpServletRequest.getHeaders(HttpHeaders.AUTHORIZATION);
        while (authHeaders != null && authHeaders.hasMoreElements()) {
            String authHeader = (String) authHeaders.nextElement();
            if (authHeader != null && authHeader.trim().toLowerCase(Locale.US).startsWith(BEARER)) {
                sessionToken = authHeader.substring(BEARER.length()).trim();
                int comma = sessionToken.indexOf(',');
                if (comma >= 0) {
                    sessionToken = sessionToken.substring(0, comma);
                }
            }
        }

        if (sessionToken != null) {
            //a token was passed. Make sure it is valid
            AuthContext authContext = new AuthContext();
            authContext.setToken(sessionToken);
            authContext.setAuthorizationResult(jwtAuthService.validateToken(sessionToken));

            //Setting this ThreadLocalAuthContext is the whole point of this Interceptor
            ThreadLocalAuthContext.setAuthContext(authContext);
            return true;
        }
//        httpServletResponse.addHeader(HttpHeaders.WARNING, UNAUTHORIZED_MESSAGE);
//        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED_MESSAGE);
//        return false;
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }

}
