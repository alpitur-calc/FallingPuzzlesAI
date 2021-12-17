import controller.MouseController;
import controller.MovementController;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.*;
import view.GraphicPanel;

import javax.swing.*;
import java.util.Vector;

public class GameLoop implements Runnable{

    private static final int WIDTH= 425, HEIGHT= 550;

    private int frequency = 10000; // 60 FPS
    private GraphicPanel gp = null;
    private Boolean canContinue = true;

    private static String encodingResource="encodings/logica.txt";
    private Handler handler;
    private InputProgram encoding;

    public GameLoop(GraphicPanel gp){
        this.gp= gp;
    }

    public void main(){
        handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
        try {
            ASPMapper.getInstance().registerClass(Move.class);
            ASPMapper.getInstance().registerClass(TileWrapper.class);
            ASPMapper.getInstance().registerClass(DoMove.class);
        } catch (ObjectNotValidException | IllegalAnnotationException e1) {
            e1.printStackTrace();
        }
        encoding = new ASPInputProgram();
        encoding.addFilesPath(encodingResource);

        JFrame f = new JFrame();
        f.setTitle("Falling Puzzles AI");
        f.setSize(GameLoop.WIDTH, GameLoop.HEIGHT);

        gp.addKeyListener(new MovementController(gp));
        gp.addMouseListener(new MouseController(gp));
        gp.setFocusable(true);

        f.add(gp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    private void addFacts(){
        InputProgram facts= new ASPInputProgram();

        // Passo tutte le moves possibili come fatti
        for(Move v: this.gp.getGrid().getAllPassibleMove()){
            try{
                //System.out.println(v);
                facts.addObjectInput(v);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        for(Tile t: this.gp.getGrid().getTiles()){
            try{
                facts.addObjectInput(new TileWrapper(t.getX(), t.getY(), t.getType()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        for(Tile t: this.gp.getGrid().getEmptyTiles()){
            try{
                facts.addObjectInput(new TileWrapper(t.getX(), t.getY(), t.getType()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        handler.addProgram(facts);
    }

    @Override
    public void run() {
        Game.getInstance().setGp(gp);
        Game.getInstance().newRow();
        while(true) {

            Game.getInstance().newRow();

            handler.removeAll();
            handler.addProgram(encoding);
            addFacts();
            Output o =  handler.startSync();
            AnswerSets answersets = (AnswerSets) o;

            for(AnswerSet AS: answersets.getAnswersets()){
                System.out.println("ANS:");
                try {
                    for (Object obj : AS.getAtoms()) {
                        //System.out.println("Obj :" + obj);

                        //if(obj instanceof Move ) { System.out.println(obj); }

                        if(obj instanceof DoMove) {
                            DoMove m = (DoMove) obj;
                            System.out.println(m);
                            Game.getInstance().doMove(m);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(Game.getInstance().isDead()){Game.getInstance().reset();}
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                break;
            }

        }

        System.out.println("DEAD");
    }
}