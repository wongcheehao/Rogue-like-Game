package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.weapons.Grossmesser;

/**
 * A class to represent Dog
 *
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class Dog extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 1390;
    private final static int MIN_RUNE_AMOUNT = 52;

    /**
     * Constructor.
     *
     */
    public Dog() {
        super("Dog", 'a', 104);
        this.addCapability(EnemyType.RAISED_IN_STORMVEIL_CASTLE);
    }

    /**
     * Creates and returns the intrinsic weapon of Dog.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites ", 93);
    }


    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
