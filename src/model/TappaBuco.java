package model;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("tappaBuco")
public class TappaBuco {

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
    @Param(5)
    private int spazivuoti;
    @Param(6)
    private int colonne;


    public TappaBuco(){}

    public TappaBuco(int x, int y, int t, int dx, int sx, int spazivuoti, int colonne) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.dx = dx;
        this.sx = sx;
        this.spazivuoti = spazivuoti;
        this.colonne = colonne;
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

    public int getSpazivuoti() {
        return spazivuoti;
    }

    public void setSpazivuoti(int spazivuoti) {
        this.spazivuoti = spazivuoti;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        /*return "TappaBuco{" +
                "x=" + x +
                ", y=" + y +
                ", t=" + t +
                ", dx=" + dx +
                ", sx=" + sx +
                ", spazivuoti=" + spazivuoti +
                ", colonne=" + colonne +
                '}';*/
        return "tappaBuco(" + x + "," + y + "," + t + "," + dx + "," + sx + "," +spazivuoti+ "," +colonne+").";
    }

    public String toFact(){
        return "tappaBuco(" + x + "," + y + "," + t + "," + dx + "," + sx + "," +spazivuoti+ "," +colonne+").";
    }
}
