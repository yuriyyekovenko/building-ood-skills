package test;

import main.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by iiekovenko on 10.10.16.
 */
public class TestRouletteGame {
    private NonRandom rng;
    private Wheel wheel;
    private Table table;
    private Player player;
    private RouletteGame game;

    @BeforeClass
    public void beforeClass() {
        rng = new NonRandom();
        wheel = new Wheel(rng);
        table = new Table(10, 200);
        player = new Passenger57(table, wheel);
        player.setStake(500);
        game = new RouletteGame(wheel, table);
    }
    @Test
    public void testAmericanRoulette() {
        int betAmount = table.getMinimum();
        int startStake = player.getStake();
        rng.setSeed(2);
        game.cycle(player);
        Assert.assertEquals(player.getStake(), startStake + betAmount,
                "Player failed to win in this cycle.");

        startStake = player.getStake();
        rng.setSeed(1);
        game.cycle(player);
        Assert.assertEquals(player.getStake(), startStake - betAmount,
                "Player unexpectedly won in this cycle.");
    }
    @Test
    public void testLaPartageRuleOfEuroRoulette() {
        wheel = new EuroWheel(rng);
        player = new Passenger57(table, wheel, 500);
        game = new RouletteGame(wheel, table);

        int betAmount = table.getMinimum();
        int startStake = player.getStake();
        rng.setSeed(0);
        game.cycle(player);
        Assert.assertEquals(
                player.getStake(),
                startStake - betAmount + betAmount / 2,
                "Player is expected to get a half of even-money bet " +
                        "if Zero won.");
    }
}
