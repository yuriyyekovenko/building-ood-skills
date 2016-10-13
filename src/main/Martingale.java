package main;

/**
 * Created by iiekovenko on 12.10.16.
 * This player doubles their bet on every loss and resets
 their bet to a base amount on each win.
 */
public class Martingale extends Player {
    private int lossCount;
    private int betMultiple;

    public Martingale(Table table, Wheel wheel) {
        super(table, wheel);
        lossCount = 0;
        betMultiple = 1;
    }
    @Override
    public void placeBets() {
        super.placeBets();
        Outcome black = wheel.getOutcome("Black");
        int amount = table.getMinimum() * betMultiple;
        table.placeBet(new Bet(amount, black));
        setStake(getStake() - amount);
    }
    @Override
    public void win(Bet bet) {
        super.win(bet);
        lossCount = 0;
        betMultiple = 1;
    }
    @Override
    public void lose(Bet bet) {
        super.lose(bet);
        lossCount += 1;
        betMultiple *= 2;
    }
    public int getLossCount() {
        return lossCount;
    }
    public int getBetMultiple() {
        return betMultiple;
    }
}
