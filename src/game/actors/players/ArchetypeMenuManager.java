package game.actors.players;

import edu.monash.fit2099.engine.displays.Display;
import game.Utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Let user select the class for the game
 * Created by:
 * Park Jun Koo
 * Modified by: Chee Hao Wong
 */
public class ArchetypeMenuManager {
    /**
     * private attribute of the class
     */
    private static ArchetypeMenuManager archetypeMenuManager = null;


    /**
     * Constructor for MenuManagerAdmin
     */
    private ArchetypeMenuManager(){};

    /**
     * Create a new instance of MenuManagerAdmin and assign it to the instance variable if it is null.
     * @return MenuManagerAdmin instance
     */
    public static ArchetypeMenuManager getInstance() {
        if (archetypeMenuManager == null){
            archetypeMenuManager  = new ArchetypeMenuManager();
        }
        return archetypeMenuManager;
    }

    /**
     * Ask user to input for the class to select
     * @return string representation of a chosen class
     */
    public String menuClass() {
        Display display = new Display();
        Scanner sel = new Scanner(System.in);

        display.println("Select your role: ");
        display.println("b: Bandit");
        display.println("s: Samurai");
        display.println("w: Wretch");
        display.println("a: Astrology");

        String choice = sel.nextLine();
        return choice;
    }

}