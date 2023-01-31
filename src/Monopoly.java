import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.Reader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class Monopoly {

	public static void main(String[] args) {
		
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
		try (Reader reader = new FileReader("D:\\Downloads\\new_coding_test\\board.json")){
			
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
		try (Reader reader = new FileReader("D:\\Downloads\\new_coding_test\\rolls_1.json")){
			JSONArray jsonArray = (JSONArray) parser.parse(reader);
			for (Object object : jsonArray) {
				int roll = Integer.parseInt(object.toString());
				rolls.add(roll);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/* creates an object of game enginer and runs the game */
		System.out.println("Welcome to Woven Monopoly! \n");
		
		GameEngine gameEngine = new GameEngine();
		gameEngine.runGame(players, tiles, rolls);
	}

}
