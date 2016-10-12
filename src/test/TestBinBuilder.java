package test;

import main.Wheel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iiekovenko on 06.10.16.
 * The unit test invoke each of the various methods that create Outcome instances.
 */

public class TestBinBuilder {

    @Test
    public void testWheelBinsContainOutcomes() {
        Wheel wheel1 = new Wheel();
        for (int i = 0; i < wheel1.getSize(); i++) {
            Assert.assertTrue(wheel1.getBin(i).size() > 0);
        }
    }
}
