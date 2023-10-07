package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Utils.FancyMessage;
import game.Utils.MapString;
import game.actors.npc.NPCManager;
import game.actors.players.*;
import game.grounds.*;
import game.grounds.Acid;
import game.grounds.NightmareWater;
/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chee Hao Wong, Park Jun Koo, Steve Rahardjo
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(),
				new GustOfWind(), new PuddleOfWater(), new SiteOfLostGrace(),
				new Cliff(), new Cage(), new Barrack(), new SummonSign(), new Acid(), new NightmareWater(),  new WaterVortex());


		GameMap limGrave = new GameMap(groundFactory, MapString.LIM_GRAVE);
		GameMap stormveilCastle = new GameMap(groundFactory, MapString.STORMVEIL_CASTLE);
		GameMap roundTableHold = new GameMap(groundFactory, MapString.ROUNDTABLE_HOLD);
		GameMap bossRoom = new GameMap(groundFactory, MapString.BOSS_ROOM);
		GameMap lakeofrot = new GameMap(groundFactory, MapString.LAKE_OF_ROT);
		world.addGameMap(limGrave);
		world.addGameMap(stormveilCastle);
		world.addGameMap(roundTableHold);
		world.addGameMap(bossRoom);
		world.addGameMap(lakeofrot);


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		Player player = new Player("Tarnished", '@', 99999999, limGrave.at(38, 11));

		// Class is selected based on the user input
		String selection;
		do {
			selection = ArchetypeMenuManager.getInstance().menuClass();
			switch (selection) {
				case "b" -> new Bandit(player);
				case "s" -> new Samurai(player);
				case "w" -> new Wretch(player);
				case "a" -> new Astrology(player);
			}
		} while (!selection.equalsIgnoreCase("b") && !selection.equalsIgnoreCase("s") &&
				!selection.equalsIgnoreCase("w") && !selection.equalsIgnoreCase("a"));


		world.addPlayer(NPCManager.getInstance().merchantK(), limGrave.at(40, 12));
		world.addPlayer(NPCManager.getInstance().fingerReaderEnia(), limGrave.at(38, 12));
		world.addPlayer(player, limGrave.at(36, 10));

		limGrave.at(3, 23).setGround(new GoldenFogDoor(stormveilCastle.at(0,0), "Stormveil Castle"));
		stormveilCastle.at(0,0).setGround(new GoldenFogDoor(lakeofrot.at(3, 23), "Land of Rot"));
		lakeofrot.at(0,0).setGround(new GoldenFogDoor(limGrave.at(3, 23), "Lim Grave"));
		limGrave.at(0, 0).setGround(new GoldenFogDoor(roundTableHold.at(9,10), "Round Table Hold"));
		roundTableHold.at(9,10).setGround(new GoldenFogDoor(limGrave.at(0,0), "Lim Grave"));
		stormveilCastle.at(74,0).setGround(new GoldenFogDoor(bossRoom.at(1,1), "Boss Room"));
		world.run();
	}
}