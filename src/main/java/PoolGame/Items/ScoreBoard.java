package PoolGame.Items;

public class ScoreBoard {

    private static ScoreBoard instance;
    private int score;

    private ScoreBoard(){
        score = 0;
    }

    public static ScoreBoard getInstance(){
        if (ScoreBoard.instance == null){
            ScoreBoard.instance = new ScoreBoard();
        }
        return ScoreBoard.instance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void incrementScore(int score){
        this.score += score;
    }

    public void resetScore(){
        setScore(0);
    }
}
