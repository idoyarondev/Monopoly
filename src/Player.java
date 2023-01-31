import java.util.ArrayList;

public class Player {
	
	private String name;
	private int currentTileNumber;
	private int balance;
	private ArrayList<Tile> tilesOwned;
	
	public Player(String playerName) {
		this.name = playerName;
		currentTileNumber = 0;
		balance = 16;
		tilesOwned = new ArrayList<>();
	}
	
	public void addToTilesOwnedByPlayer(Tile tile) {
		tilesOwned.add(tile);
	}
	
	/* getters */
	public String getPlayerName() {
		return name;
	}
	
	public int getCurrentTileNumber() {
		return currentTileNumber;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public ArrayList<Tile> getTilesOwnedByPlayer(){
		return tilesOwned;
	}
	
	/* setters */
	public void setCurrentTileNumber(int currentTileNumber) {
		this.currentTileNumber = currentTileNumber;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
