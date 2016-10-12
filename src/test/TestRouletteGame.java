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
        game = new RouletteGame(wheel, table);
    }
    @Test
    public void testAmericanRoulette() {
        int stakeBefore = player.getStake();
        rng.setSeed(2);
        game.cycle(player);
        Assert.assertTrue(player.getStake() > stakeBefore,
                "Player failed to win in this cycle.");

        stakeBefore = player.getStake();
        rng.setSeed(1);
        game.cycle(player);
        Assert.assertTrue(player.getStake() < stakeBefore,
                "Player unexpectedly won in this cycle.");
    }
}
