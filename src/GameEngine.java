import java.util.Hashtable;
import java.util.Queue;

public class GameEngine {
	
	public void runGame(Player[] players, Hashtable<Integer,Tile> tiles, Queue<Integer> rolls) {
		
		boolean gameOver = false;
		int numOfPlayer = 0;	//number of the player whose turn it is
		int numOfTiles = tiles.size();
		
		/* a while loop that runs the game until one of the players runs out of money */
		while(!gameOver) {
			
			Player currentPlayer = players[numOfPlayer];
			String playerName = currentPlayer.getPlayerName();
			int previousTileNumber = currentPlayer.getCurrentTileNumber();
			Tile previousTile = tiles.get(previousTileNumber);
			
			/* returns and removes the dice roll at the front end of the queue */
			int roll = rolls.poll();
			System.out.println("It is " + playerName + "'s turn to roll the dice");
			System.out.println(playerName + " has rolled " + roll);
			
			/* moves player to new tile based on their dice roll */
			int newTileNumber = (previousTileNumber + roll) % numOfTiles;
			Tile newTile = tiles.get(newTileNumber);
			String newTileName = newTile.getName();
			System.out.println(playerName + " was on tile number " + previousTileNumber + " - " + previousTile.getName());
			System.out.println(playerName + " is now on tile number " + newTileNumber + " - " + newTileName);
			players[numOfPlayer].setCurrentTileNumber(newTileNumber);
			
			/* next player's turn */
			numOfPlayer = (numOfPlayer+1) % players.length;

		}
	}

}
