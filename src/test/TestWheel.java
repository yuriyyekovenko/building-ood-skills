package test;

import main.Bin;
import main.Outcome;
import main.RouletteGame;
import main.Wheel;
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
        wheel = new Wheel(new Random());
    }

    @Test
    public void testWheelIsPopulatedWithBins() {
        int rouletteWheelCount = 38;
        for (int i = 0; i < rouletteWheelCount; i++) {
            Assert.assertTrue(wheel.getBin(i) != null);
        }
    }
    @Test
    public void testBinsArePopulatedWithOutcomes() {
        int rouletteWheelCount = 38;
        for (int i = 0; i < rouletteWheelCount; i++) {
            Assert.assertTrue(wheel.getBin(i).size() > 0);
        }
    }
    @Test
    public void testWheelProvidesOutcomesCatalog() {
        Set<Outcome> res = wheel.getOutcome("Split");
        System.out.println(res.size());
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
        Wheel mywheel = new Wheel(new Random());
        Outcome oc = new Outcome("25", RouletteGame.STRAIGHTBET);
        int initialOutcomeCount = mywheel.getBin(0).size();
        mywheel.addOutcome(0, oc);
        Assert.assertEquals(mywheel.getBin(0).size(), initialOutcomeCount + 1,
                "New outcome added to the bin.");
        mywheel.addOutcome(0, oc);
        Assert.assertEquals(mywheel.getBin(0).size(), initialOutcomeCount + 1,
                "The outcome-duplicate was not added to the bin.");
    }
    @Test
    public void testUsingNonRandomClassIfWheelPicksBinsRandomly() {
        NonRandom rng = new NonRandom();
        Wheel wheel = new Wheel(rng);

        rng.setSeed(0);
        Bin bin = wheel.getBin(0);
        Assert.assertEquals(wheel.next().toString(), bin.toString());

        rng.setSeed(6);
        bin = wheel.getBin(6);
        Assert.assertEquals(wheel.next().toString(), bin.toString());
    }
    @Test
    public void testWithoutMocksIfWheelPicksBinsRandomly() {
        Random goldenGen = new Random();
        goldenGen.setSeed(1);
        Random workingGen = new Random();
        workingGen.setSeed(1);

        Wheel wheel = new Wheel(workingGen);
        int expectedRandomValue = goldenGen.nextInt(38);

        Bin bin = wheel.getBin(expectedRandomValue);
        Assert.assertEquals(wheel.next().toString(), bin.toString());

        expectedRandomValue = goldenGen.nextInt(38);
        bin = wheel.getBin(expectedRandomValue);
        Assert.assertEquals(wheel.next().toString(), bin.toString());
    }
}
