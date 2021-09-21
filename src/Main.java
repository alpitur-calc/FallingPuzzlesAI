import controller.MovementController;
import model.Grid;
import view.GraphicPanel;

import javax.swing.*;

public class Main {

    private static final int WIDTH= 425, HEIGHT= 550;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Falling Puzzles AI");
        f.setSize(Main.WIDTH, Main.HEIGHT);

        GraphicPanel gp = new GraphicPanel(new Grid());
        gp.addKeyListener(new MovementController(gp));
        gp.setFocusable(true);

        f.add(gp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        GameLoop gameLoop = new GameLoop(gp);
        Thread t = new Thread(gameLoop);
        t.start();

    }

}
