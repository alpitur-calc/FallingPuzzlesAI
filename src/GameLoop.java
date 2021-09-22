import view.GraphicPanel;

public class GameLoop implements Runnable{

    private int frequency = 2; // 60 FPS
    private GraphicPanel gp = null;

    public GameLoop(GraphicPanel gp){
        this.gp= gp;
    }

    @Override
    public void run() {
        while(!gp.getGrid().isFull()) {
            gp.update();
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("DEAD");
    }
}