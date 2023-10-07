package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.combatclass.CombatClassManager;

/**
 * Summons either ally or invader within the surrounding at a chance of 50%
 * Created by:
 * @author Park Jun Koo
 */
public class SummonAction extends Action {

    /**
     * Location where the ally or invader to be summoned
     */
    Location summonLocation;


    /**
     * Constructor of SummontAction class
     * @param summonLocation
     */
    public SummonAction(Location summonLocation) {
        this.summonLocation = summonLocation;
    }

    /**
     * When there is a space, summon either ally or invader
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string display of ally or invader being summoned or insufficient space to summon
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Randomly assigned role of ally or invader
        Actor summon = CombatClassManager.getInstance().summonCombatClass();

        // If the ground is empty, summon ally or invader
        for (Exit exit : summonLocation.getExits()){
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(actor)) {
                map.addActor(summon, exit.getDestination());
                return menuDescription(actor);
            }

        }

        return summon + " cannot be summoned due to insufficient space";


    }

    /**
     * menu display
     * @param actor The actor performing the action.
     * @return player summons ally or invader in the surrounding
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest in the surrounding";
    }
}
