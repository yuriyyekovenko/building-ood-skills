package test;

import main.BinBuilder;
import main.Wheel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by iiekovenko on 06.10.16.
 * The unit test invoke each of the various methods that create Outcome instances.
 */
public class TestBinBuilder {

    @Test
    public void testWheelBinsContainOutcomes() {
        BinBuilder builder = new BinBuilder();
        Wheel wheel1 = builder.buildBins(new Random());
        for (int i = 0; i < wheel1.getSize(); i++) {
            Assert.assertTrue(wheel1.getBin(i).size() > 0);
        }
    }
}
