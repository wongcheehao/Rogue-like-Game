package game.Items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.actions.RecoverRunesAction;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * A currency used in the game
 * Created by:
 * @author Park Jun Koo
 * Modified by: Chee Hao Wong
 */
public class Rune extends Item implements Resettable {

    /**
     * The amount of runes.
     */
    private int value;

    /**
     * Flag to check if the recoverAction has been added to allowableActions
     */
    private boolean flag ;

    /**
     * number of reset times this rune undergo
     */
    private int resetCounter;

    /***
     * Constructor.
     */
    public Rune(int value) {
        super("runes", '$', false);
        this.value = value;
        resetCounter = 1;
        flag = false;
        ResetManager.getInstance().registerResettable(this);
    }

    /***
     * Getter for value
     */
    public int getValue() {
        return value;
    }

    /**
     * If Rune is dropped on the ground, add RecoverRunesAction to allowableActions
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
//        The rune is removed in the next reset if not picked up.
        if (resetCounter == 2){
            currentLocation.removeItem(this);
        }
        if(!flag){
            this.addAction(new RecoverRunesAction(this));
            flag = true;
        }
    }

     /**
     * Increment reset counter
     */
    @Override
    public void reset() {
        this.resetCounter += 1;
    }
}
