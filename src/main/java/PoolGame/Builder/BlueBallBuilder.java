package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.Items.Ball.BallType;
import PoolGame.Observer.BlueBallCheat;
import PoolGame.Strategy.BallPocketStrategy;

/** Builder for blue ball */
public class BlueBallBuilder implements BallBuilder {
    
    private Ball ball;
    private BallType ballType = null;
    private BallPocketStrategy action = null;

    private Integer scoreIncrement = null;

    /** Initialise the builder and start a new build */
    public BlueBallBuilder() {
        this.reset();
    }

    /**
     * Initialise a builder with the ball type and action for the new build
     * @param type The ball type the builder will build
     * @param action The action that the ball have when it falls into a pocket
     */
    public BlueBallBuilder(BallType type, BallPocketStrategy action, Integer scoreIncrement) {
        this.ballType = type;
        this.action = action;
        this.scoreIncrement = scoreIncrement;
        this.reset();
    }
    
    public void reset() {
        this.ball = new Ball();
        this.ball.setColour("blue");
        if (ballType != null) {
            this.ball.setBallType(this.ballType);
        }
        if (this.action != null) {
            this.ball.setPocketAction(this.action);
        }
        if (this.scoreIncrement != null){
            this.ball.setScoreIncrement(scoreIncrement);
        }
    }

    public void setXPos(double xPos) {
        this.ball.setInitialXPos(xPos);
    }

    public void setYPos(double yPos) {
        this.ball.setInitialYPos(yPos);
    }

    public void setXVel(double xVel) {
        this.ball.setInitialXVel(xVel);
    }

    public void setYVel(double yVel) {
        this.ball.setInitialYVel(yVel);
    }

    public void setMass(double mass) {
        this.ball.setMass(mass);
    }

    public void setBallType(BallType type) {
        this.ballType = type;
        this.ball.setBallType(type);
    }

    public void setPocketAction(BallPocketStrategy action) {
        this.action = action;
        this.ball.setPocketAction(action);
    }

    public void setScoreIncrement(int scoreIncrement) {
        this.scoreIncrement = scoreIncrement;
    }

    public Ball finaliseBuild() {
        Ball ball = this.ball;
        BlueBallCheat.getInstance().attach(ball);
        this.reset();
        return ball;
    }
}
