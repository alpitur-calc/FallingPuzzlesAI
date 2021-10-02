package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("move")
public class Move {

    @Param(0)
    private int x;
    @Param(1)
    private int y;
    @Param(2)
    private int lenght;
    @Param(3)
    private int move;

    public Move() {
    }

    public Move(int x, int y, int lenght, int move) {
        this.x = x;
        this.y = y;
        this.lenght = lenght;
        this.move = move;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
