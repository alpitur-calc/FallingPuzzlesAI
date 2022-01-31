import model.Grid;
import view.GraphicPanel;


public class Main {

    public static void main(String[] args) {
        GraphicPanel gp = new GraphicPanel(new Grid());;

        GameLoop gameLoop = new GameLoop(gp);
        gameLoop.main();
        Thread t = new Thread(gameLoop);
        t.start();
    }

}
