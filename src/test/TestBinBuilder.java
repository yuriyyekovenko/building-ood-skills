package test;

import main.Bin;
import main.BinBuilder;
import main.Wheel;
import main.Wheel2;
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
//        // my favourite implementation
//        Wheel2 wheel2 = new Wheel2.Builder(new Random()).build();
//        // per book but customized
//        BinBuilder builder = new BinBuilder();
//        Wheel wheel1 = builder.buildBins();

        Wheel wheel1 = new Wheel(new Random());
//        BinBuilder builder = new BinBuilder();
//        builder.buildBins(wheel1);

        for (int i = 0; i < wheel1.getSize(); i++) {
            Assert.assertTrue(wheel1.getBin(i).size() > 0);
        }
    }
}
