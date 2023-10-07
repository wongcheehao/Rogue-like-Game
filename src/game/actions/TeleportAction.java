package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action that teleport the actor.
 * Created by:
 * @author Chee Hao Wong
 */
public class TeleportAction extends Action {

    /**
     * The location teleport to.
     */
    private Location teleportDestination;

    /**
     * The name of the map teleport to.
     */
    private String mapName;

    /**
     * Constructor.
     *
     * @param teleportDestination the location teleport to
     * @param mapName the name of the map teleport to
     */
    public TeleportAction(Location teleportDestination, String mapName) {
        this.teleportDestination = teleportDestination;
        this.mapName = mapName;
    }

    /**
     * When executed, the actor is moved to the teleport destination.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return the result of the attack, e.g. the actor teleport to which map.
     * @see MoveActorAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        new MoveActorAction(teleportDestination, "to somewhere else").execute(actor, map);
        return menuDescription(actor);
    }

    /**
     * Return the actor travels to which map.
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + mapName;
    }
}
