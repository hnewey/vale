package org.newdevelopment.vale.data.util.sql;

public final class AuthSql {
    private AuthSql() {
    }

    public static final String SELECT_USERNAME_AVAILABLE =
            "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM user_table u " +
            "WHERE (u.username = ?)";

    public static final String SELECT_USER =
            "SELECT u.* " +
            "FROM user_table u " +
            "WHERE (u.username = ?)";

}
