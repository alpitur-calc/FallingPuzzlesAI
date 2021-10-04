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

    public void doMove(DoMove doMove){
        if(doMove.getMoveDx() > 0){ // dx
            for(int k = 0; k < doMove.getMoveDx(); k++){
                this.gp.getGrid().move(doMove.getX(), doMove.getY(), true);
            }
        }
        else if(doMove.getMoveSx() < 0){ // sx
            for(int k = 0; k > doMove.getMoveSx(); k--){
                this.gp.getGrid().move(doMove.getX(), doMove.getY(), false);
            }
        }
        gravity();
    }

    public void move(int x, int y, boolean dir){
        this.gp.getGrid().move(x, y, dir);
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
