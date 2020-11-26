package data;

import java.util.ArrayList;

public class Republic {
    private ArrayList<FederalState> states;
    public Republic() {
        states = new ArrayList<>();
    }

    public void addState(String name, int id, int hospitalCapacity, int icuCapacity) {
        states.add(new FederalState(name,id, hospitalCapacity, icuCapacity));
    }

    public String getState(String name) {
        for(FederalState fs : states) {
            if(fs.getStateName().contentEquals(name)) {
                return fs.getStateName();
            }
        }
        return "not found";
    }

    public ArrayList<FederalState> getStates() {
        return this.states;
    }

}
