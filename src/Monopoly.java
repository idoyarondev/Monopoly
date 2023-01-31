import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class Monopoly {

	public static void main(String[] args) {
		
		System.out.println(args[0]);
		
		/* I Created an object of the class Player for each Monopoly player and placed them in an array of Player for use during the game */
		Player peter = new Player("Peter");
		Player billy = new Player("Billy");
		Player charlotte = new Player("Charlotte");
		Player sweedal = new Player("Sweedal");
		Player[] players = {peter,billy,charlotte,sweedal};

		/* I used a external Java library for working with JSON files */
		JSONParser parser = new JSONParser();
		
		/* Reads the board.json file and creates a hash table where the key is the tile's number and the value is an object of the class Tile */
		Hashtable<Integer,Tile> tiles = new Hashtable<>();	
		try (Reader reader = new FileReader("board.json")){
			
			JSONArray board = (JSONArray) parser.parse(reader);
			Integer tileNumber = 0;
			for (Object obj : board) {
				
				JSONObject jsonObject = (JSONObject)obj;
				
				/* since some tiles don't have all variables this code checks whether a variable has value and if not sends ""/0 as an argument when creating the object */
				String colour = "";
				String name = "";
				String type = "";
				int price = 0;
				
				if(jsonObject.containsKey("colour")) {
					colour = jsonObject.get("colour").toString();
				}
				if(jsonObject.containsKey("name")) {
					name = jsonObject.get("name").toString();
				}
				if(jsonObject.containsKey("type")) {
					type = jsonObject.get("type").toString();
				}
				if(jsonObject.containsKey("price")) {
					price = Integer.parseInt(jsonObject.get("price").toString());
				}
				
				tiles.put(tileNumber,new Tile(colour,price,name,type));
				tileNumber++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/* reads the rolls JSON file and creates a queue of rolls to be used during the game */
		Queue<Integer> rolls = new LinkedList<>(); 
		try (Reader reader = new FileReader("rolls_" + args[0] + ".json")){
			JSONArray jsonArray = (JSONArray) parser.parse(reader);
			for (Object object : jsonArray) {
				int roll = Integer.parseInt(object.toString());
				rolls.add(roll);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/* creates an object of game engine and runs the game */
		System.out.println("Welcome to Woven Monopoly! \n");
		
		GameEngine gameEngine = new GameEngine();
		gameEngine.runGame(players, tiles, rolls);
		
		System.out.println("Game over!");
		
		/* when the game is over shows the final results */

		/* sorts array of players based on their final score */
		Arrays.sort(players, Comparator.comparing(Player::getBalance));	//sort
		Collections.reverse(Arrays.asList(players));					//reverse
		System.out.println("Congratulations " + players[0].getPlayerName() + "! You are the winner! \n");
		
		/* prints final results in a table format */
		System.out.println("Final Results:");
		final Object[][] table = new String[players.length + 1][];
		table[0] = new String[] {"Name","Balance","Space"};
		for(int i=0;i<players.length;i++) {
			table[i+1] = new String[] {players[i].getPlayerName(), Integer.toString(players[i].getBalance()), 
					tiles.get(players[i].getCurrentTileNumber()).getName()};
		}

		for (final Object[] row : table) {
		    System.out.format("%15s%15s%25s%n", row);
		}
	}

}
