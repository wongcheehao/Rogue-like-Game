package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action to remove actor from map.
 * Created by:
 * @author Chee Hao Wong
 */
public class RemoveAction extends Action{

    /**
     * Constructor
     */
    public RemoveAction() {
    }

    /**
     * when this action is executed, remove actor from map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the actor has been removed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        // remove actor
        map.removeActor(actor);
        result += menuDescription(actor);
        return result;
    }

    /**
     * Describes which target has been removed from the map
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map.";
    }
}
