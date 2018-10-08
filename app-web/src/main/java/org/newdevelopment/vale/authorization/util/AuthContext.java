package org.newdevelopment.vale.authorization.util;

public class AuthContext {
    private String token;
    private String username;
    private Boolean authorizationResult;

    public AuthContext() {
        token = null;
        username = null;
        authorizationResult = null;
    }

    public String getToken() { return token; }
    public void setToken(String sessionId) { this.token = sessionId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Boolean getAuthorizationResult() { return authorizationResult; }
    public void setAuthorizationResult(Boolean authorizationResult) { this.authorizationResult = authorizationResult; }
}