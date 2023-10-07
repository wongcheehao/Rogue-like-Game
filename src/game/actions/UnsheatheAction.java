package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;


import java.util.Random;

/**
 * Special skill which deals double the damage
 * Created by:
 * @author Park Jun Koo
 */
public class UnsheatheAction extends Action {

    private Actor target;


    /**
     * The direction of incoming attack.
     */
    private String direction;

    private Random rand = new Random();


    Weapon weapon;

    public UnsheatheAction(Actor target, String direction,Weapon weapon){

        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        // Success rate is 60%
        if (!(rand.nextInt(100) <= 60)) {
            return actor + " misses " + target + ".";
        }


        // This skill doubles the damage
        int damage = weapon.damage() * 2;
        result = actor + " unsheathes " + target + " for " + damage + " damage.";
        target.hurt(damage);


        // Check whether the enemy is death
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }


        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " unsheathes " + target + " at " + direction + " with " + weapon );
    }
}
