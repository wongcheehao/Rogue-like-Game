package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.actions.TeleportAction;
import game.enums.Status;

/**
 * A class that represents Golden Fog Door.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class GoldenFogDoor extends Ground{

    /**
     * The location that player can travels to through this door.
     */
    private Location teleportDestination;

    /**
     * The map name that player can travels to through this door.
     */
    private String mapName;

    /**
     * Constructor.
     */
    public GoldenFogDoor(Location teleportDestination, String mapName) {
        super('D');
        this.teleportDestination = teleportDestination;
        this.mapName = mapName;
    }

    /**
     * Only passable if the actor is player.
     *
     * @param actor the Actor to check
     * @return true if the actor is player.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * The player can teleport through the door.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new TeleportAction(teleportDestination, mapName));
        }
        return actions;
    }
}
