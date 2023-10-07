package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//import game.enums.EnemyType;
import game.enums.EnemyType;
import game.weapons.Grossmesser;

/**
 * A class to represent Heavy Skeletal Swordsman
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class HeavySkeletalSwordsman extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 892;
    private final static int MIN_RUNE_AMOUNT = 35;

    /**
     * Constructor. Heavy Skeletal Swordsman carries Grossmesser.
     *
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(EnemyType.SKELETAL);
    }

    /**
     * Creates and returns the intrinsic weapon of HeavySkeletalSwordsman.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "attacks", 95);
    }


    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
