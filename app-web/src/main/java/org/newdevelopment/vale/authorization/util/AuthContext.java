package org.newdevelopment.vale.authorization.util;

public class AuthContext {
    private String token;
    private Boolean authorizationResult;

    public AuthContext() {
        token = null;
        authorizationResult = null;
    }

    public String getToken() { return token; }
    public void setToken(String sessionId) { this.token = sessionId; }

    public Boolean getAuthorizationResult() { return authorizationResult; }
    public void setAuthorizationResult(Boolean authorizationResult) { this.authorizationResult = authorizationResult; }
}