package main;

/**
 * Created by iiekovenko on 07.10.16.
 */
public class Passenger57  extends Player {
    private Outcome black;

    public Passenger57(Table table, Wheel wheel) {
        super(table, wheel);
        black = wheel.getOutcome("Black");
    }
    public void placeBets() {
        super.placeBets();
        int amount = table.getMinimum();
        table.placeBet(new Bet(table.getMinimum(), black));
        setStake(getStake() - amount);
    }
}
