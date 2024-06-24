package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class BlackBallCheat implements Subject{

    private static BlackBallCheat instance;
    private List<Observer> observers;

    private BlackBallCheat(){
        observers = new ArrayList<>();
    }

    public static BlackBallCheat getInstance(){
        if (instance == null){
            instance = new BlackBallCheat();
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
