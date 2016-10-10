package test;

import java.util.Random;

/**
 * Created by iiekovenko on 05.10.16.
 */
public class NonRandom extends Random {
    private long value;

    public NonRandom() {}

    @Override
    public void setSeed(long value) {
        this.value = value;
    }
    @Override
    protected int next(int bits) {
        return (int)value;
    }
}
