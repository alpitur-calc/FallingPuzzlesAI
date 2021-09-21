package view;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void update() {
        repaint();
    }
}