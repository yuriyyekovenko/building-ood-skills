package test;

import main.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by iiekovenko on 13.10.16.
 *
 * A unit test class for Martingale. This test should synthesize a fixed list of
 * Outcomes, Bins, and calls a Martingale instance with various sequences
 * of reds and blacks to assure that the bet doubles appropriately on each loss,
 * and is reset on each win.
 */
public class TestMartingale {
    Random rng;
    Wheel wheel;
    int minimum, limit;
    Table table;
    int stake;
    Martingale player;
    RouletteGame game;

    @BeforeClass
    public void beforeClass() {
        rng = new NonRandom();
        wheel = new Wheel(rng);
        minimum = 10;
        limit = 200;
        table = new Table(minimum, limit);
        stake = 500;
        player = new Martingale(table, wheel);
        player.setStake(stake);
        game = new RouletteGame(wheel, table);
    }
    @Test(dataProvider = "cycles-testdata")
    public void testMartingaleFollowsStrategy(int randomBin, int expStake,
                                              int expLossCount,
                                              int expBetMultiple) {
        rng.setSeed(randomBin);
        game.cycle(player);
        Assert.assertEquals(player.getStake(), expStake);
        Assert.assertEquals(player.getLossCount(), expLossCount);
        Assert.assertEquals(player.getBetMultiple(), expBetMultiple);
    }
    @DataProvider(name = "cycles-testdata")
    public Object[][] cyclesData() {
        int red = 1, black = 2, zero = 0;
        return new Object[][] {
                { red,   490, 1,  2 }, //expected bet = 10
                { red,   470, 2,  4 }, //expected bet = 20
                { red,   430, 3,  8 }, //expected bet = 40
                { zero,  350, 4, 16 }, //expected bet = 80
                { black, 510, 0,  1 }, //expected bet = 160
                { black, 520, 0,  1 }  //expected bet = 10
        };
    }
}
