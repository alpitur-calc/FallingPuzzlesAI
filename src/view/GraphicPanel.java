package view;

import model.Game;
import model.Grid;
import model.Tile;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {
    private final int DIM = 50;
    private Grid grid = null;

    public GraphicPanel(Grid grid){
        super();
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.invalidate();

        g.setColor(Color.BLACK);
        for(int i = 0; i < this.grid.WIDTH; i++){
            for(int j = 0; j < this.grid.HEIGHT; j++){
                g.drawRect( i * DIM, j * DIM, DIM, DIM);

            }
        }

        for(Tile t : this.grid.getTiles()){
            switch(t.getType()){
                case(1): g.setColor(Color.RED); break;
                case(2): g.setColor(Color.GREEN); break;
                case(3): g.setColor(Color.BLUE); break;
                default: g.setColor(Color.WHITE);
            }
            if(t.isSpecial()){ g.setColor(Color.MAGENTA); }
            g.fillRect(t.getX() * DIM, t.getY() * DIM, DIM * t.getType(), DIM);
            if(t.isHighlighted()){
                g.setColor(Color.YELLOW);
                g.drawRect(t.getX() * DIM, t.getY() * DIM, DIM * t.getType(), DIM);
            }
            else {
                g.setColor(Color.BLACK);
                g.drawRect(t.getX() * DIM, t.getY() * DIM, DIM * t.getType(), DIM);
            }
        }

        g.setColor(Color.BLACK);
        g.drawString("Points"+ Game.getInstance().getPoints(), 180,510 );
    }

    public void update() {
        repaint();
    }

    public void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void resetGrid(){ this.grid= new Grid(); }
}