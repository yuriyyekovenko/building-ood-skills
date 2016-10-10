package test;

import main.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by iiekovenko on 07.10.16.
 */
public class TestTable {
    private Table table;
    private Bet bet1;
    private Bet bet2;

    @BeforeClass
    public void beforeClass() {
        Outcome outcome1 = new Outcome("1", RouletteGame.STRAIGHTBET);
        Outcome outcome2 = new Outcome("Street 1-2-3",
                                       RouletteGame.STREETBET);
        bet1 = new Bet(10, outcome1);
        bet2 = new Bet(20, outcome2);
    }
    @Test
    public void testBetsCanBePlacedAndIterated() {
        table = new Table(10, 200);
        table.placeBet(bet1);
        table.placeBet(bet2);
        Iterator<Bet> betIter = table.iterator();
        Assert.assertEquals(betIter.next(), bet1);
        Assert.assertEquals(betIter.next(), bet2);
    }
    @Test
    public void testTableToString() {
        table = new Table(10, 200);
        table.placeBet(bet1);
        table.placeBet(bet2);
        Assert.assertEquals(
                table.toString(), String.format("[%s, %s]", bet1, bet2));
    }
    @Test(expectedExceptions = InvalidBet.class)
    public void testIsValidThrowsInvalidBet() throws Exception {
        table = new Table(20, 100);
        table.placeBet(bet1);
        table.isValid();
    }
    @Test
    public void testIsValidAcceptsBetsWithinLimits() throws InvalidBet {
        table = new Table(10, 30);
        table.placeBet(bet1); //10
        table.placeBet(bet2); //20
        Assert.assertTrue(table.isValid());
    }
}
