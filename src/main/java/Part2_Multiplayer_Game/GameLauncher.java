package Part2_Multiplayer_Game;


import Part2_Multiplayer_Game.Tressure_Finder_Game.GameEngine;

/**
 * This is the GameLauncher class and it is used to start the game
 */

public class GameLauncher {
    public static void main(String args[]){
        GameEngine game = new GameEngine();
        game.StartGame();
    }
}
