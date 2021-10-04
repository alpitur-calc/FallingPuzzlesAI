package model;

import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Grid {

    public final int WIDTH = 8, HEIGHT = 10;
    private Integer[][] matrix;
    private Integer[][] prevmatrix;
    private Tile[][] tilesObjects;
    private Vector<Tile> tiles;
    private boolean full = false, falling = false;
    private Tile selected = null;
    private int multiplier = 1;

    public Grid() {
        matrix = new Integer[HEIGHT][WIDTH];
        prevmatrix = new Integer[HEIGHT][WIDTH];
        tilesObjects = new Tile[HEIGHT][WIDTH];
        resetMatrix();
        tiles = new Vector<Tile>();
        updateGrid();
    }

    public void setSelectedTile(int x, int y){

        //System.out.println(x+" " +y);
        if(selected != null){ selected.setHighlighted(false); }
        selected = tilesObjects[x][y];
        if(selected != null){ selected.setHighlighted(true); }
    }

    private void resetMatrix(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++){
                matrix[i][j] = 0;
                tilesObjects[i][j] = null;
            }
        }
    }

    private void setPrevmatrix(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++){
                prevmatrix[i][j]= matrix[i][j];
            }
        }
    }

    public void generateRow(){
        this.rowsUp();
        int lenght= 0, maxLenght = 6, nspecial = 0; //, ntiles= 0;
        Random r = new Random();

        while(lenght < maxLenght){// && ntiles < 4){
            int type= r.nextInt(3)+1, x= 9, y= r.nextInt(8);
            boolean special = false;
            //if(r.nextInt(100) % 15 == 0 && nspecial == 0){ special = true; } //possibilità di 1 su 10 di essere speciale
            Tile tile= new Tile(type, x, y, special);

            if( (y + tile.getType())-1 < 8 && (tile.getType()+lenght) <= maxLenght){
                if(!this.overlap(tile.getType(), tile.getX(), tile.getY())){
                    lenght+= tile.getType();
                    //ntiles+= 1;
                    this.tiles.add(tile);
                    if(tile.isSpecial()){ nspecial++; }
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

    private boolean areMatrixEqual(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(!Objects.equals(matrix[i][j], prevmatrix[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    private void rowsUp(){
        for(Tile t : this.tiles){
            t.setX(t.getX()-1);
        }
        this.updateGrid();
    }

    public void fall(){
        setPrevmatrix();
        for(int i = this.tiles.size()-1; i>=0; i--){
            Tile t = tiles.get(i);
            boolean overlap = false;
            if((t.getX()+1) < 10 & !overlap(t.getType(), t.getX()+1, t.getY())){
                t.setX(t.getX()+1);
            }
            updateGrid();
        }
        if(areMatrixEqual()){falling = false;}
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

    public void move(int x, int y, boolean dir){
        Tile selected = tilesObjects[x][y];
        if(selected != null){
            if(dir){// true = dx
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

    public int checkFullRows(){
        int points = 0;
        for(int x = HEIGHT -1; x >=0; x--){
            int cont = 0;
            boolean isThereASpecial = false;
            int xSpecial = -1, ySpecial = -1, typeSpecial = -1;
            for(int y = WIDTH -1; y>=0; y--){
                if(matrix[x][y] != 0){
                    cont++;
                    /*if(tilesObjects[x][y].isSpecial()){
                        isThereASpecial = true;
                        xSpecial= tilesObjects[x][y].getX();
                        ySpecial= tilesObjects[x][y].getY();
                        typeSpecial= tilesObjects[x][y].getType();
                    }*/
                }
            }
            if(cont >=8){
                /*if (isThereASpecial) {
                    points+= 16; // la tile di per se aggiunge 16 punti extra
                    for(int k = ySpecial; k < ySpecial+typeSpecial; k++){
                        if(matrix[xSpecial-1][k]!=0){
                            int currX = tilesObjects[xSpecial-1][k].getX();
                            int currY = tilesObjects[xSpecial-1][k].getY();
                            int currType = tilesObjects[xSpecial-1][k].getType();
                            for(int j= currY; j< currY+currType; j++){
                                setSelectedTile(currX, currY);
                                matrix[xSpecial][j]= 0;
                                tilesObjects[xSpecial][j]= null;
                                if(selected != null){
                                    boolean done = tiles.remove(selected);
                                    if(done) points+=8;  // per ogni tile eliminata della special aggiungo 8
                                }
                            }
                        }
                        if(xSpecial+1 < 10){
                            if(matrix[xSpecial+1][k]!=0){
                                int currX = tilesObjects[xSpecial+1][k].getX();
                                int currY = tilesObjects[xSpecial+1][k].getY();
                                int currType = tilesObjects[xSpecial+1][k].getType();
                                for(int j= currY; j< currY+currType; j++){
                                    setSelectedTile(currX, currY);
                                    matrix[xSpecial][j]= 0;
                                    tilesObjects[xSpecial][j]= null;
                                    if(selected != null){
                                        boolean done = tiles.remove(selected);
                                        if(done) points+=8;  // per ogni tile eliminata della special aggiungo 8
                                    }
                                }
                            }
                        }
                    }
                }*/
                for(int k = WIDTH -1; k>=0; k--){
                    setSelectedTile(x,k);
                    matrix[x][k]= 0;
                    tilesObjects[x][k]= null;
                    if(selected != null){
                        tiles.remove(selected);
                    }
                }
                points+=16 * multiplier; // 16 punti ogni riga eliminata * multiplier;
                multiplier+=1;
                updateGrid();
            }
        }
        return points;
    }

    public void resetMultiplier(){ this.multiplier= 1; }

    public boolean isFull(){
        return this.full;
    }

    public void setFalling(boolean f){
        this.falling= f;
    }

    public boolean isFalling(){
        return falling;
    }

    public Vector<Tile> getTiles(){
        return this.tiles;
    }

    public Vector<Tile> getEmptyTiles(){
        Vector<Tile> v = new Vector<Tile>();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++){
                if( matrix[i][j] == 0){ v.add(new Tile(0, i, j, false)); }
            }
        }
        return v;
    }

    public Vector<Move> getAllPassibleMove(){
        Vector<Move> moves = new Vector<Move>();
        for(Tile t : tiles){

            //controllo a dx
            int dx = 0;
            for(int k = (t.getY() + t.getType()) ; k < WIDTH; k++){
                if(matrix[t.getX()][k] == 0){
                    dx++;
                    moves.add(new Move(t.getX(), t.getY(), t.getType(), dx, 0));
                }
                else{ break; }
            }

            //controllo a dx
            int sx=0;
            for(int j = t.getY() - 1; j >= 0; j--){
                if(matrix[t.getX()][j] == 0){
                    sx++;
                    moves.add(new Move(t.getX(),t.getY(),t.getType(), 0, sx));
                }
                else{ break; }
            }

        }

        return moves;
    }

    public void printMatrix(){
        for (int i = 0; i < HEIGHT; i++){
            String s= "";
            for (int j = 0; j < WIDTH; j++){
                s+= matrix[i][j] + " ";
            }
            System.out.println(s);
        }
    }

}
