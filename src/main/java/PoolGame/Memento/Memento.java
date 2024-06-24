package PoolGame.Memento;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import PoolGame.Items.ScoreBoard;
import PoolGame.ReadFactory.LevelFactoryRegistry;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.LinkedList;
import java.util.List;

public class Memento {

    private List<BallData> balls;
    private boolean shownWonText = false;

    private int score;
    private int timer;

    public Memento( PoolTable table, boolean shownWonText, int score, int timer) {
        this.balls = new LinkedList<>();
        this.shownWonText = shownWonText;
        this.score = score;
        this.timer = timer;
        for (Ball ball: table.getBalls()){
            this.balls.add(new BallData(ball.getXPos(), ball.getYPos(),ball.getFallCounter(),ball.isDisabled()));
        }
    }

    public List<BallData> getBalls() {
        return this.balls;
    }

    public boolean isShownWonText() {
        return shownWonText;
    }

    public int getScore() {
        return score;
    }

    public int getTimer() {
        return timer;
    }

}
