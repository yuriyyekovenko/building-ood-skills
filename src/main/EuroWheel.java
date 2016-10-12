package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by iiekovenko on 12.10.16.
 * Roulette wheel with 37 bins (euro style - without 00).
 */
public class EuroWheel extends Wheel {
    @Override
    void buildBins() {
        for (int i = 0; i < 37; i++)
            bins.add(new Bin());
        BinBuilder builder = new EuroBinBuilder();
        builder.buildBins(this);
    }
}
