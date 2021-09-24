package model;

public class Tile {

    public static final Integer TYPE_1= 1, TYPE_2= 2, TYPE_3= 3, TYPE_SPECIAL= 5;
    private Integer type= null;
    private Integer x, y;
    private boolean highlighted = false, special = false;

    public Tile(Integer type, Integer x, Integer y, boolean special){
        this.type = type;
        this.x= x;
        this.y= y;
        this.special = special;
    }

    public Integer getType() {
        return type;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isSpecial(){ return this.special; }
}
