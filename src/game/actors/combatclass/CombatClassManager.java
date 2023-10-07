package game.actors.combatclass;

import edu.monash.fit2099.engine.actors.Actor;
import game.Utils.RandomNumberGenerator;
import game.actors.players.Astrology;
import game.actors.players.Bandit;
import game.actors.players.Samurai;
import game.actors.players.Wretch;

import java.util.ArrayList;

/**
 * Manages summoning of ally and invader
 * Created by:
 * @author Park Jun Koo
 */
public class CombatClassManager {
    /**
     * private attribute of the class
     */
    private static CombatClassManager combatClassManager = null;

    /**
     * list of combat classes in the game
     */
    private ArrayList<Actor> combatClasses = new ArrayList<>();
    /**
     * Constructor for CombatClassManager
     */
    private CombatClassManager(){};

    /**
     * Create a new instance of CombatClassManager and assign it to the instance variable if it is null.
     * @return CombatClassManager instance
     */
    public static CombatClassManager getInstance() {
        if (combatClassManager == null){
            combatClassManager = new CombatClassManager();
        }
        return combatClassManager;
    }

    /**
     * Choose roles randomly and create ally
     * @return ally with a role
     */
    public Actor summonAlly() {
        Actor ally = new Ally();
        int choice = RandomNumberGenerator.getRandomInt(1,4);
        switch (choice) {
            case 1 -> new Bandit(ally);
            case 2 -> new Samurai(ally);
            case 3 -> new Wretch(ally);
            case 4 -> new Astrology(ally);
            default -> {
            }
        }
        combatClasses.add(ally);
        return ally;
    }

    /**
     * Choose roles randomly and create invader
     * @return invader with a role
     */
    public Actor summonInvader() {
        Actor invader = new Invader();
        int choice = RandomNumberGenerator.getRandomInt(1,4);
        switch (choice) {
            case 1 -> new Bandit(invader);
            case 2 -> new Samurai(invader);
            case 3 -> new Wretch(invader);
            case 4 -> new Astrology(invader);
            default -> {
            }
        }
        combatClasses.add(invader);
        return invader;
    }

    /**
     * Summon either ally or invader at a chance of 50%
     * @return ally or invader
     */
    public Actor summonCombatClass(){
        // Chance of selection is 50%
        if (Math.random() <= 0.5){
            return summonAlly();
        }
        return summonInvader();
    }

    /**
     * Get a list of combat classes in the game
     * @return an arraylist of combat classes
     */
    public ArrayList<Actor> getCombatClasses(){
        return combatClasses;
    }
}