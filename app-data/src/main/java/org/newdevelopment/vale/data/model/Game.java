package org.newdevelopment.vale.data.model;

public class Game {

    private String gameId;
    private String username;
    private GameState gameState;

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) { this.gameState = gameState; }
}
