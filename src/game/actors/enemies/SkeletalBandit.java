package game.actors.enemies;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//import game.enums.EnemyType;
import game.enums.EnemyType;
import game.weapons.Scimitar;

import java.util.Scanner;

/**
 *  A class to represent Skeletal Bandit
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class SkeletalBandit extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 892;
    private final static int MIN_RUNE_AMOUNT = 35;

    /**
     * Constructor.
     *
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184);
        this.addCapability(EnemyType.SKELETAL);
        this.addWeaponToInventory(new Scimitar());
    }

    /**
     * Creates and returns the intrinsic weapon of SkeletalBandit.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(0, "attacks", 0);
    }

    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }

}

