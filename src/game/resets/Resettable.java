package game.resets;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chee Hao Wong
 *
 */
public interface Resettable {

    /**
     * What happened when the resettable being reset, e.g. remove from map etc.
     */
    void reset();
}
