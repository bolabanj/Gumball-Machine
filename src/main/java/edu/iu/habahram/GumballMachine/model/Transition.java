package edu.iu.habahram.GumballMachine.model;

public class Transition {
    public static final Transition INSERT_QUARTER = new Transition(true, "You inserted a quarter", "HAS_QUARTER", 0);
    public static final Transition EJECT_QUARTER = new Transition(true, "Quarter returned", "NO_QUARTER", 0);
    public static final Transition TURN_CRANK = new Transition(true, "You turned...", "GUMBALL_SOLD", 0);
    public static final Transition DISPENSE = new Transition(true, "Here is your gumball", "NO_QUARTER", 0);
    public static final Transition SOLD_OUT_INSERT_QUARTER = new Transition(false, "You can't insert a quarter, the machine is sold out", "SOLD_OUT", 0);

    private boolean isTransitionValid;
    private String message;
    private String currentState;
    private Integer count;

    public Transition(boolean isTransitionValid, String message, String currentState, Integer count) {
        this.isTransitionValid = isTransitionValid;
        this.message = message;
        this.currentState = currentState;
        this.count = count;
    }

    public boolean isTransitionValid() {
        return isTransitionValid;
    }

    public void setTransitionValid(boolean transitionValid) {
        isTransitionValid = transitionValid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
