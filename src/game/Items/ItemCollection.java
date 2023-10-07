package game.Items;

import game.Items.Purchasable;
import game.weapons.*;

import java.util.ArrayList;

/**
 * Collection of selling items
 * Created by:
 * @author Park Jun Koo
 */
public class ItemCollection {

    private ArrayList<Purchasable> sellingItems = new ArrayList<>();

    public ItemCollection() {
        sellingItems.add(new Uchigatana());
        sellingItems.add(new GreatKnife());
        sellingItems.add(new Club());
        sellingItems.add(new Scimitar());
    }

    public ArrayList<Purchasable> getSellingItems(){
        return sellingItems;
    }

}