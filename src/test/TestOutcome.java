package test;

import main.Outcome;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iiekovenko on 04.10.16.
 */
public class TestOutcome {

    private Outcome outcome1 = new Outcome("s1", 10);
    private Outcome outcome2 = new Outcome("s1", 10);
    private Outcome outcome3 = new Outcome("s2", 17);

    @Test
    public void testOutcomesWithTheSameNameAreEqual() {
        Assert.assertTrue(outcome1.equals(outcome2));
    }
    @Test
    public void testOutcomesWithTheSameNameHaveTheSameHash() {
        Assert.assertEquals(
                outcome1.hashCode(),
                outcome2.hashCode());
    }
    @Test
    public void testOutcomesWithDiffNamesAreNotEqual() {
        Assert.assertFalse(outcome1.equals(outcome3));
    }
    @Test
    public void testOutcomesWithDiffNamesHaveDiffHash() {
        Assert.assertNotEquals(
                outcome1.hashCode(),
                outcome3.hashCode());
    }
    @Test
    public void testWinCalculation() {
        Assert.assertEquals(outcome1.winAmount(5), 50);
        Assert.assertEquals(outcome3.winAmount(100), 1700);
    }
}
