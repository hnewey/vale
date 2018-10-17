package org.newdevelopment.vale.data.util;

public final class AppConstants {

    //------------------------------------
    //ERROR MESSAGES
    //------------------------------------
    public static final String NULL_USERNAME = "username cannot be null";
    public static final String NULL_PASSWORD = "password cannot be null";
    public static final String EMPTY_PASSWORD = "password cannot be empty";
    public static final String BAD_PASSWORD = "password does not meet requirements";

    public static final String NULL_STATE = "provided game state cannot be null";
    public static final String NULL_STATE_DATA = "provided game state has one or more null fields";

    public static final String USERNAME_EXISTS = "username already exists";
    public static final String INVALID_LOGIN = "username or password is incorrect";

    public static final String UNAUTHORIZED = "user is not authorized to perform this action";
    public static final String BAD_TOKEN = "invalid token provided";

    public static final String MISSING_INTERCEPTOR = "missing interceptor, AuthContext is null";


    //------------------------------------
    //TABLE AND COLUMN NAMES
    //------------------------------------
    public static final String USER_TABLE = "user_table";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_SALT = "salt";

    public static final String GAME_TABLE = "game";
    public static final String GAME_ID = "game_id";
    public static final String GAME_USERNAME = "username";
    public static final String GAME_STATE = "game_state";
}
