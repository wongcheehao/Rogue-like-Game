package game.actors.players;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.Club;

/**
 * Wretch class which starts the game with a club weapon
 * Created by:
 * @author Park Jun Koo
 */
public class Wretch {

    /**
     * player is the Actor
     */
    private Actor player;

    /**
     * Constructor for Wretch class
     * This class starts the game with Club and 414 hit points
     * @param player the player
     */
    public Wretch(Actor player){
        this.player = player;
        this.player.addWeaponToInventory(new Club());
        this.player.resetMaxHp(414);
    }
}