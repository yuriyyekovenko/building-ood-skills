package main;

import java.util.Random;

/**
 * Created by iiekovenko on 10.10.16.
 * Euro-style Roulette doesn't contain double-zero bin
 * and has special processing for Zero bet.
 */
public class EuroBinBuilder extends BinBuilder {

    public Wheel buildBins(Random rng) {
        binCount = 37;
        wheel = new Wheel(rng);
        for (int i = 0; i < binCount; i++)
            this.wheel.addBin(new Bin());

        generateStraightBets();
        generateZeroBet();
        generateSplitBets();
        generateStreetBets();
        generateCornerBets();
        generateLineBets();
        generateDozenBets();
        generateColumnBets();
        generateEvenMoneyBets();
        generateZeroBet();
        return wheel;
    }
//    @Override
//    protected void generateZeroBet() {
//        wheel.addOutcome(0,
//                new PrisonOutcome("0", RouletteGame.STRAIGHTBET));
//    }
}
