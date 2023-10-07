package game.actors.players;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.Uchigatana;

/**
 * Samurai class which starts the game with Uchigatana weapon
 * Created by:
 *  * @author Park Jun Koo
 */
public class Samurai {

    /**
     * player is the Actor
     */
    private Actor player;

    /**
     * Constructor for Samurai class
     * This class starts the game with Uchigatana and 455 hit points
     * @param player the player
     */
    public Samurai(Actor player){
        this.player = player;
        this.player.addWeaponToInventory(new Uchigatana());
        this.player.resetMaxHp(455);
    }
}