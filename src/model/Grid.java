package model;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    public final int WIDTH = 8, HEIGHT = 10;
    private Integer[][] matrix ;
    private ArrayList<Tile> tiles;
    private boolean full = false;

    public Grid() {
        matrix = new Integer[HEIGHT][WIDTH];
        resetMatrix();
        tiles = new ArrayList<Tile>();
        // Tiles di prova, giusto per vedere se le disegna bene
        /*Tile t = new Tile(1, 5,0);
        tiles.add(t);
        Tile t2 = new Tile(2, 3,4);
        tiles.add(t2);
        Tile t3 = new Tile(3, 4,3);
        tiles.add(t3);
        Tile t4 = new Tile(4, 2,2);
        tiles.add(t4);*/
        updateGrid();
    }

    private void resetMatrix(){
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                matrix[i][j] = 0;
    }

    public void generateRow(){
        this.rowsUp();
        int lenght= 0, maxLenght = 6; //, ntiles= 0;
        Random r = new Random();

        while(lenght < maxLenght){// && ntiles < 4){
            int type= r.nextInt(3)+1, x= 9, y= r.nextInt(8);
            Tile tile= new Tile(type, x, y);
            //System.out.println("Tile :"+ tile.getType()+ " "+ tile.getX() +" " +tile.getY());
            if( (y + tile.getType())-1 < 8 && (tile.getType()+lenght) <= maxLenght ){
                if(!this.overlap(tile)){
                    lenght+= tile.getType();
                    //ntiles+= 1;
                    this.tiles.add(tile);
                    this.updateGrid();
                }
            }
        }
    }

    private boolean overlap(Tile t){
        boolean does = false;
        for(int k = t.getY(); k < t.getY()+t.getType(); k++){
            if(matrix[t.getX()][k] != 0){ does = true; }
        }

        return does;
    }

    private void rowsUp(){
        for(Tile t : this.tiles){
            t.setX(t.getX()-1);
        }
        this.updateGrid();
    }

    public void updateGrid(){
        resetMatrix();
        for(Tile t : this.tiles){
            if(t.getX() == 0){ this.full = true; return;} // se trovo una tile sulla prima riga allora ho perso

            for(int k = t.getY(); k < t.getY()+t.getType(); k++){
                this.matrix[t.getX()][k]= t.getType();
            }
        }
    }

    public boolean isFull(){
        return this.full;
    }

    public ArrayList<Tile> getTiles(){
        return this.tiles;
    }

    public void printMatrix(){
        for (int i = 0; i < HEIGHT; i++){
            String s= "";
            for (int j = 0; j < WIDTH; j++){
                s+= matrix[i][j] + " ";
            }
            System.out.println(s);
        }
        System.out.println("");
    }

}
