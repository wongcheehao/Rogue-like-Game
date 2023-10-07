package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GreatKnife;

import java.util.Random;

/**
 * Special skill which moves to the random surrounding after attack
 * Created by:
 * @author Park Jun Koo
 */
public class QuickStepAction extends Action {

    private Actor target;


    /**
     * The direction of incoming attack.
     */
    private String direction;

    private Random rand = new Random();

    // Weapons with
    Weapon weapon;



    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        // Success rate is same as the weapon hit rate
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        result = actor + " quick steps " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }


        // Get location of the attacker
        Location location = map.locationOf(actor);
        for (Exit exit : location.getExits()){
            Location destination = exit.getDestination();
            if(!destination.containsAnActor()){
                map.moveActor(actor,destination);
                break;
            }


        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " quicksteps " + target + " at " + direction + " with " + weapon );
    }
}

