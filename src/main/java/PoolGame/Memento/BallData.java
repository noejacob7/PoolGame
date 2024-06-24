package PoolGame.Memento;

public class BallData {
    private double xPos;
    private double yPos;
    private int fallCounter;
    private boolean disabled;

    public BallData(double xPos, double yPos, int fallCounter, boolean disabled) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.fallCounter = fallCounter;
        this.disabled = disabled;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public int getFallCounter() {
        return fallCounter;
    }

    public void setFallCounter(int fallCounter) {
        this.fallCounter = fallCounter;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
