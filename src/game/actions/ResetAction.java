package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;

/**
 * An Action to reset the game.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class ResetAction extends Action {

    /**
     * When executed, calling run() of ResetManager to reset all the resettables.
     *
     * @param actor The actor performing the reset action.
     * @param map The map the actor is on.
     * @return "Game has been reset"
     * @see ResetManager
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();

        return "Game has been reset";
    }

    /**
     * Return "Reset the game"
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }
}
