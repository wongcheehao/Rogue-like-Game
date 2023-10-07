package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.weapons.Grossmesser;

/**
 * A class to represent Godrick Soldier
 *
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class GodrickSoldier extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 70;
    private final static int MIN_RUNE_AMOUNT = 38;

    /**
     * Constructor.
     *
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(EnemyType.RAISED_IN_STORMVEIL_CASTLE);
    }

    /**
     * Creates and returns the intrinsic weapon of HeavySkeletalSwordsman.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(0, "attacks ", 0);
    }


    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}
