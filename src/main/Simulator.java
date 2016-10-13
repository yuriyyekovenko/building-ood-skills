package main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by iiekovenko on 13.10.16.
 */
public class Simulator {

    private int initDuration = 250;
    private int initStake = 100;
    private int samples;
    private List<Integer> durations;
    private List<Integer> maxima;
    private Player player;
    private RouletteGame game;
//    private Table table;

    public Simulator(RouletteGame game, Player player) {
        this.game = game;
        this.player = player;
        durations = new LinkedList<>();
        maxima = new LinkedList<>();

//        Table table = new Table(10, 1000);
//        Wheel wheel = new Wheel(new Random());
//        game = new RouletteGame(wheel, table);
//        player = new Martingale(table, wheel);
    }

    public List<Integer> session() {
        List<Integer> stakes = new LinkedList<>();
        player.setStake(initStake); //TODO in my design, it should be table.getMin * initStake.
        player.setRounds(initDuration);
        while (player.playing()) {
            try {
                game.cycle(player);
                stakes.add(player.getStake());
            }
            catch (InvalidBet exception) {
                System.out.println("InvalidBet happened.");
                break;
            }
        }
        return stakes;
    }

    public void gather() {
        List<Integer> sessionStakes;
        for (int s = 0; s < samples; s++) {
            sessionStakes = session();
            durations.add(sessionStakes.size());
            maxima.add(Collections.max(sessionStakes));
        }
    }
}
