## This is a Monopoly game created with Java.

The program contains 4 classes:
1 Monopoly - this is the main class that recieves the data and creates objects of the other classes and runs the game.
2 GameEngine - holds the logic of the game
3 Player - holds data about each player such as name, balance, etc.
4 Tile - holds data about tile such as price, etc.

This program uses the org.json.simple external library to read the JSON files.

The program crates 4 hard coded players and inserts them into an array.
This can be changed to recieve external player data from a JSON file or user input in future (same as board and rolls).

Tiles are being read from the board.json file and inserted into a hash table.

Rolls are being read from a JSON file inserted into a queue.

The program then runs the game engine with the appropriate attributes.
When the game is over the program prints out the final results.

## How to run:
To compile the program go to src folder and run from the command line (Windows):
javac -cp "json-simple-1.1.1.jar" Monopoly.java GameEngine.java Player.java Tile.java
*the cp stands for class path. It adds the external java for JSON library to the program.

After compiling, from the src folder, to run the program with rolls_1 JSON from the command line (Windows):
java -cp .;json-simple-1.1.1.jar Monopoly "1"
to run the program with rolls_2 JSON from the command line (Windows):
java -cp .;json-simple-1.1.1.jar Monopoly "2"

## Extensibility:
The game has been programmed with extensibility in mind.
The game engine has been seperated from the main method so it could be replaced by another game engine if desired.
Both the Tile class and the rolls queue were created in such way that any JSON file containing tiles/dice rolls can be used to extend the game.
For example, if a longer game with more tiles is wanted.

