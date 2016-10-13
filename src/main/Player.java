package main;

/**
 * Created by iiekovenko on 03.10.16.
 */
public abstract class Player {
    private int stake;
    private int roundsToGo;
    Table table;
    Wheel wheel;

    public Player(Table table, Wheel wheel) {
        this.table = table;
        this.wheel = wheel;
        this.stake = table.getMinimum() * 100;
        this.roundsToGo = 250;
    }

    /*
    * The method defines list of player's bets according
    * to his strategy, amount for each bet and
    * reduces the stake accordingly.
    * */
    public void placeBets() {
        roundsToGo--;
    }

    public boolean playing() {
        //TODO Clarify about higher limit to stop game.
        return stake >= table.getMinimum() || roundsToGo == 0;
    }

    public void win(Bet bet) {
        stake += bet.winAmount();
        System.out.println("Player won on " + bet.getOutcome());
    }

    /* Player's stake is reduced when the player places a bet so
    * it's unnecessary to reduce it again in case of lose.
    * So, this method is required just to get and use info about spin result.
    * */
    public void lose(Bet bet) {
        System.out.println("Player lost on " + bet.getOutcome());
    }

    public void push(Bet bet) {
        stake += bet.getAmount() / 2;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public int getRounds() {
        return roundsToGo;
    }

    public void setRounds(int value) {
        roundsToGo = value;
    }
}
