package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class GreenBallCheat implements Subject{

    private static GreenBallCheat instance;
    private List<Observer> observers;

    private GreenBallCheat(){
        observers = new ArrayList<>();
    }

    public static GreenBallCheat getInstance(){
        if (instance == null){
            instance = new GreenBallCheat();
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
