package game.Items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.Enemy;


import java.util.HashMap;

/**
 * Manages rune for the player and the enemies.
 * RuneManager directly manages rune of the player
 * RuneManager manages runes of enemies with a HashMap
 * Created by:
 * @author Park Jun Koo
 */

public class RuneManager{
    /**
     * The amount of rune of the player
     */
    private int runeAmount;

    /**
     * To ensure only one instance
     */
    private static RuneManager runeManager = null;

    /**
     * Hashmap of enemies with amount of runes
     */

    private HashMap<Enemy, Integer> runeTrack = new HashMap<>();


    /**
     * Constructor of the RuneManager
     */
    private RuneManager() { }

    public static RuneManager getInstance(){
        if (runeManager ==null){
            runeManager = new RuneManager();
        } return runeManager;
    }


    /**
     * Register rune for each enemy
     * @param enemy actor who extends to the enemy
     * @param runeAmount range of runes
     */
    public void registerRune(Enemy enemy, int runeAmount){
        runeTrack.put(enemy,runeAmount);
    }

    /**
     * Player gains the amount of the rune
     * @param amount amount of the rune to be added
     */
    public void gainRune(int amount) {
        runeAmount += amount;
    }

    /**
     * Player spends the rune
     * @param amount amount of the rune the player spends
     */
    public void spendRune(int amount){ runeAmount -= amount;}

    /**
     * Get balance of player's rune
     * @return rune balance
     */
    public int getRuneAmount(){return runeAmount;}


    /**
     * Get amount of enemy rune
     * @param enemy actor who extends to the enemy
     * @return amount of the rune
     */
    public int getRuneAmountEnemy(Actor enemy){
        return runeTrack.get(enemy);
    }

    /**
     * Removes the enemy from the hashmap
     * @param enemy actor who extends to the enemy
     */
    public void removeEnemy(Actor enemy){
        runeTrack.remove(enemy);
    }



}
