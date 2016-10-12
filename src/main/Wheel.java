package main;

import java.util.*;

/**
 * Created by iiekovenko on 03.10.16.
 * Model of roulette wheel containing bins and able
 * to pick one of bins randomly.
 * Bins are stored as an indexed sequence 0, 1, 2, ..., 36, and 00.
 */
public class Wheel {

    List<Bin> bins;
    Random rng;
    Set<Outcome> outcomes;

    public Wheel() {
        this(new Random());
    }
    /*
    * this constructor (with input parameter) is needed
    * for testability purposes.
    * */
    public Wheel(Random rng) {
        this.rng = rng;
        outcomes = new HashSet<>();
        bins = new ArrayList<>();
        buildBins();
    }
    void buildBins() {
        for (int i = 0; i < 38; i++)
            bins.add(new Bin());
        BinBuilder builder = new BinBuilder();
        builder.buildBins(this);
    }
    public void addOutcome(int bin, Outcome outcome) {
        outcomes.add(outcome);
        bins.get(bin).add(outcome);
    }
    public Bin next() {
        return bins.get(rng.nextInt(bins.size()));
    }
    public Bin getBin(int bin) {
        return bins.get(bin);
    }
    public Set<Outcome> getOutcome(String name) {
        Set<Outcome> result = new HashSet<>();
        for (Outcome oc : outcomes) {
            if (oc.getName().startsWith(name))
                result.add(oc);
        }
        return result;
    }
    public int getSize() {
        return bins.size();
    }
}
