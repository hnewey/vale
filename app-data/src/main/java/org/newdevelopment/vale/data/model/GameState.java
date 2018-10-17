package org.newdevelopment.vale.data.model;

import org.newdevelopment.vale.data.model.character.GameCharacter;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private List<GameCharacter> playerTeam;
    private List<Item> inventory;
    private Integer chapter;
    private Integer stage;

    public GameState() {
        this.playerTeam = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.chapter = 1;
        this.stage = 1;
    }

    public List<GameCharacter> getPlayerTeam() { return playerTeam; }
    public void setPlayerTeam(List<GameCharacter> playerTeam) { this.playerTeam = playerTeam; }

    public List<Item> getInventory() { return inventory; }
    public void setInventory(List<Item> inventory) { this.inventory = inventory; }

    public Integer getChapter() { return chapter; }
    public void setChapter(Integer chapter) { this.chapter = chapter; }

    public Integer getStage() { return stage; }
    public void setStage(Integer stage) { this.stage = stage; }

    public Boolean hasNoNullValues() {
        return (playerTeam != null && inventory != null && chapter != null && stage != null);
    }
}
