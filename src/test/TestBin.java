package test;

import main.Bin;
import main.Outcome;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iiekovenko on 04.10.16.
 */
public class TestBin {

    private Outcome ocFive = new Outcome("00-0-1-2-3", 6);
    private Outcome[] ocZero = {new Outcome("0", 35), ocFive};

    @Test
    public void testCreateEmptyBinAndAddOutcomesToIt() {
        Bin b = new Bin();
        Assert.assertEquals(b.toString(), "[]");
        b.add(ocFive);
        Assert.assertEquals(b.toString(), "[" + ocFive.toString() + "]");
    }
    @Test
    public void testCreateBinFromOutcomeArray() {
        Bin zero = new Bin(ocZero);
        String outcomes = zero.toString();
        Assert.assertTrue(outcomes.length() > 0);
    }
}
