package main;

import java.util.*;

/**
 * Created by iiekovenko on 04.10.16.
 * Bin represents segmets of the Wheel and provides
 * a collections of outcomes that step into game for
 * specific bin.
 */
public class Bin {
    private Set<Outcome> outcomes;

    public Bin() {
        outcomes = new HashSet<>();
    }

    public Bin(Outcome[] outcomes) {
        this();
        Collections.addAll(this.outcomes, outcomes);
    }

    public Bin(Collection<Outcome> outcomes) {
        this();
        this.outcomes.addAll(outcomes);
    }

    public void add(Outcome outcome) {
        outcomes.add(outcome);
    }

    @Override
    public String toString() {
        return outcomes.toString();
    }

    public int size() {
        return outcomes.size();
    }

    public boolean contains(Outcome outcome) {
        return outcomes.contains(outcome);
    }
}
