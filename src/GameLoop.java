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
import java.io.FileReader;

public class GameLoop implements Runnable{

    private static final int WIDTH= 425, HEIGHT= 550;

    private int iterations = 0;
    private int frequency = 1000; // 60 FPS
    private GraphicPanel gp = null;
    private Boolean canContinue = true;

    private static String encodingResource="encodings/logica.txt";
    private Handler handler;
    private InputProgram encoding;

    public GameLoop(GraphicPanel gp){
        this.gp= gp;
    }

    public void main(){
        // Creo l'handler
        handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));

        // Registro tutte le classi dei fatti
        try {
            ASPMapper.getInstance().registerClass(Move.class);
            ASPMapper.getInstance().registerClass(TileWrapper.class);
            ASPMapper.getInstance().registerClass(DoMove.class);
            ASPMapper.getInstance().registerClass(TappaBuco.class);
            ASPMapper.getInstance().registerClass(LiberaSopra.class);
        } catch (ObjectNotValidException | IllegalAnnotationException e1) {
            e1.printStackTrace();
        }

        // Aggiungo il path al file contenente le regole logiche
        encoding = new InputProgram();
        encoding.addFilesPath(encodingResource);

        // Inizializzo il frame per la grafica
        JFrame f = new JFrame();
        f.setTitle("Falling Puzzles AI");
        f.setSize(GameLoop.WIDTH, GameLoop.HEIGHT);

        //gp.addKeyListener(new MovementController(gp));
        //gp.addMouseListener(new MouseController(gp));
        gp.setFocusable(true);

        f.add(gp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    private void addFacts() {
        InputProgram facts = new ASPInputProgram();
        String Facts = "";

        try {
            // Prendo tutte le moves possibili come fatti
            for (Move v : this.gp.getGrid().getAllPossibleMove()) {
                facts.addObjectInput(v);
                Facts += v.toFact();
            }
            // Prendo tutte le tile piene
            for (Tile t : this.gp.getGrid().getTiles()) {
                facts.addObjectInput(new TileWrapper(t.getX(), t.getY(), t.getType()));
                TileWrapper T = new TileWrapper(t.getX(),t.getY(),t.getType());
                Facts += T.toFact();
            }
            // Prendo tutte le tile vuote
            for (TileWrapper t : this.gp.getGrid().getEmptyTiles()) {
                facts.addObjectInput(t);
                Facts += t.toFact();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Aggiungo all'handler tutti i fatti raccolti
        handler.addProgram(facts);

        //System.out.println(Facts);
    }

    @Override
    public void run() {
        Game.getInstance().setGp(gp);
        Game.getInstance().newRow();

        while(true) {
            // Genero una nuova riga dal basso ed attivo la gravità
            Game.getInstance().newRow();

            // Controllo che non abbia raggiunto il limite sopre e in caso resetto
            if(Game.getInstance().isDead()) {
                // Se il gioco è finito resetto tutto e ricomincio
                Game.getInstance().reset();
                System.out.println("DEAD! Resetting game.");
                continue;
            }


                // Pulisco l'handler
            handler.removeAll();

            //Aggiungo tutti i fatti della matrice (tile e mosse possibili)
            addFacts();

            // Aggiungo il programma logico
            handler.addProgram(encoding);

            // Eseguo in maniera sincronizzata in modo tale da aspettare il risultato
            Output o =  handler.startSync();

            // Mi prendo tutti gli AnswerSets
            AnswerSets answersets = (AnswerSets) o;

            // Scorro tutti gli answer set ottimali
            for(AnswerSet AS: answersets.getOptimalAnswerSets()){
                //System.out.println("ANS:");
                try {
                    for (Object obj : AS.getAtoms()) {
                        //System.out.println("Obj :" + obj);
                        //if(obj instanceof LiberaSopra || obj instanceof TappaBuco) {System.out.print(obj.toString());}
                        //System.out.print(obj.toString());

                        if(obj instanceof DoMove) {
                            // Se trovo una mossa nell'answer set allora è la mossa da fare
                            DoMove m = (DoMove) obj;
                            // Eseguo la mossa
                            Game.getInstance().doMove(m);
                        }
                    }
                    System.out.println("");
                    System.out.println("");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            // Faccio bloccare il thread fra una mossa e la successiva
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}