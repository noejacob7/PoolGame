package PoolGame;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Config.PocketConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import PoolGame.Items.ScoreBoard;
import PoolGame.Memento.Caretaker;
import PoolGame.Memento.Memento;
import PoolGame.Observer.*;
import PoolGame.ReadFactory.LevelFactoryRegistry;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;

/** The game class that runs the game */
public class Game {
    private Label score;
    private Caretaker caretaker;

    private PoolTable table;
    private boolean shownWonText = false;
    private final Text winText = new Text(50, 50, "Win and Bye");
    private Group root;
    private LevelFactoryRegistry registry;

    private Scene scene;

    private ScoreBoard scoreBoard;
    private int timer;
    private Label timeLabel;
    private boolean saveState = false;

    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
        setButton();
    }
    public Game(ConfigReader config, Group root, Scene scene) throws ConfigReader.ConfigKeyMissingException, IOException, ParseException {
        this.root = root;
        this.scene = scene;
        this.registry = new LevelFactoryRegistry();
        this.scoreBoard = ScoreBoard.getInstance();
        registry.registerDefault();
        caretaker = new Caretaker();
        this.setup(config);
        setButton();
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setup(ConfigReader config) {
        RedBallCheat.getInstance().reset();
        BlueBallCheat.getInstance().reset();
        BlackBallCheat.getInstance().reset();
        BrownBallCheat.getInstance().reset();
        GreenBallCheat.getInstance().reset();
        OrangeBallCheat.getInstance().reset();
        PurpleBallCheat.getInstance().reset();
        YellowBallCheat.getInstance().reset();
        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }

        this.table.setupBalls(balls);
        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2);
        this.winText.setY(table.getDimY() / 2);
        this.timer = 0;
        this.scoreBoard.resetScore();
        this.score = new Label("Score : 0 points");
        this.timeLabel = new Label();
        this.score.setLayoutX(this.table.getDimX()-100);
        this.score.setLayoutY(50);
        this.timeLabel.setLayoutY(100);
        this.timeLabel.setLayoutX(this.table.getDimX()-100);
        this.caretaker = new Caretaker();
        this.saveState = false;
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);
        groupChildren.add(this.score);
        groupChildren.add(this.timeLabel);
    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
        this.scoreBoard.resetScore();
        this.timer = 0;
    }

    public void setButton(){

        this.scene.setOnKeyPressed(
                value -> {
                    if (value.getCode().equals(KeyCode.A)){
                        BlackBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.S)){
                        BlueBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.D)){
                        BrownBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.F)){
                        GreenBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.G)){
                        OrangeBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.H)){
                        PurpleBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.J)){
                        RedBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.K)){
                        YellowBallCheat.getInstance().notifyUpdate();
                    }
                    else if (value.getCode().equals(KeyCode.DIGIT1)) {
                        try {
                            registry.create("easy", this);
                        } catch (ConfigReader.ConfigKeyMissingException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (value.getCode().equals(KeyCode.DIGIT2)) {
                        try {
                            registry.create("normal", this);
                        } catch (ConfigReader.ConfigKeyMissingException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (value.getCode().equals(KeyCode.DIGIT3)) {
                        try {
                            registry.create("hard", this);
                        } catch (ConfigReader.ConfigKeyMissingException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (value.getCode().equals(KeyCode.Z)){
                        getStateFromMemento(caretaker.getMemento());
                    }


                }
        );
    }

    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
        }
        if (!table.hasWon()){
            this.timer +=1;
        }
        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }
        if (!table.allBallsHasStopped() && !saveState){
            System.out.println("save");
            caretaker.add(saveStateToMemento());
            saveState = true;
        }
        if (table.allBallsHasStopped()){
            saveState = false;
        }
//        if (!caretaker.isUndo() && table.allBallsHasStopped() && checkMemento()){
//            System.out.println("save");
//            caretaker.add(saveStateToMemento());
//        }
        this.score.setText(String.format("Score : %d points", this.scoreBoard.getScore()));
        this.timeLabel.setText(String.format("Time: %2d: %02d", (int) (this.timer*App.FRAMETIME/60), (int) (this.timer*App.FRAMETIME) % 60));
    }

//    public boolean checkMemento(){
//        Memento mem = caretaker.getLastMemento();
//        if (mem != null) {
//            if (mem.isShownWonText() != this.shownWonText) {
//                return true;
//            }
//            if (mem.getScore() != this.scoreBoard.getScore()) {
//                return true;
//            }
//            for (int i = 0; i < mem.getBalls().size(); i++) {
//                if (mem.getBalls().get(i).getXPos() != this.table.getBalls().get(i).getXPos()) {
//                    return true;
//                }
//                if (mem.getBalls().get(i).getYPos() != this.table.getBalls().get(i).getYPos()) {
//                    return true;
//                }
//                if (mem.getBalls().get(i).getFallCounter() != this.table.getBalls().get(i).getFallCounter()) {
//                    return true;
//                }
//                if (mem.getBalls().get(i).isDisabled() != this.table.getBalls().get(i).isDisabled()) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        return true;
//    }

    public Memento saveStateToMemento(){
        return new Memento(this.table, this.shownWonText, this.scoreBoard.getScore(), this.timer);
    }

    public void getStateFromMemento(Memento memento){
        if (memento != null) {
            this.shownWonText = memento.isShownWonText();
            if (!this.shownWonText){
                this.winText.setVisible(false);
            }
            this.scoreBoard.setScore(memento.getScore());
            this.timer = memento.getTimer();
            for (int i = 0; i<table.getBalls().size(); i++){
                table.getBalls().get(i).setXPos(memento.getBalls().get(i).getXPos());
                table.getBalls().get(i).setYPos(memento.getBalls().get(i).getYPos());
                table.getBalls().get(i).setXVel(0);
                table.getBalls().get(i).setYVel(0);
                table.getBalls().get(i).setFallCounter(memento.getBalls().get(i).getFallCounter());
                table.getBalls().get(i).setDisabled(memento.getBalls().get(i).isDisabled());
            }
        }
    }
}
