package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//import game.enums.EnemyType;
import game.enums.EnemyType;

/**
 * A class to represent Lone Wolf
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 1470;
    private final static int MIN_RUNE_AMOUNT = 55;

    /**
     * Constructor.
     *
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        this.addCapability(EnemyType.DOG_WOLF);
    }

    /**
     * Creates and returns the intrinsic weapon of LoneWolf.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
