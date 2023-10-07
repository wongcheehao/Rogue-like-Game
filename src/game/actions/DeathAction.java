package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Utils.FancyMessage;
import game.actors.combatclass.CombatClassManager;
import game.enums.Status;
import game.Items.RuneManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Park Jun Koo, Chee Hao Wong
 */
public class DeathAction extends Action {
    private final Actor attacker;

    private int runeAmount;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        if (target.hasCapability(Status.HOSTILE) || target.hasCapability(Status.COMBAT_CLASS)) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);

            // remove actor
            map.removeActor(target);
        }

        if (attacker.hasCapability(Status.PLAYER) && target.hasCapability(Status.HOSTILE)) {
            runeAmount = RuneManager.getInstance().getRuneAmountEnemy(target);
            RuneManager.getInstance().gainRune(runeAmount);
            RuneManager.getInstance().removeEnemy(target);
        }

        result += System.lineSeparator() + menuDescription(target);
        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (target.hasCapability(Status.PLAYER)) {
                // Allies and invaders are removed only when the player dies
                for (Actor combatClass : CombatClassManager.getInstance().getCombatClasses()) {
                    combatClass.removeCapability(Status.RESTING);
                }
                CombatClassManager.getInstance().getCombatClasses().clear();
                result += System.lineSeparator() + new ResetAction().execute(target, map);
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " is killed with " + runeAmount + " runes.";
    }
}