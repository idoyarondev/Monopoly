public class Tile {
	private String colour,name,type;
	private int price;
	private Player owner;
	
	public Tile(String colour,int price,String name,String type) {
		this.colour = colour;
		this.price = price;
		this.name = name;
		this.type = type;
	}
	
	/* getters */
	public String getName() {
		return name;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getColour() {
		return colour;
	}
	
	/* setters */
	public void setOwner(Player player) {
		owner = player;
	}
}
