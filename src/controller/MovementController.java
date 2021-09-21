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
        if(e.getKeyCode() == KeyEvent.VK_A){
            Game.getInstance(this.gp).addRow();
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            Game.getInstance(this.gp).printMatrix();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
