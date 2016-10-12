package test;

import main.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;

/**
 * Created by iiekovenko on 05.10.16.
 */
public class TestWheel {
    private Wheel wheel;

    @BeforeClass
    public void setUpClass() {
        wheel = new Wheel();
    }

    @Test
    public void testWheelIsPopulatedWithBins() {
        for (int i = 0; i < wheel.getSize(); i++) {
            Assert.assertTrue(wheel.getBin(i) != null);
        }
    }
    @Test
    public void testBinsArePopulatedWithOutcomes() {
        for (int i = 0; i < wheel.getSize(); i++) {
            Assert.assertTrue(wheel.getBin(i).size() > 0);
        }
    }
    @Test
    public void testWheelProvidesOutcomesCatalog() {
        Assert.assertEquals(wheel.getOutcome("Street").size(), 12);
        Assert.assertEquals(wheel.getOutcome("Split").size(), 57);
        Assert.assertEquals(wheel.getOutcome("Corner").size(), 22);
        Assert.assertEquals(wheel.getOutcome("Line").size(), 11);
        Assert.assertEquals(wheel.getOutcome("Dozen").size(), 3);
        Assert.assertEquals(wheel.getOutcome("Column").size(), 3);
        Assert.assertEquals(wheel.getOutcome("Red").size(), 1);
        Assert.assertEquals(wheel.getOutcome("Black").size(), 1);
        Assert.assertEquals(wheel.getOutcome("Low").size(), 1);
        Assert.assertEquals(wheel.getOutcome("High").size(), 1);
        Assert.assertEquals(wheel.getOutcome("Odd").size(), 1);
        Assert.assertEquals(wheel.getOutcome("Even").size(), 1);
        Assert.assertEquals(wheel.getOutcome("00-0-1-2-3").size(), 1);
        Assert.assertEquals(wheel.getOutcome("").size() - 115, 38,
                "Ensure there are 38 straight bets in the catalog.");
    }
    @Test
    public void testAddOutcomeToBinButNoDuplicates() {
        Wheel wheel1 = new Wheel();
        Outcome oc = new Outcome("25", RouletteGame.STRAIGHTBET);
        int initialOutcomeCount = wheel1.getBin(0).size();
        wheel1.addOutcome(0, oc);
        Assert.assertEquals(wheel1.getBin(0).size(), initialOutcomeCount + 1,
                "New outcome added to the bin.");
        wheel1.addOutcome(0, oc);
        Assert.assertEquals(wheel1.getBin(0).size(), initialOutcomeCount + 1,
                "The outcome-duplicate was not added to the bin.");
    }
    @Test
    public void testUsingNonRandomClassIfWheelPicksBinsRandomly() {
        NonRandom rng = new NonRandom();
        Wheel wheel1 = new Wheel(rng);

        rng.setSeed(0);
        Bin bin = wheel1.getBin(0);
        Assert.assertEquals(wheel1.next().toString(), bin.toString());

        rng.setSeed(6);
        bin = wheel1.getBin(6);
        Assert.assertEquals(wheel1.next().toString(), bin.toString());
    }
    @Test
    public void testWithoutMocksIfWheelPicksBinsRandomly() {
        Random goldenGen = new Random();
        goldenGen.setSeed(1);
        Random workingGen = new Random();
        workingGen.setSeed(1);

        Wheel wheel = new Wheel(workingGen);
        int expectedRandomValue = goldenGen.nextInt(wheel.getSize());

        Bin bin = wheel.getBin(expectedRandomValue);
        Assert.assertEquals(wheel.next().toString(), bin.toString());

        expectedRandomValue = goldenGen.nextInt(wheel.getSize());
        bin = wheel.getBin(expectedRandomValue);
        Assert.assertEquals(wheel.next().toString(), bin.toString());
    }
}
