package Part2_Multiplayer_Game;

/**
 * Created by Dylan Galea and Gabriel Camilleri on 20/03/2018.
 */
public class Position {
    private int x;
    private int y;

    public Position(){}

    public Position(int x ,int y){
        setX(x);
        setY(y);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
