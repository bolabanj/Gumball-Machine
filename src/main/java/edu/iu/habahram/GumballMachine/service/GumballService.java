package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.*;
import edu.iu.habahram.GumballMachine.repository.GumballMachineFileRepository;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static edu.iu.habahram.GumballMachine.model.Transition.*;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    private TransitionResult transit(String id, Transition action) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        TransitionResult result = runTheMachine(machine, action);
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    private TransitionResult runTheMachine(IGumballMachine machine, Transition action) {
        if (action.equals(INSERT_QUARTER)) {
            return machine.insertQuarter();
        } else if (action.equals(EJECT_QUARTER)) {
            return machine.ejectQuarter();
        } else if (action.equals(TURN_CRANK)) {
            return machine.turnCrank();
        }
        return null;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return transit(id, INSERT_QUARTER);
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return transit(id, EJECT_QUARTER);
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return transit(id, TURN_CRANK);
    }



    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}
