package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class OrangeBallCheat implements Subject{

    private static OrangeBallCheat instance;
    private List<Observer> observers;

    private OrangeBallCheat(){
        observers = new ArrayList<>();
    }

    public static OrangeBallCheat getInstance(){
        if (instance == null){
            instance = new OrangeBallCheat();
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
