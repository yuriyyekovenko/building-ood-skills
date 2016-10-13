package main;

import java.util.Random;

/**
 * Created by iiekovenko on 10.10.16.
 * Euro-style Roulette doesn't contain double-zero bin
 * and has special processing for Zero bet.
 */
public class EuroBinBuilder extends BinBuilder {

    public void buildBins(Wheel wheel) {
        this.wheel = wheel;
        generateStraightBets();
        generateZeroBet();
        generateSplitBets();
        generateStreetBets();
        generateCornerBets();
        generateLineBets();
        generateDozenBets();
        generateColumnBets();
        generateEvenMoneyBets();
    }
}
