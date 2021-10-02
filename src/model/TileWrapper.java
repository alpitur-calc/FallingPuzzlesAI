package model;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("tile")
public class TileWrapper {

    @Param(0)
    private int x;
    @Param(1)
    private int y;
    @Param(2)
    private int type;
    @Param(3)
    private int move;

    public TileWrapper(){}

    public TileWrapper(int x, int y, int type, int move) {
        this.x = x;
        this.y = y;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
