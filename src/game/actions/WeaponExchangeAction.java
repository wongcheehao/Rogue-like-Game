package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class WeaponExchangeAction extends Action {

    private Item item;

    private WeaponItem weaponItem;

    public WeaponExchangeAction(Item item, WeaponItem weaponItem) {
        this.item = item;
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        actor.addWeaponToInventory(weaponItem);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges " + item + " to " + weaponItem;
    }
}
