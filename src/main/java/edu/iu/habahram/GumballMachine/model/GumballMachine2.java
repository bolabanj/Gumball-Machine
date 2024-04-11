package edu.iu.habahram.GumballMachine.model;

public class GumballMachine2 implements IGumballMachine{
    String id;
    IState soldOutState;
    IState noQuarterState;
    IState hasQuarterState;
    IState soldState;
    IState state;
    int count = 0;

    public IState getState(){
        return state;
    }
    public void setCount(int count){
        this.count = count;
    }

    public void setState(IState state){
        this.state = state;
    }

    public GumballMachine2(String id,String stateNanme,  int count){
        this.id = id;
        this.count = count;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        changeTheStateTo(GumballMachineState.NO_QUARTER);

    }
    public String getTheStateName(){
        return state.getTheName();
    }

    @Override
    public TransitionResult insertQuarter() {
        return state.insertQuarter();
    }

    @Override
    public TransitionResult ejectQuarter() {
        return state.ejectQuarter();
    }

    @Override
    public TransitionResult turnCrank() {
        return state.turnCrank();
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {
        switch (name) {
            case NO_QUARTER -> {this.setState(noQuarterState);}
            case HAS_QUARTER -> {this.setState(hasQuarterState);}
            case GUMBALL_SOLD -> {this.setState(soldState);}
            case OUT_OF_GUMBALLS -> {this.setState(soldOutState);}
        }

    }

    @Override
    public Integer getCount() {
        return count;
    }


    @Override
    public void releaseBall() {
        System.out.println("A gumball dispenses...plonk!");
        if (count != 0) {
            count = count - 1;
        }
    }
}
