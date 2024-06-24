package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class YellowBallCheat implements Subject{

    private static YellowBallCheat instance;
    private List<Observer> observers;

    private YellowBallCheat(){
        observers = new ArrayList<>();
    }

    public static YellowBallCheat getInstance(){
        if (instance == null){
            instance = new YellowBallCheat();
        }
        return  instance;
    }

    public void reset(){
        instance.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer o) {
        instance.observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        instance.observers.remove(o);
    }

    @Override
    public void notifyUpdate() {
        for (Observer o: instance.observers){
            o.update();
        }
    }
}
