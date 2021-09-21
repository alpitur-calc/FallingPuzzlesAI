import view.GraphicPanel;

public class GameLoop implements Runnable{

    private int frequency = 60; // 60 FPS

    public GameLoop(GraphicPanel gp){
        //TODO
    }

    @Override
    public void run() {
        while(true) {
            //TODO
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}