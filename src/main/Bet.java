package main;

/**
 * Created by iiekovenko on 06.10.16.
 */
public class Bet {
    private int amount;
    private Outcome outcome;

    public Bet(int amount, Outcome outcome) {
        this.amount = amount;
        this.outcome = outcome;
    }

    public int winAmount() {
        return outcome.winAmount(amount) + amount;
    }

    public String toString() {
        return String.format("%d on %s", amount, outcome);
    }

    public int getAmount() {
        return amount;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
