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
}
