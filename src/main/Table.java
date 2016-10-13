package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by iiekovenko on 07.10.16.
 */
public class Table {
    private List<Bet> bets;
    private int limit = 200;
    private int minimum = 10;

    public Table() {
        bets = new LinkedList<>();
    }

    public Table(int minimum, int limit) {
        this();
        this.minimum = minimum;
        this.limit = limit;
    }

    public boolean isValid() throws InvalidBet {
        int betSum = 0;
        for (Bet b : bets) {
            if (b.getAmount() < minimum)
                throw new InvalidBet();
            betSum += b.getAmount();
        }
        if (betSum > limit)
            throw new InvalidBet();
        return true;
    }

    public void placeBet(Bet bet) {
        bets.add(bet);
    }

    public String toString() {
        return bets.toString();
    }

    public ListIterator<Bet> iterator() {
        return bets.listIterator();
    }

    public void removeBets() {
        bets.clear();
    }

    public int getMinimum() {
        return minimum;
    }

    public int getLimit() {
        return limit;
    }


}
