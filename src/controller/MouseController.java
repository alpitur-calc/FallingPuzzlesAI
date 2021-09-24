package controller;

import model.Game;
import view.GraphicPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {

    private GraphicPanel gp;

    public MouseController(GraphicPanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (e.getY() / 50) % 10;
        int y = (e.getX() / 50) % 10;
        Game.getInstance().selectTile(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
