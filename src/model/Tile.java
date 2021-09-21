package model;

public class Tile {

    public static final Integer TYPE_1= 1, TYPE_2= 2, TYPE_3= 3, TYPE_SPECIAL= 5;
    private Integer type= null;
    private Integer x, y;

    public Tile(Integer type, Integer x, Integer y){
        this.type = type;
        this.x= x;
        this.y= y;
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
}
