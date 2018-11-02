package org.newdevelopment.vale.data.util.sql;

public class GameSql {
    private GameSql() {}

    public static final String GET_ALL_GAMES =
            "SELECT g.* " +
            "FROM game g " +
            "WHERE (g.username = ?)";

}
