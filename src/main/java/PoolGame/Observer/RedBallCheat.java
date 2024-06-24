package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class RedBallCheat implements Subject{

    private static RedBallCheat instance;
    private List<Observer> observers;

    private RedBallCheat(){
        observers = new ArrayList<>();
    }

    public static RedBallCheat getInstance(){
        if (instance == null){
            instance = new RedBallCheat();
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
