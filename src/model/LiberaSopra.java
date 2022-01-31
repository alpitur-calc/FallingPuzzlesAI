package model;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("liberaSopra")
public class LiberaSopra {

    @Param(0)
    private int x;
    @Param(1)
    private int y;
    @Param(2)
    private int t;
    @Param(3)
    private int dx;
    @Param(4)
    private int sx;

    public LiberaSopra(){}

    public LiberaSopra(int x, int y, int t, int dx, int sx) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.dx = dx;
        this.sx = sx;
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

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getSx() {
        return sx;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    @Override
    public String toString() {
        /*return "LiberaSopra{" +
                "x=" + x +
                ", y=" + y +
                ", t=" + t +
                ", dx=" + dx +
                ", sx=" + sx +
                '}';*/
        return "liberaSopra(" + x + "," + y + "," + t + "," + dx +","+ sx +").";

    }

    public String toFact(){
        return "tile(" + x + "," + y + "," + t + "," + dx +","+ sx +").";
    }
}
