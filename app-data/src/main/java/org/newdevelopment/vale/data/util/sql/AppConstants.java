package org.newdevelopment.vale.data.util.sql;

public final class AppConstants {

    //------------------------------------
    //ERROR MESSAGES
    //------------------------------------
    public static final String NULL_USERNAME = "username cannot be null";
    public static final String NULL_PASSWORD = "password cannot be null";
    public static final String EMPTY_PASSWORD = "password cannot be empty";
    public static final String BAD_PASSWORD = "password does not meet requirements";

    public static final String USERNAME_EXISTS = "username already exists";
    public static final String INVALID_LOGIN = "username or password is incorrect";


    //------------------------------------
    //TABLE AND COLUMN NAMES
    //------------------------------------
    public static final String USER_TABLE = "user_table";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_SALT = "salt";
}
