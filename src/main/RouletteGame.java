package main;

import java.util.ListIterator;

/**
 * Created by iiekovenko on 03.10.16.
 */
public class RouletteGame {

    public static final int STRAIGHTBET = 35;
    public static final int SPLITBET = 17;
    public static final int STREETBET = 11;
    public static final int CORNERBET = 8;
    public static final int FIVEBET = 6;
    public static final int LINEBET = 5;
    public static final int DOZENBET = 2;
    public static final int COLUMNBET = 2;
    public static final int EVENMONEYBET = 1;

    private Wheel wheel;
    private Table table;

    public RouletteGame(Wheel wheel, Table table) {
        this.wheel = wheel;
        this.table = table;
    }
    public void cycle(Player player) {
        player.placeBets();
        Bin winBin = wheel.next();
        Bet bet;
        for (ListIterator<Bet> iter = table.iterator(); iter.hasNext();) {
            bet = iter.next();
            if (winBin.contains(bet.getOutcome()))
                player.win(bet);
            else
                player.lose(bet);
        }
        table.removeBets();
    }
}
