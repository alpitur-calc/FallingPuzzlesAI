import controller.MovementController;
import view.GraphicPanel;

import javax.swing.*;

public class Main {

    private static final int WIDTH= 1280, HEIGHT= 720;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Falling Puzzles AI");
        f.setSize(Main.WIDTH, Main.HEIGHT);

        GraphicPanel gp = new GraphicPanel();
        gp.addKeyListener(new MovementController());
        gp.setFocusable(true);

        f.add(gp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        GameLoop gameLoop = new GameLoop(gp);
        Thread t = new Thread(gameLoop);
        t.start();
    }

}

/*
package application;

import javax.swing.JFrame;

import application.controller.MovementController;
import application.view.GraphicPanel;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Swing animation");
		f.setSize(800,800);
		GraphicPanel gp = new GraphicPanel();
		gp.addKeyListener(new MovementController());
		gp.setFocusable(true);
		f.add(gp);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		GameLoop gameLoop = new GameLoop(gp);
		Thread t = new Thread(gameLoop);
		t.start();
	}

}
 */
