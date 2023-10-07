package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.actions.RestAction;

/**
 * A class that represents Site Of Lost Grace.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class SiteOfLostGrace extends Ground {

    /**
     * Constructor.
     *
     */
    public SiteOfLostGrace() {
        super('U');
    }


    /**
     * The player can rest at Site Of Lost Grace.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability(Status.PLAYER)){
            actions.add(new RestAction());
        }
        return actions;
    }
}
