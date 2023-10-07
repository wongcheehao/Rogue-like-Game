package game.actors.players;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.GreatKnife;

/**
 * Bandit class which starts the game with Great Knife weapon
 * Created by:
 * @author Park Jun Koo
 */
public class Bandit {
    /**
     * player is the Actor
     */
    private Actor player;

    /**
     * Constructor for Bandit class
     * This class starts the game with GreatKnife and 414 hit points
     * @param player the player
     */
    public Bandit(Actor player){
        this.player = player;
        this.player.addWeaponToInventory(new GreatKnife());
        this.player.resetMaxHp(414);
    }
}
