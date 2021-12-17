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

    public TileWrapper(){}

    public TileWrapper(int x, int y, int type) {
        this.x = y;
        this.y = x;
        this.type = type;
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

    @Override
    public String toString() {
        return "TileWrapper{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
