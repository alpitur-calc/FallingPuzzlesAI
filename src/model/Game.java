package model;

import view.GraphicPanel;

public class Game {

    private static Game instance = null;
    private GraphicPanel gp = null;
    private int points = 0;
    private int pauses = 300;

    private Game(){}

    public static Game getInstance(){
        if(instance == null){ instance = new Game(); }
        return instance;
    }

    public void setGp(GraphicPanel gp) {
        this.gp = gp;
    }

    private void gravity(){
        gp.getGrid().setFalling(true);
        while(gp.getGrid().isFalling()){
            gp.getGrid().fall();
            gp.repaint();
            this.gp.sleep(pauses);
            points += gp.getGrid().checkFullRows();
            gp.repaint();
            this.gp.sleep(pauses);
        }
        this.gp.getGrid().resetMultiplier();
    }

    public void newRow(){
        this.gp.getGrid().generateRow();
        this.gp.repaint();
        this.gp.sleep(pauses);
        gravity();
    }

    /*public void activateGravity(){
        gravity();
        newRow();
    }*/

    public void doMove(Move move){
        if(move.getMove() > 0){ // dx
            for(int k = 0; k < move.getMove(); k++){
                this.gp.getGrid().move(true);
            }
        }
        else if(move.getMove() < 0){
            for(int k = 0; k > move.getMove(); k--){
                this.gp.getGrid().move(false);
            }
        }
        gravity();
    }

    public void move(boolean dir){
        this.gp.getGrid().move(dir);
    }

    public void printMatrix(){
        this.gp.getGrid().printMatrix();
    }

    public void selectTile(int x, int y){
        this.gp.getGrid().setSelectedTile(x, y);
    }

    public int getPoints(){
        return this.points;
    }

    public boolean isDead(){
        return this.gp.getGrid().isFull();
    }

    public void reset(){
        this.gp.resetGrid();
        this.points = 0;
    }
    /*public void addRow(){
        this.gp.getGrid().generateRow();
    }

    public GraphicPanel getGraphicPanel(){ return gp; }

    public void fall(){
        points += this.gp.getGrid().fall();
    }
    */
}
