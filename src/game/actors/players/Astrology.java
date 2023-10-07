package game.actors.players;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.GreatKnife;

/**
 * Astrology class which starts the game with Great Knife weapon
 * Created by:
 * @author Park Jun Koo
 *
 */
public class Astrology {
    /**
     * player is the Actor
     */
    private Actor player;

    /**
     * Constructor for Astrology class
     * This class starts the game with GreatKnife and 396 hit points
     * @param player the player
     */
    public Astrology(Actor player){
        this.player = player;
        this.player.addWeaponToInventory(new GreatKnife());   // GreatKnife has been added to this class since the new weapon is optional
        this.player.resetMaxHp(396);
    }
}
