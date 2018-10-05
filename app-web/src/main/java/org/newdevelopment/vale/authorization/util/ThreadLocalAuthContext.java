package org.newdevelopment.vale.authorization.util;

/**
 * A holder for an authorization response object stored on a thread-local variable.
 *
 */
public class ThreadLocalAuthContext {

    private static final ThreadLocal<AuthContext> AUTHORIZATION_CONTEXT = new ThreadLocal<>();

    private ThreadLocalAuthContext() {}

    /**
     * Get the current authorization context
     * @return The current context.
     */
    public static AuthContext getAuthContext() {
        return AUTHORIZATION_CONTEXT.get();
    }

    /**
     * Set the current authorization context.
     * @param authContext The current authContext
     */
    public static void setAuthContext(AuthContext authContext) {

        AUTHORIZATION_CONTEXT.set(authContext);
    }

    /**
     * Clear the thread local holding the AuthContext.
     */
    public static void clearAuthContext() {
        if (AUTHORIZATION_CONTEXT.get() != null) {
            AUTHORIZATION_CONTEXT.get().setToken(null);
            AUTHORIZATION_CONTEXT.get().setAuthorizationResult(null);
        }
        AUTHORIZATION_CONTEXT.remove();
    }

}
