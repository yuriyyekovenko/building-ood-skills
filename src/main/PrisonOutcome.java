package main;

/**
 * Created by iiekovenko on 10.10.16.
 */
public class PrisonOutcome extends Outcome {

    public PrisonOutcome(String name, int odds) {
        super(name, odds);
    }
    @Override
    public int loseAmount(int amount) {
        return amount / 2;
    }
}
