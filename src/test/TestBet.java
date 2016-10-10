package test;

import main.Bet;
import main.Outcome;
import main.RouletteGame;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by iiekovenko on 06.10.16.
 */
public class TestBet {
    private int amount = 10;
    private Outcome outcome;
    private Bet bet;

    @BeforeClass
    public void beforeClass() {
        outcome = new Outcome("1", RouletteGame.STRAIGHTBET);
        bet = new Bet(amount, outcome);
    }
    @Test
    public void testWinAmount() {
        Assert.assertEquals(bet.winAmount(),
                amount + outcome.winAmount(amount));
    }
    @Test
    public void testLoseAmount() {
        Assert.assertEquals(bet.loseAmount(), amount);
    }
    @Test
    public void testToString() {
        Assert.assertEquals(bet.toString(),
                amount + " on " + outcome.toString());
    }
    @Test
    public void testGetOutcome() {
        Assert.assertEquals(bet.getOutcome(), outcome);
    }
}
