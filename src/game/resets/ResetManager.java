package game.resets;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Park Jun Koo, Chee Hao Wong
 *
 */
public class ResetManager {

    /**
     * A list storing all the resettable in the game.
     */
    private List<Resettable> resettables;

    /**
     * Constructor for ResetManager
     */
    private static ResetManager resetManager;

    /**
     * Create a new instance of ResetManager and assign it to the instance variable if it is null.
     * @return ResetManager instance
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    public static ResetManager getInstance(){
        if(resetManager == null){
            resetManager = new ResetManager();
        }
        return resetManager;
    }

    /**
     * Execute reset() method of every resettable.
     */
    public void run(){
        for (Resettable resettable : resettables) {
            resettable.reset();
        }
    }

    /**
     * Add resettable to the resettables list.
     */
    public void registerResettable(Resettable resettable) {resettables.add(resettable);}

}
