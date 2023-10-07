package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * An Action to attack surrounding Actors.
 * Created by:
 * @author Chee Hao Wong
 * Modified by: Park Jun Koo
 *
 */
public class AreaAttackAction extends AttackAction {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 * @param weapon the weapon used for attack
	 */
	public AreaAttackAction(Actor target, String direction, Weapon weapon) {
		super(target, direction, weapon);
		this.target = target;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AreaAttackAction(Actor target, String direction) {
		super(target,direction);
		this.target = target;
	}

	/**
	 * When executed, call AttackAction to attack all actor at the exits.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see AttackAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);

		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		int damage = weapon.damage();
		String result = actor + " attacks anything in the surrounding for " + damage + " damage.";

		for(Exit exit : here.getExits()){
			if(exit.getDestination().containsAnActor()){
				target = exit.getDestination().getActor();
				result += System.lineSeparator() +  super.execute(actor,map);
			}
		}
		return result;
	}

	/**
	 * Describes the actor is attack surround with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks anything in the surrounding with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}
}
