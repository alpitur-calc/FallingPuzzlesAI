package view;

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

        g.setColor(Color.BLACK);
        for(int i = 0; i< this.grid.WIDTH; i++){
            for(int j = 0; j < this.grid.HEIGHT; j++){
                g.drawRect( i * DIM, j * DIM, DIM, DIM);

            }
        }

        for(Tile t : this.grid.getTiles()){
            switch(t.getType()){
                case(1): g.setColor(Color.RED); break;
                case(2): g.setColor(Color.GREEN); break;
                case(3): g.setColor(Color.YELLOW); break;
                case(4): g.setColor(Color.BLUE); break;
                default: g.setColor(Color.WHITE);
            }
            g.fillRect(t.getY() * DIM, t.getX() * DIM, DIM * t.getType(), DIM);
            g.setColor(Color.BLACK);
            g.drawRect(t.getY() * DIM, t.getX() * DIM, DIM * t.getType(), DIM);
        }
    }

    public void update() {
        this.grid.updateGrid();
        repaint();
    }

    public Grid getGrid() {
        return grid;
    }
}