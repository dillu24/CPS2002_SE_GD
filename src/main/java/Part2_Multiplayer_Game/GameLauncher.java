package Part2_Multiplayer_Game;

import Part2_Multiplayer_Game.Tressure_Finder_Game.GameEngine;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the GameLauncher class and it is used to start the game with the proper parameters , note that the checking
 * for valid input is done in the game launcher class so as to keep the game class as concise as possible , and since
 * no unit testing can be done for user input
 */

public class GameLauncher {
    public static void main(String args[]) throws IOException {
        int numberOfPlayers = 0; //stores the number of players to play the game
        int mapSize = 0; //stores the map size
        String mapType;
        Scanner sc = new Scanner(System.in);
        GameEngine game =null;

        boolean validInput = false; //used to check for valid input
        while(!validInput){
            try{
                System.out.println("Please enter the number of players you wish to play the game , please enter a number between" +
                        " 2 and 8");
                while (!validInput) { //until input is valid
                    try {
                        numberOfPlayers = sc.nextInt();
                        validInput = true; // if valid break loop
                    } catch (InputMismatchException e) { //if input not an integer
                        System.out.println("Please enter an integer!");
                        sc.next();
                    }
                }
                System.out.println("Enter Safe if you want a safe map , or Hazardous if you want a hazardous map");
                mapType = sc.next();
                if (numberOfPlayers <= 4) { //Each player size has different minimum size of maps
                    System.out.println("Please enter the map size , the minimum size is 5 , the maximum number is 50");
                } else {
                    System.out.println("Please enter the map size , the minimum size is 8 , the maximum number is 50");
                }
                validInput = false;
                while (!validInput) { // iterate until user enters a valid number
                    try {
                        mapSize = sc.nextInt();
                        validInput = true;
                    } catch (InputMismatchException e) { //If input is not an integer
                        System.out.println("Please enter an integer!");
                        sc.next();
                    }
                }
                validInput = false;
                game = new GameEngine(mapSize,numberOfPlayers,mapType); //try to create a game object
                validInput = true;
            }catch (Exception e){ //if the parameters passed are not correct notify the user and iterate again untill
                                  //proper parameters are entered
                System.out.println("You did not enter input as specified");
            }
        }
        if(game != null){ //start the game if everything went well.
            game.StartGame();
        }
    }
}
