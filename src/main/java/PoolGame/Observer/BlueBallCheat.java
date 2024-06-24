package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class BlueBallCheat implements Subject{

    private static BlueBallCheat instance;
    private List<Observer> observers;

    private BlueBallCheat(){
        observers = new ArrayList<>();
    }

    public static BlueBallCheat getInstance(){
        if (instance == null){
            instance = new BlueBallCheat();
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
