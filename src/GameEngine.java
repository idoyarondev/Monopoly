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
			boolean passGo = false;
			
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
			currentPlayer.setCurrentTileNumber(newTileNumber);
			
			/* checks if players passes the GO tile */
			if(previousTileNumber + roll >= numOfTiles) {
				passGo = true;
			}
			
			/* actions taken at new tile */
			
			/* gets the player's previous balance */
			int previousPlayerBalance = currentPlayer.getBalance();
			System.out.println(playerName + "'s previous balance is " + previousPlayerBalance + "$");
			
			/* add 1$ to player's balance if passed GO */
			if(passGo) {
				System.out.println(playerName + " passed GO and gets 1$");
				players[numOfPlayer].setBalance(previousPlayerBalance + 1);
				previousPlayerBalance = currentPlayer.getBalance();
			}
			
			/* checks if new tile is the GO tile */
			/* Can be changed to using type variable in Tile class */
			if(newTileNumber != 0) {
				
				Player owner = newTile.getOwner();
				int tilePrice = newTile.getPrice();
				
				/* player buys tile if there is no owner */
				if(owner == null) {
					System.out.println(playerName + " is buying " + newTileName);
					System.out.println(newTileName + "'s price is " + tilePrice + "$");
					currentPlayer.setBalance(previousPlayerBalance - tilePrice);
					newTile.setOwner(currentPlayer);
					currentPlayer.addToTilesOwnedByPlayer(newTile);
				}
				
			}
			
			/* next player's turn */
			numOfPlayer = (numOfPlayer+1) % players.length;

		}
	}

}
