package org.newdevelopment.vale.data.model.character;

public abstract class Hero extends GameCharacter{

    //child classes will implement leveling up individually since each hero type will do that differently
    public abstract void levelUp();


}
