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
    private int highestOccupiedRow = 9;

    public Grid() {
        matrix = new Integer[WIDTH][HEIGHT];
        prevmatrix = new Integer[WIDTH][HEIGHT];
        tilesObjects = new Tile[WIDTH][HEIGHT];
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
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++){
                matrix[i][j] = 0;
                tilesObjects[i][j] = null;
            }
        }
    }

    private void setPrevmatrix(){
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++){
                prevmatrix[i][j]= matrix[i][j];
            }
        }
    }

    public void generateRow(){
        this.rowsUp();
        int lenght= 0, maxLenght = 6, nspecial = 0; //, ntiles= 0;
        Random r = new Random();

        while(lenght < maxLenght){// && ntiles < 4){
            int type= 0, y= 9, x= r.nextInt(8);
            boolean special = false;
            //if(r.nextInt(100) % 15 == 0 && nspecial == 0){ special = true; } //possibilità di 1 su 10 di essere speciale
            int rand = r.nextInt(100);
            if(rand >=0 && rand < 25){ type = 1; }
            if(rand >=25 && rand < 75){ type = 2; }
            if(rand >=75 && rand < 100){ type = 3; }
            Tile tile= new Tile(type, x, y, special);

            if( (x + tile.getType())-1 < 8 && (tile.getType()+lenght) <= maxLenght){
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
        if(y>=10){ return does; }
        for(int k = x; k < x+type; k++){
            if(matrix[k][y] != 0){ does = true; }
        }
        return does;
    }

    private boolean areMatrixEqual(){
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if(!Objects.equals(matrix[i][j], prevmatrix[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    private void rowsUp(){
        for(Tile t : this.tiles){
            t.setY(t.getY()-1);
        }
        this.updateGrid();
    }

    public void fall(){
        setPrevmatrix();
        for(int i = this.tiles.size()-1; i>=0; i--){
            Tile t = tiles.get(i);
            if((t.getY()+1) < 10 & !overlap(t.getType(), t.getX(), t.getY()+1)){
                t.setY(t.getY()+1);
            }
            updateGrid();
        }
        if(areMatrixEqual()){falling = false;}
    }

    public void updateGrid(){
        resetMatrix();
        for(Tile t : this.tiles){
            //if(t.getY() == 0){ this.full = true; return;} // se trovo una tile sulla prima riga allora ho perso

            for(int k = t.getX(); k < t.getX()+t.getType(); k++){
                this.matrix[k][t.getY()]= t.getType();
                this.tilesObjects[k][t.getY()] = t;
            }
        }
    }

    public int checkFullRows(){
        int points = 0;
        for(int y = HEIGHT -1; y >=0; y--){
            int cont = 0;
            boolean isThereASpecial = false;
            int xSpecial = -1, ySpecial = -1, typeSpecial = -1;
            for(int x = WIDTH -1; x>=0; x--){
                if(matrix[x][y] != 0){
                    cont++;
                    if(tilesObjects[x][y].isSpecial()){
                        isThereASpecial = true;
                        xSpecial= tilesObjects[x][y].getX();
                        ySpecial= tilesObjects[x][y].getY();
                        typeSpecial= tilesObjects[x][y].getType();
                    }
                }
            }
            if(cont >=8){
                if (isThereASpecial) {
                    points+= 16; // la tile di per se aggiunge 16 punti extra
                    for(int k = xSpecial; k < xSpecial+typeSpecial; k++){
                        if(matrix[k][ySpecial-1]!=0){
                            int currX = tilesObjects[k][ySpecial-1].getX();
                            int currY = tilesObjects[k][ySpecial-1].getY();
                            int currType = tilesObjects[k][ySpecial-1].getType();
                            for(int j= currX; j< currX+currType; j++){
                                setSelectedTile(currX, currY);
                                matrix[j][currY]= 0;
                                tilesObjects[j][currY]= null;
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
                }
                for(int k = WIDTH -1; k>=0; k--){
                    setSelectedTile(k,y);
                    matrix[k][y]= 0;
                    tilesObjects[k][y]= null;
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

        //return this.full;
        for(int i = 0; i < WIDTH; i++){
            if(matrix[i][0] != 0){ return true; }
        }
        return false;
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

    public Tile[][] getTilesObjects() {
        return tilesObjects;
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public Vector<TileWrapper> getEmptyTiles(){
        Vector<TileWrapper> v = new Vector<TileWrapper>();
        int k=0;
        boolean top = false;
        for (int j = HEIGHT-1 ; j >= 0 && !top ; j--) {
            for (int i = WIDTH-1 ; i >=0 && k<8 ; i--){
                if( matrix[i][j] == 0){
                    v.add(new TileWrapper(i, j, 0));
                    k++;
                }
            }
            if(k>=8){ top = true; }
            else{ k=0; }
        }
        return v;
    }

    public Vector<Move> getAllPossibleMove(){
        Vector<Move> moves = new Vector<Move>();
        for(Tile t : tiles){

            //controllo a dx
            int dx = 0;
            for(int k = (t.getX() + t.getType()) ; k < WIDTH; k++){
                if(matrix[k][t.getY()] == 0){
                    dx++;
                    moves.add(new Move(t.getX(), t.getY(), t.getType(), dx, 0));
                }
                else{ break; }
            }

            //controllo a sx
            int sx=0;
            for(int j = t.getX() - 1; j >= 0; j--){
                if(matrix[j][t.getY()] == 0){
                    sx++;
                    moves.add(new Move(t.getX(),t.getY(),t.getType(), 0, sx));
                }
                else{ break; }
            }

        }

        return moves;
    }

    public void printMatrix(){
        for (int j = 0; j < HEIGHT; j++){
            String s= "";
            for (int i = 0; i < WIDTH; i++){
                s+= matrix[i][j] + " ";
            }
            System.out.println(s);
        }
    }

}
