package PoolGame.Observer;

import java.util.ArrayList;
import java.util.List;

public class BrownBallCheat implements Subject{

    private static BrownBallCheat instance;
    private List<Observer> observers;

    private BrownBallCheat(){
        observers = new ArrayList<>();
    }

    public static BrownBallCheat getInstance(){
        if (instance == null){
            instance = new BrownBallCheat();
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
