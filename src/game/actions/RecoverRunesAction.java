package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Items.Rune;
import game.Items.RuneManager;

/**
 * An Action to recover dropped runes.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class RecoverRunesAction extends Action {

    /**
     * The rune to be recovered
     */
    private Rune rune;

    /**
     * Constructor
     * @param rune the rune to be recovered
     */
    public RecoverRunesAction(Rune rune) {
        this.rune = rune;
    }

    /**
     * Perform the Action. Actor gain rune, rune removed from map.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().gainRune(rune.getValue());
        map.locationOf(actor).removeItem(rune);
        return menuDescription(actor);
    }

    /**
     * Returns actor recovers runes with the value of rune.
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " recovers runes " + rune.getValue();
    }
}

