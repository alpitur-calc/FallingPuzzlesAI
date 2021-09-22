package model;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    public final int WIDTH = 8, HEIGHT = 10;
    private Integer[][] matrix ;
    private Tile[][] tilesObjects;
    private ArrayList<Tile> tiles;
    private boolean full = false;
    private Tile selected = null;

    public Grid() {
        matrix = new Integer[HEIGHT][WIDTH];
        tilesObjects = new Tile[HEIGHT][WIDTH];
        resetMatrix();
        tiles = new ArrayList<Tile>();
        updateGrid();
    }

    public void setSelectedTile(int x, int y){
        if(selected != null){ selected.setHighlighted(false); }
        selected = tilesObjects[x][y];
        if(selected != null){ selected.setHighlighted(true); }
    }

    private boolean isATileStart(int type, int x, int y){
        for(Tile t: tiles){
            if(t.getType() == type && t.getX() == x && t.getY() == x){
                return true;
            }
        }
        return false;
    }

    private void resetMatrix(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++){
                matrix[i][j] = 0;
                tilesObjects[i][j] = null;
            }
        }
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
                if(!this.overlap(tile.getType(), tile.getX(), tile.getY())){
                    lenght+= tile.getType();
                    //ntiles+= 1;
                    this.tiles.add(tile);
                    this.updateGrid();
                }
            }
        }
    }

    private boolean overlap(int type, int x, int y){
        boolean does = false;
        if(x>=10){ return does; }
        for(int k = y; k < y+type; k++){
            if(matrix[x][k] != 0){ does = true; }
        }

        return does;
    }

    private void rowsUp(){
        for(Tile t : this.tiles){
            t.setX(t.getX()-1);
        }
        this.updateGrid();
        //this.fall();
    }

    public void fall(){
        for(int i = this.tiles.size()-1; i>=0; i--){
            Tile t = tiles.get(i);
            boolean overlap = false;
            while((t.getX()+1) < 10 & !overlap(t.getType(), t.getX()+1, t.getY())){
                t.setX(t.getX()+1);
            }
            updateGrid();
        }
        checkFullRows();
    }

    public void updateGrid(){
        resetMatrix();
        for(Tile t : this.tiles){
            if(t.getX() == 0){ this.full = true; return;} // se trovo una tile sulla prima riga allora ho perso

            for(int k = t.getY(); k < t.getY()+t.getType(); k++){
                this.matrix[t.getX()][k]= t.getType();
                this.tilesObjects[t.getX()][k] = t;
            }
        }
    }

    public void move(boolean dir){
        if(selected != null){
            if(dir){;// true = dx
                if(selected.getY()<7) {
                    if(matrix[selected.getX()][selected.getY()+1] == 0 && tilesObjects[selected.getX()][selected.getY()+1] == null){
                        selected.setY(selected.getY() + 1);
                        updateGrid();
                    }
                }
                if(selected.getY()<6) {
                    if(matrix[selected.getX()][selected.getY()+2] == 0 && tilesObjects[selected.getX()][selected.getY()+2] == null){
                        selected.setY(selected.getY() + 1);
                        updateGrid();
                    }
                }
                if(selected.getY()<5) {
                    if(matrix[selected.getX()][selected.getY()+3] == 0 && tilesObjects[selected.getX()][selected.getY()+3] == null){
                        selected.setY(selected.getY() + 1);
                        updateGrid();
                    }
                }
            }
            else{  // false = sx
                if(selected.getY()>0) {
                    if(matrix[selected.getX()][selected.getY()-1] == 0 && tilesObjects[selected.getX()][selected.getY()-1] == null){
                        selected.setY(selected.getY() - 1);
                        updateGrid();
                    }
                }
            }
        }
    }

    public void checkFullRows(){
        for(int x = HEIGHT -1; x >=0; x--){
            int cont = 0;
            for(int y = WIDTH -1; y>=0; y--){
                if(matrix[x][y] != 0){ cont++; }
            }
            //System.out.println(x +" "+ cont);
            if(cont >= 7){
                for(int k = WIDTH -1; k>=0; k--){
                    //System.out.println("ciao");
                    setSelectedTile(x,k);
                    matrix[x][k]= 0;
                    tilesObjects[x][k]= null;
                    if(selected != null){
                        boolean done = tiles.remove(selected);
                        //if(done) System.out.println("eliminato");
                    }
                }
                updateGrid();
                fall();
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
