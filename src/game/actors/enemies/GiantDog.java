package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//import game.enums.EnemyType;
import game.enums.EnemyType;
import game.weapons.Grossmesser;

/**
 *  A class to represent Giant Dog
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class GiantDog extends Enemy{
    private final static int MAX_RUNE_AMOUNT = 1808;
    private final static int MIN_RUNE_AMOUNT = 313;

    /**
     * Constructor.
     *
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(EnemyType.DOG_WOLF);

    }

    /**
     * Creates and returns the intrinsic weapon of GiantDog.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "attacks", 90);
    }

    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
