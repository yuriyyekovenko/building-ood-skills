package main;

/**
 * Created by iiekovenko on 07.10.16.
 */
public class Passenger57  extends Player {
    private Outcome black;
    private Table table;
    private int stake;

    public Passenger57(Table table, Wheel wheel) {
        this.table = table;
        black = wheel.getOutcome("Black").toArray(new Outcome[1])[0];

        //TODO Probably stake should be defined by Game?
        stake = 500;
    }
    public void placeBets() {
        //Smarter player will contain some logic to define amount
        int amount = 10;
        table.placeBet(new Bet(amount, black));
        stake -= amount;
    }
    public void win(Bet bet) {
        stake += bet.winAmount();
        System.out.println("Player won on " + bet.getOutcome());
    }
    public void lose(Bet bet) {
        //Use this info to define the next step
        stake -= bet.loseAmount();
        System.out.println("Player lost on " + bet.getOutcome());
    }
    @Override
    public int getStake() {
        return stake;
    }
}
