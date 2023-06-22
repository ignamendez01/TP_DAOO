package model.entities.inspector;

public class Change {

    private final String field;
    private final String prevState;
    private final String nextState;

    public Change(String field, String prevState, String nextState) {
        this.field = field;
        this.prevState = prevState;
        this.nextState = nextState;
    }

    public String getField() {
        return field;
    }

    public String getPrevState() {
        return prevState;
    }

    public String getNextState() {
        return nextState;
    }
}
