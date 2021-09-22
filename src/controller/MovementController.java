package controller;

import model.Game;
import view.GraphicPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementController implements KeyListener {

    private GraphicPanel gp = null;
    public MovementController(GraphicPanel gp){
        super();
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R){
            Game.getInstance(this.gp).addRow();
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            Game.getInstance(this.gp).printMatrix();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            Game.getInstance(this.gp).getGraphicPanel().getGrid().move(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Game.getInstance(this.gp).getGraphicPanel().getGrid().move(true);
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            Game.getInstance(this.gp).fall();
        }
    }
}
