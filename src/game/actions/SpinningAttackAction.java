package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

import java.util.Random;

/**
 * Special skill which attacks any actors in the surrounding
 * Created by:
 * @author Park Jun Koo
 */
public class SpinningAttackAction extends Action {

    private Actor target;

    private String direction;

    private Weapon weapon;
    private Random rand = new Random();


    public SpinningAttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }


    @Override
    public String execute(Actor attacker, GameMap map) {

        String result = "";
        Location here = map.locationOf(attacker);


        int damage = weapon.damage();
        result = attacker + " attacks anything in the surrounding for " + damage + " damage.";

        for(Exit exit : here.getExits()){
            if(exit.getDestination().containsAnActor()){
                target = exit.getDestination().getActor();
                result += System.lineSeparator() +  new AttackAction(target, exit.getName(), weapon).execute(attacker,map);
            }
        }
        return result;
    }



    @Override
    public String menuDescription(Actor actor) {
        return actor + " spin attacks anything in the surrounding";
    }
}
