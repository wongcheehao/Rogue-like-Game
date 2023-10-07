package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.combatclass.CombatClassManager;
import game.enums.Status;

/**
 * An Action to rest.
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class RestAction extends Action {
    /**
     * When executed, add resting status to actor. Execute ResetAction
     *
     * @param actor The actor performing the rest action.
     * @param map The map the actor is on.
     * @return the result of the rest.
     * @see ResetAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        actor.addCapability(Status.RESTING);
        result += new ResetAction().execute(actor, map);
        return result;
    }

    /**
     * Describes where the actor rests at.
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at Sites Of Lost Grace" ;
    }
}
