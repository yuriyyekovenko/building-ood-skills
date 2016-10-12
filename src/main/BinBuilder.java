package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//TODO Maybe, the best implementation is with nested builder class in Wheel2.
/**
 * Created by iiekovenko on 05.10.16.
 * Builder creates the Outcomes for all of the 38
 * individual Bins (including 00) on a Roulette wheel.
 */

public class BinBuilder {
    Wheel wheel;
    int binCount;

    public Wheel buildBins(Random rng) {
        binCount = 38; //including double-zero
        wheel = new Wheel(rng);
        for (int i = 0; i < binCount; i++)
            this.wheel.addBin(new Bin());

        generateStraightBets();
        generateDoubleZeroBet();
        generateZeroBet();
        generateFiveBet();
        generateSplitBets();
        generateStreetBets();
        generateCornerBets();
        generateLineBets();
        generateDozenBets();
        generateColumnBets();
        generateEvenMoneyBets();
        return wheel;
    }
    private void generateFiveBet() {
        Outcome five = new Outcome("00-0-1-2-3", RouletteGame.FIVEBET);
        wheel.addOutcome(37, five);
        for (int i=0; i<6; i++) {
            wheel.addOutcome(i, five);
        }
    }
    private void generateDoubleZeroBet() {
        wheel.addOutcome(37,
                new Outcome("00", RouletteGame.STRAIGHTBET));
    }
    void generateZeroBet() {
        wheel.addOutcome(0,
                new Outcome("0", RouletteGame.STRAIGHTBET));
    }
    void generateStraightBets() {
        for (int i=1; i<=36; i++) {
            wheel.addOutcome(i,
                    new Outcome(Integer.toString(i),
                    RouletteGame.STRAIGHTBET));
        }
    }
    void generateSplitBets() {
        Outcome oc;
        int n;
        for (int row=0; row<12; row++) {
            for (int col=0; col<3; col++) {
                n = row * 3 + 1 + col;
                if (col < 2) {
                    oc = new Outcome(
                            String.format("Split %d-%d", n, n + 1),
                            RouletteGame.SPLITBET);
                    wheel.addOutcome(n, oc);
                    wheel.addOutcome(n + 1, oc);
                }
                if (row < 11) {
                    oc = new Outcome(
                            String.format("Split %d-%d", n, n + 3),
                            RouletteGame.SPLITBET);
                    wheel.addOutcome(n, oc);
                    wheel.addOutcome(n + 3, oc);
                }
            }
        }
    }
    void generateStreetBets() {
        Outcome street;
        int n;
        for (int row=0; row<12; row++) {
            n = row * 3 + 1;
            street = new Outcome(
                    String.format("Street %d-%d-%d", n, n+1, n+2),
                    RouletteGame.STREETBET);
            wheel.addOutcome(n, street);
            wheel.addOutcome(n+1, street);
            wheel.addOutcome(n+2, street);
        }
    }
    void generateCornerBets() {
        Outcome corner;
        int n;
        for (int row=0; row<11; row++) {
            for (int col=0; col<2; col++) {
                n = row * 3 + 1 + col;
                corner = new Outcome(String.format(
                        "Corner %d-%d-%d-%d", n, n + 1, n + 3, n + 4),
                        RouletteGame.CORNERBET);
                wheel.addOutcome(n, corner);
                wheel.addOutcome(n + 1, corner);
                wheel.addOutcome(n + 3, corner);
                wheel.addOutcome(n + 4, corner);
            }
        }
    }
    void generateLineBets() {
        Outcome line;
        int n;
        for (int row=0; row<11; row++) {
            n = row * 3 + 1;
            line = new Outcome(String.format(
                    "Line %d-%d-%d-%d-%d-%d",
                    n, n+1, n+2, n+3, n+4, n+5),
                    RouletteGame.LINEBET);
            wheel.addOutcome(n, line);
            wheel.addOutcome(n+1, line);
            wheel.addOutcome(n+2, line);
            wheel.addOutcome(n+3, line);
            wheel.addOutcome(n+4, line);
            wheel.addOutcome(n+5, line);
        }
    }
    void generateDozenBets() {
        Outcome dozen;
        int start, end;
        for (int d=0; d<3; d++) {
            start = d * 12 + 1;
            end = (d + 1) * 12;
            dozen = new Outcome(String.format(
                    "Dozen %d-%d", start, end), RouletteGame.DOZENBET);
            for (int i=start; i<=end; i++) {
                wheel.addOutcome(i, dozen);
            }
        }
    }
    void generateColumnBets() {
        Outcome column;
        for (int col=1; col<=3; col++) {
            column = new Outcome(String.format(
                    "Column %d", col), RouletteGame.COLUMNBET);
            for (int row=0; row<12; row++) {
                wheel.addOutcome(col + row * 3, column);
            }
        }
    }
    void generateEvenMoneyBets() {
        Outcome red = new Outcome("Red", RouletteGame.EVENMONEYBET);
        Outcome black = new Outcome("Black", RouletteGame.EVENMONEYBET);
        Outcome odd = new Outcome("Odd", RouletteGame.EVENMONEYBET);
        Outcome even = new Outcome("Even", RouletteGame.EVENMONEYBET);
        Outcome low = new Outcome("Low", RouletteGame.EVENMONEYBET);
        Outcome high = new Outcome("High", RouletteGame.EVENMONEYBET);

        Set<Integer> reds = new HashSet<>(Arrays.asList(
                1, 3, 5, 7, 9, 12, 14, 16, 18,
                19, 21, 23, 25, 27, 30, 32, 34, 36));

        for (int i = 1; i < 37; i++) {
            if (i < 19) wheel.addOutcome(i, low);
            else wheel.addOutcome(i, high);
            if (i % 2 == 0) wheel.addOutcome(i, even);
            else wheel.addOutcome(i, odd);
            if (reds.contains(i)) wheel.addOutcome(i, red);
            else wheel.addOutcome(i, black);
        }
    }
}
