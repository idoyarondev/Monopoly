import org.json.simple.parser.*;

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
	}

}
