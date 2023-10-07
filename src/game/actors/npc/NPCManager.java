package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;
import game.Items.ItemCollection;

/**
 * Manage NPC in the game
 * Created by:
 * @author Park Jun Koo
 */
public class NPCManager {

    private static NPCManager npcManager = null;

    /**
     * Constructor for NPCManager
     */
    private NPCManager(){};

    /**
     * Create a new instance of NPCManager and assign it to the instance variable if it is null.
     * @return NPCManager instance
     */
    public static NPCManager getInstance() {
        if (npcManager == null){
            npcManager = new NPCManager();
        }
        return npcManager;
    }

    /**
     * Create trader Merchant Kale
     * @return MerchantKale
     */
    public Actor merchantK(){
        return new MerchantKale(new ItemCollection());
    }

    /**
     * Create trader Finger Reader Enia
     * @return FingerReaderEnia
     */
    public Actor fingerReaderEnia(){
        return new FingerReaderEnia();
    }
}
