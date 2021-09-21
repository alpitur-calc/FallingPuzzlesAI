package model;

import view.GraphicPanel;

public class Game {

    private static Game instance = null;
    private GraphicPanel gp = null;

    private Game(GraphicPanel gp){
        this.gp = gp;
    }

    public static Game getInstance(GraphicPanel gp){
        if(instance == null){ instance = new Game(gp); }
        return instance;
    }

    public void addRow(){
        this.gp.getGrid().generateRow();
    }

    public void printMatrix(){
        this.gp.getGrid().printMatrix();
    }
    public void fall(){
        this.gp.getGrid().fall();
    }
}
