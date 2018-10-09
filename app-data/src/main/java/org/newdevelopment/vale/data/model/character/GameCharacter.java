package org.newdevelopment.vale.data.model.character;

/**
 * Model object to track character type and ability scores
 * <p><b>Warrior: Strength-based. Uses melee weapons, shields, armor</b><br>
 *      <u>Expertise Classes (primary ability/secondary ability):</u><br>
 *      Champion: (Strength/Agility) All-around strong fighter and harder to hit.<br>
 *      Barbarian: (Strength/Knowlege) Fierce warrior with high chance of crit<br>
 *      Paladin: (Strength/Wisdom) High armor rating, can heal or has weak magic attacks?
 * </p><br>
 * <p><b>Bowman: Agility-based. Uses ranged weapons, light armor</b><br>
 *      <u>Bowman Expertise Classes</u><br>
 *      Marksman: (Agility-Knowledge) Deadly archer with high chance of crit<br>
 *      Rogue: (Agility-Strength) Quick fighter that can do significant damage<br>
 *      Ranger: (Agility-Wisdom) Competent fighter with healing skills<br>
 * </p><br>
 * <p><b>Adept: Knowledge-based. Uses melee weapons and magic</b><br>
 *     <u>Adept Expertise Classes</u><br>
 *     Mage: (Knowledge/Strength) Dangerous fighter utilizing both magic and melee weapons<br>
 *     Necromancer: (Knowledge/Agility) Quick wizard susceptible to other magic users<br>
 *     Sorcerer: (Knowledge/Wisdom) Deadly magic user but fragile when attacked with melee weapons<br>
 * </p>
 */
public abstract class GameCharacter {

    private Integer strength; //chance to hit with physical weapons, amount of damage done, chance to crit (magic)
    private Integer agility; //chance to be hit by physical weapons, increases character movement
    private Integer knowledge; //chance to hit with magic, number of turns per round, chance to crit (melee weapons)
    private Integer wisdom; //chance to be hit by magic, amount of magic damage done

    private Integer armor; //Increases chances to be hit, reduces damage taken
    private Integer health; //Damage that can be taken before death
    private Integer movement; //Number of spaces character can move each turn

    //other things we'll need classes for: equipment, powers (usable and innate), experience and level trackers, etc

    public GameCharacter() {
        //Base values
        strength = 0;
        agility = 0;
        knowledge = 0;
        wisdom = 0;
        armor = 0;
        health = 10;
        movement = 5;
    }

    public void attack(GameCharacter target) {
        //Algorithm to determine hit chance and damage done
    }

    public void takeDamage(Integer damage) {
        this.health -= damage;
        if (health <= 0) {
            die();
        }
    }

    private void die() {

    }
}
