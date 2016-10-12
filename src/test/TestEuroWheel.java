package test;

import main.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iiekovenko on 12.10.16.
 */
public class TestEuroWheel {
    @Test
    public void testEuroWheelSizeIs37() {
        Wheel wheel = new EuroWheel();
        Assert.assertEquals(wheel.getSize(), 37);
    }
    @Test
    public void testEuroWheelIncludesPrisonOutcome() {
        Wheel wheel = new EuroWheel();
        Outcome p = wheel.getOutcome("0").toArray(new Outcome[1])[0];
        Assert.assertEquals(p.getClass(), PrisonOutcome.class);
    }
}
