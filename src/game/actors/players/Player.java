package game.actors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//import game.actions.RespawnAction;
import game.Utils.FancyMessage;
import game.Items.FlaskOfCrimsonTears;
import game.Items.GoldenRunes;
import game.Items.RemembranceOfTheGrafted;
import game.enums.Status;
import game.grounds.SiteOfLostGrace;
import game.Items.Rune;
import game.Items.RuneManager;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.weapons.*;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Park Jun Koo, Chee Hao Wong
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private Location respawnSite;
	private Location lastLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location respawnSite) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESPAWNABLE);
		this.addCapability(Status.PLAYER);
		this.addItemToInventory(new Rune(0));
		this.addItemToInventory(new FlaskOfCrimsonTears());
		ResetManager.getInstance().registerResettable(this);
		this.respawnSite = respawnSite;
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//		update the last location of player
		updateLastLocation(map);

//		update the respawn site of player
		updateRespwnSite(map);

//		When the game been reset
		if (this.hasCapability(Status.WIPED)) {
			resetTurn(map);
		}else{
			return normalTurn(actions, lastAction, display);
		}

		//if the player is inside Acid, return display in println
		if(this.hasCapability(Status.ROTTING)){
			display.println(this + " got drained");
		}

//		Reset capability to default
		resetCapability();

		return new DoNothingAction();
	}

	/**
	 * Creates and returns the intrinsic weapon of player.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}

	/**
	 * Add Status.WIPED to this actor.
	 */
	@Override
	public void reset() {
		this.addCapability(Status.WIPED);
	}

//  	Remove WIPED&RESTING status from player.
	private void resetCapability() {
//		Remove WIPED status from player, if there is.
		this.removeCapability(Status.WIPED);

//		Remove RESTING status from player, if there is.
		this.removeCapability(Status.RESTING);
	}

//		When the game not been reset.
	private Action normalTurn(ActionList actions, Action lastAction, Display display) {
		// Display HP and runes in the console
		display.println(this + this.printHp() + ", rune:" + RuneManager.getInstance().getRuneAmount());

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

// 		When game reset
	private void resetTurn(GameMap map) {
//			Remove player from map
		map.removeActor(this);

//			If the reset is caused by player's death
		if(!this.hasCapability(Status.RESTING)) {
			playerDeath();
		}

//			If actor is RESPAWNABLE
		if(this.hasCapability(Status.RESPAWNABLE)){
			respawn(map);
		}
	}

//		When player respawn
	private void respawn(GameMap map) {
//				heal fully revived
		this.hitPoints = maxHitPoints;

//				player respawn at respawnSite
		map.addActor(this, respawnSite);
	}

	//	When player dies
	private void playerDeath() {
//				print out YOU_DIED message
		new Display().println(FancyMessage.YOU_DIED);

//				drop rune at last location
		lastLocation.addItem(new Rune(RuneManager.getInstance().getRuneAmount()));

//				rune of actor becomes zero
		RuneManager.getInstance().spendRune(RuneManager.getInstance().getRuneAmount());
	}

	//		update the location of respawn site if the player has visited another Site Of Lost Grace
	private void updateRespwnSite(GameMap map) {
		if (map.locationOf(this).getGround() instanceof SiteOfLostGrace){
			this.respawnSite = map.locationOf(this);
		}
	}

	//		update the last location of actor(for drop runes purpose)
	private void updateLastLocation(GameMap map) {
		if(!this.hasCapability(Status.WIPED)) {
			this.lastLocation = map.locationOf(this);
		}
	}
}
