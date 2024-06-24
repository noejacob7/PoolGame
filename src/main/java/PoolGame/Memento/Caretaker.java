package PoolGame.Memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private List<Memento> mementoList = new ArrayList<>();
    private boolean undo = false;

    public boolean isUndo() {
        return undo;
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
    }

    public void add (Memento memento){
        undo = false;
        this.mementoList.add(memento);
    }

    public Memento getMemento(){
        if (mementoList.size()>0){
            undo = true;
            return this.mementoList.remove(mementoList.size()-1);
        }
        return null;
    }

    public Memento getLastMemento(){
        if (mementoList.size()>0){
            return this.mementoList.get(mementoList.size()-1);
        }
        return null;
    }
}
