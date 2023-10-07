package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;

/**
 *  A class to represent Giant Cray Fosh
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class GiantCrayFish extends Enemy {
    private final static int MAX_RUNE_AMOUNT = 2374;
    private final static int MIN_RUNE_AMOUNT = 500;

    /**
     * Constructor.
     *
     */
    public GiantCrayFish() {
        super("Giant Crab", 'R', 4803);
        this.addCapability(EnemyType.CRAB_FISH);
    }

    /**
     * Creates and returns the intrinsic weapon of GiantCrabFish.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527,"attacks",100);
    }


    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }


}

