package game.grounds;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.enums.Status;

/**
 * Ground which summons either ally or invader within surroundings
 * Created by:
 * @author Park Jun Koo
 */
public class SummonSign extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Check whether the player is within the surrounding
     * Add SummonAction when the player is around
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions to be performed, SummonAction in this case
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        if (location.containsAnActor() && location.getActor().hasCapability(Status.PLAYER)){
            actions.add(new SummonAction(location));
        }

        for (Exit exit: location.getExits()){
            if (exit.getDestination().containsAnActor()){
                if (exit.getDestination().getActor().hasCapability(Status.PLAYER)){
                    actions.add(new SummonAction(location));
                }
            }
        }

        return actions;
    }

    /**
     * Check whether the actor can enter this ground
     * @param actor the Actor to check
     * @return only player can enter this ground
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.PLAYER);
    }


}
