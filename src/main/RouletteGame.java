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
    public void cycle(Player player) throws InvalidBet {
        if (!player.playing()) return;

        player.placeBets();
        //TODO cycle will be interrupted if player made too small bet (should be impossible by design)
        // or too big bet (is possible e.g. for Martingale if unlucky)
        //Not sure how to handle the second case.
        table.isValid();
        Bin winBin = wheel.next();
        Bet bet;
        for (ListIterator<Bet> iter = table.iterator(); iter.hasNext();) {
            bet = iter.next();
            if (winBin.contains(bet.getOutcome()))
                player.win(bet);
            else {
                player.lose(bet);
                if (wheel.isLaPartageEnabled() &&
                        bet.getOutcome().getOdds() == EVENMONEYBET &&
                        winBin.contains(wheel.getOutcome("0")))
                    player.push(bet);
            }
        }
        table.removeBets();
    }
}
