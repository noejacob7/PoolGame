package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class PurpleBallCheat implements Subject{

    private static PurpleBallCheat instance;
    private List<Observer> observers;

    private PurpleBallCheat(){
        observers = new ArrayList<>();
    }

    public static PurpleBallCheat getInstance(){
        if (instance == null){
            instance = new PurpleBallCheat();
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
