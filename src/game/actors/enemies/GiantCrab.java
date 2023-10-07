package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.behaviours.AreaAttackBehaviour;

/**
 * A class to represent Giant Crab
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class GiantCrab extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 4961;
    private final static int MIN_RUNE_AMOUNT = 318;

    /**
     * Constructor.
     *
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        this.addCapability(EnemyType.CRAB_FISH);
    }

    /**
     * Creates and returns the intrinsic weapon of GiantCrab.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
