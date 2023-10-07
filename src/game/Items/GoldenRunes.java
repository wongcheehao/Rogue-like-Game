package game.Items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils.RandomNumberGenerator;
import game.actions.ConsumeAction;
import game.Items.RuneManager;

/**
 * A consumable item which gives a range of runes when consumed
 * Created by:
 * @author Park Jun Koo
 */
public class GoldenRunes extends ConsumableItem {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
    }

    /**
     * Check for location of this item and the actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation,Actor actor) {
        super.tick(currentLocation,actor);
    }

    /**
     * Player gains random range of runes when this item is consumed
     * @param actor
     */
    @Override
    public void consume(Actor actor) {
        int runeAmount = RandomNumberGenerator.getRandomInt(200,10000);
        RuneManager.getInstance().gainRune(runeAmount);
    }

    /**
     * This item not provided at the start of the game
     * @return false
     */
    @Override
    public boolean isBasicItem() {
        return false;
    }
}
