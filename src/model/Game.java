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
                //this.gp.getGrid().move(doMove.getX(), doMove.getY(), true);
                // X e Y sono invertite perchè sono stupido
                this.move(doMove.getX()+k, doMove.getY(), true);
                //System.out.println(k);
            }
        }
        else if(doMove.getMoveSx() > 0){ // sx
            for(int k = 0; k < doMove.getMoveSx(); k++){
                //this.gp.getGrid().move(doMove.getX(), doMove.getY(), false);
                // X e Y sono invertite perchè sono stupido
                this.move(doMove.getX()-k, doMove.getY(), false);
            }
        }
        //System.out.println("MUOVO");
        gravity();
    }

    public void move(int x, int y, boolean dir){
        //this.gp.getGrid().move(x, y, dir);
        Tile selected = this.gp.getGrid().getTilesObjects()[x][y];
        boolean moved = false;
        //System.out.print(x + " " + y +" -> ");System.out.println(selected);

        if(selected != null){
            if(dir){// true = dx
                if(selected.getX()<7 && !moved) {
                    if(this.gp.getGrid().getMatrix()[selected.getX()+1][selected.getY()] == 0 && this.gp.getGrid().getTilesObjects()[selected.getX()+1][selected.getY()] == null){
                        selected.setX(selected.getX() + 1);
                        moved = true;
                        this.gp.getGrid().updateGrid();
                        this.gp.repaint();
                        this.gp.sleep(pauses);
                    }
                }
                if(selected.getX()<6 && !moved) {
                    if(this.gp.getGrid().getMatrix()[selected.getX()+2][selected.getY()] == 0 && this.gp.getGrid().getTilesObjects()[selected.getX()+2][selected.getY()] == null){
                        selected.setX(selected.getX() + 1);
                        moved = true;

                        this.gp.getGrid().updateGrid();
                        this.gp.repaint();
                        this.gp.sleep(pauses);
                    }
                }
                if(selected.getX()<5 && !moved) {
                    if(this.gp.getGrid().getMatrix()[selected.getX()+3][selected.getY()] == 0 && this.gp.getGrid().getTilesObjects()[selected.getX()+3][selected.getY()] == null){
                        selected.setX(selected.getX() + 1);
                        moved = true;
                        this.gp.getGrid().updateGrid();
                        this.gp.repaint();
                        this.gp.sleep(pauses);
                    }
                }
            }
            else{  // false = sx
                if(selected.getX()>0) {
                    if(this.gp.getGrid().getMatrix()[selected.getX()-1][selected.getY()] == 0 && this.gp.getGrid().getTilesObjects()[selected.getX()-1][selected.getY()] == null){
                        selected.setX(selected.getX() - 1);
                        this.gp.getGrid().updateGrid();
                        this.gp.repaint();
                        this.gp.sleep(pauses);
                    }
                }
            }
        }

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
