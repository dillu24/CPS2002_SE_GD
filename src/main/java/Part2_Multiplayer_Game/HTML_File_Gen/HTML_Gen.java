package Part2_Multiplayer_Game.HTML_File_Gen;

import Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern.TreasureFinderPlayer;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;

import java.awt.Desktop;
import java.io.*;

public class HTML_Gen {

    public HTML_Gen(){}

    //Constructor used to call the private methods found in the class
    /**
     * This constructor is used to run the generate player file method found in the class. Thus the game engine class
     * can simply pass the parameters and call the constructor to generate the HTML File of a specific player.
     * @param players
     * Stores the information of all the players
     * @param teamNo
     * stores the index of the team currently playing the game
     * @param playerNo
     * stores the index of the player currently playing the game
     * @param turnNo
     * stores the current turn number the players are on
     * @param mapSize
     * stores the size of the map of 1 dimension (given it's a square it will be (mapSize x mapSize)
     * @param map
     * stores the actual map of the player. This is of type Map.
     * @param isVisited
     * stores a 2-d array of boolean, which determines which tiles have been already visited. Will be used to indicate
     * that if visited the tile colour should show in the HTML file.
     * @throws IOException
     * Whenever there occurs a failed or interrupted I/O operations.
     */
    public HTML_Gen(TreasureFinderPlayer [] players, int teamNo, int playerNo, int turnNo, int mapSize, Map map, boolean[][] isVisited) throws IOException {
        generatePlayerFile(players, teamNo, playerNo, turnNo, mapSize, map, isVisited);
    }
    public HTML_Gen(TreasureFinderPlayer [] players, int playerNo, int turnNo, int mapSize, Map map, boolean[][] isVisited) throws IOException {
        generatePlayerFile(players, -1, playerNo, turnNo, mapSize, map, isVisited);
    }

    /**
     * This method will run the methods relating for creation and writing of html files of the player map
     * and information inputted.
     * @param players
     * Stores the information of all the players
     * @param teamNo
     * stores the index of the team currently playing the game
     * @param playerNo
     * stores the index of the player currently playing the game
     * @param turnNo
     * stores the current turn number the players are on
     * @param mapSize
     * stores the size of the map of 1 dimension (given it's a square it will be (mapSize x mapSize)
     * @param map
     * stores the actual map of the player. This is of type Map.
     * @param isVisited
     * stores a 2-d array of boolean, which determines which tiles have been already visited. Will be used to indicate
     * that if visited the tile colour should show in the HTML file.
     * @throws IOException
     * Whenever there occurs a failed or interrupted I/O operations.
     */
    public File generatePlayerFile(TreasureFinderPlayer[] players, int teamNo, int playerNo, int turnNo,
                                           int mapSize, Map map, boolean[][] isVisited) throws IOException {

        String file = "map_player_" + (playerNo+1) + ".html"; //File Name of this player map

        File f = new File(file);

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        writeHTMLFile(bw, players, teamNo, playerNo, turnNo, mapSize, map, isVisited);
        return f;
    }

    /**
     * This method will output the html files of the player map.
     * @param f
     * Stores the player file to be displayed
     * @throws IOException
     * Whenever there occurs a failed or interrupted I/O operations.
     */
    public void displayFile(File f)throws IOException{
        Desktop.getDesktop().browse(f.toURI());
    }

    /**
     * This method will handle the writing of html files of the player map and information inputted.
     * @param players
     * Stores the information of all the players
     * @param teamNo
     * stores the index of the player currently playing the game
     * @param playerNo
     * stores the index of the player currently playing the game
     * @param turnNo
     * stores the current turn number the players are on
     * @param mapSize
     * stores the size of the map of 1 dimension (given it's a square it will be (mapSize x mapSize)
     * @param map
     * stores the actual map of the player. This is of type Map.
     * @param isVisited
     * stores a 2-d array of boolean, which determines which tiles have been already visited. Will be used to indicate
     * that if visited the tile colour should show in the HTML file.
     * @throws IOException
     * Whenever there occurs a failed or interrupted I/O operations.
     */
    private void writeHTMLFile(BufferedWriter bw, TreasureFinderPlayer[] players, int teamNo, int playerNo, int turnNo,
                               int mapSize, Map map, boolean[][] isVisited) throws IOException{
        int i,j;
        int tileSize = 20;
        int borderSize = 3;

        String tileColour = "";
        String player = "";

        bw.write("<!DOCTYPE html>");
        bw.write("<html>");
        bw.write("<head>");
        bw.write("<style>");
        bw.write("table {");
        bw.write("    border-collapse: collapse;");
        bw.write("}");
        bw.write("td, tr{");
        bw.write("    border: "+borderSize+"px solid black;");
        bw.write("    border-collapse: collapse;");
        bw.write("    height: "+tileSize+"px;");
        bw.write("    width: "+tileSize+"px;");
        bw.write("}");
        bw.write("</style>");
        bw.write("</head>");
        bw.write("<body>");
        if(teamNo>=0) {
            bw.write("<h2>Team #" + (teamNo + 1) + " Player #" + (playerNo + 1) + " Map:" + " Turn #" + turnNo + "</h2>");
        }else{
            bw.write("<h2>Player #" + (playerNo + 1) + " Map:" + " Turn #" + turnNo + "</h2>");
        }
        bw.write("<table>");
        for(i = 0; i<mapSize; i++){
            bw.write("<tr>");
            for(j = 0; j<mapSize; j++){

                tileColour = getTileColour(i,j,map,isVisited);
                player = isPlayerHere(players,playerNo,i,j);
                bw.write("<td "+tileColour+">"+player+"</td>");
            }
        }
        bw.write("</table>");
        bw.write("</body>");
        bw.write("</html>");
        bw.close();
    }

    /**
     * This method is used to insert HTML code regarding the background colour of each tile in the map.
     * @param i
     * Stores the row number of the tile.
     * @param j
     * stores the column number of the tile.
     * @param map
     * stores the actual map of the player. This is of type Map.
     * @param isVisited
     * stores a 2-d array of boolean, which determines which tiles have been already visited. Will be used to indicate
     * that if visited the tile colour should show in the HTML file.
     * @returns
     * The background colour of the tile specified
     */
     public String getTileColour(int i,int j,Map map, boolean [][] isVisited){
        char colour = 'X';

        if(isVisited[i][j])
            colour = map.getTileType(i,j);
        switch(colour){
            case 'G':
                return "bgcolor = #00FF00";
            //break;
            case 'W':
                return "bgcolor = #00FFFF";
            //break;
            case 'T':
                return "bgcolor = #FFFF00";
            //break;
            case 'X':
                return "bgcolor = #D6D6D6";
            //break;
            default:
                break;
        }
        return "";
    }
    /**
     * This method is used to insert HTML code regarding the existence of a player in each tile in the map.
     * @param players
     * stores the information of all players
     * @param pNo
     * stores the index of the current player.
     * @param i
     * Stores the row number of the tile.
     * @param j
     * stores the column number of the tile.
     * @returns
     * A string if the player is in the tile or not
     */
    public String isPlayerHere(TreasureFinderPlayer[]players, int pNo, int i, int j){
        if(i == players[pNo].getPosition().getX() && j == players[pNo].getPosition().getY()){
            return "P"+(pNo+1);
        }
        return "";
    }
}
