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
    private int moveDx;
    @Param(4)
    private int moveSx;

    public Move() {
    }

    public Move(int x, int y, int lenght, int moveDx, int moveSx) {
        this.x = x;
        this.y = y;
        this.lenght = lenght;
        this.moveDx = moveDx;
        this.moveSx = moveSx;
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

    public int getMoveDx() {
        return moveDx;
    }

    public void setMoveDx(int moveDx) {
        this.moveDx = moveDx;
    }

    public int getMoveSx() {
        return moveSx;
    }

    public void setMoveSx(int moveSx) {
        this.moveSx = moveSx;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                ", lenght=" + lenght +
                ", moveDx=" + moveDx +
                ", moveSx=" + moveSx +
                '}';
    }

    public String toFact(){
        return "move(" + x + "," + y + "," + lenght + "," + moveDx + "," + moveSx + ").";
    }
}
