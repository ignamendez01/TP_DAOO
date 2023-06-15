package model;

import java.util.ArrayList;
import java.util.List;

public class Versionable<T> {

    private List<T> history = new ArrayList<>();
    private int pointer;

    public Versionable(T element) {
        this.history.add(element);
        this.pointer = 0;
    }

    public T getActual() {
        return history.get(pointer);
    }

    public void update(T newVersion) {
        if (pointerIsInLatestVersion()){
            history.add(newVersion);
            pointer++;
        }else{
            rewriteHistoryFrom(newVersion);
        }
    }

    private void rewriteHistoryFrom(T newVersion) {
        List<T> newHistory = new ArrayList<>();
        for (int i = 0; i <= pointer; i++) {
            newHistory.add(history.get(i));
        }
        newHistory.add(newVersion);
        pointer++;
        history = newHistory;
    }

    private boolean pointerIsInLatestVersion() {
        return pointer == history.size() - 1;
    }

    public void undo(){
        if (pointer > 0){
            pointer--;
        }else{
            throw new RuntimeException("This is your first version");
        }
    }

    public void redo(){
        if (pointer < history.size()-1){
            pointer++;
        }else{
            throw new RuntimeException("This is your latest version");
        }
    }

    public List<T> getHistory() {
        return history;
    }
}
