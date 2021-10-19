package model;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("freeSpaceFT")
public class FreeSpaceFT {

    @Param(0)
    private int from;
    @Param(1)
    private int to;
    @Param(2)
    private int x;

    public FreeSpaceFT() {
    }

    public FreeSpaceFT(int from, int to, int x) {
        this.from = from;
        this.to = to;
        this.x = x;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "FreeSpaceFT{" +
                "from=" + from +
                ", to=" + to +
                ", x=" + x +
                '}';
    }
}
