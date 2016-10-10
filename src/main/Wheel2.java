package main;

import java.util.*;

/**
 * Created by iiekovenko on 03.10.16.
 * Model of roulette wheel containing 38 bins and able
 * to pick one of bins randomly.
 * Bins are stored as an indexed sequence 0, 1, 2, ..., 36, 00
 */
public class Wheel2 {

    private List<Bin> bins;
    private final Random rng;
    private Set<Outcome> allOutcomes;

    public static class Builder {
        private final Random rng;
        private final int wheelSize = 38;
        private List<Bin> bins;
        private Set<Outcome> allOutcomes;

        public Builder(Random rng) {
            this.rng = rng;
            bins = new ArrayList<>(wheelSize);
            for (int i = 0; i < wheelSize; i++) {
                bins.add(new Bin());
            }
            generateStraightBets();
            generateFiveBet();
            generateSplitBets();
            generateStreetBets();
            generateCornerBets();
            generateLineBets();
            generateDozenBets();
            generateColumnBets();
            generateEvenMoneyBets();
        }

        public Wheel2 build() {
            return new Wheel2(this);
        }
        private void addOutcome(int bin, Outcome oc) {
            allOutcomes.add(oc);
            bins.get(bin).add(oc);
        }
        private void generateFiveBet() {
            Outcome five = new Outcome("00-0-1-2-3", RouletteGame.FIVEBET);
//            bins.get(37).add(five);
            addOutcome(37, five);

            for (int i=0; i<6; i++) {
//                bins.get(i).add(five);
                addOutcome(i, five);
            }
        }
        private void generateStraightBets() {
//            bins.get(37).add(
//                    new Outcome("00", RouletteGame.STRAIGHTBET));
            addOutcome(37, new Outcome("00", RouletteGame.STRAIGHTBET));
            for (int i=0; i<37; i++) {
//                bins.get(i).add(
//                        new Outcome(Integer.toString(i),
//                                RouletteGame.STRAIGHTBET));
                addOutcome(i, new Outcome(Integer.toString(i),
                        RouletteGame.STRAIGHTBET));

            }
        }
        private void generateSplitBets() {
            Outcome oc;
            int n;
            for (int row=0; row<12; row++) {
                for (int col=0; col<2; col++) {
                    n = row * 3 + 1 + col;
                    oc = new Outcome(
                            String.format("Split %d-%d", n, n + 1),
                            RouletteGame.SPLITBET);
//                    bins.get(n).add(oc);
//                    bins.get(n + 1).add(oc);
                    addOutcome(n, oc);
                    addOutcome(n + 1, oc);
                }
            }
        }
        private void generateStreetBets() {
            Outcome street;
            int n;
            for (int row=0; row<12; row++) {
                n = row * 3 + 1;
                street = new Outcome(
                        String.format("Street %d-%d-%d", n, n+1, n+2),
                        RouletteGame.STREETBET);
//                bins.get(n).add(street);
//                bins.get(n + 1).add(street);
//                bins.get(n + 2).add(street);
                addOutcome(n, street);
                addOutcome(n + 1, street);
                addOutcome(n + 2, street);
            }
        }
        private void generateCornerBets() {
            Outcome corner;
            int n;
            for (int row=0; row<11; row++) {
                for (int col=0; col<2; col++) {
                    n = row * 3 + 1 + col;
                    corner = new Outcome(String.format(
                            "Corner %d-%d-%d-%d", n, n + 1, n + 3, n + 4),
                            RouletteGame.CORNERBET);
//                    bins.get(n).add(corner);
//                    bins.get(n + 1).add(corner);
//                    bins.get(n + 3).add(corner);
//                    bins.get(n + 4).add(corner);
                    addOutcome(n, corner);
                    addOutcome(n + 1, corner);
                    addOutcome(n + 3, corner);
                    addOutcome(n + 4, corner);
                }
            }
        }
        private void generateLineBets() {
            Outcome line;
            int n;
            for (int row=0; row<11; row++) {
                n = row * 3 + 1;
                line = new Outcome(String.format(
                        "Line %d-%d-%d-%d-%d-%d",
                        n, n+1, n+2, n+3, n+4, n+5),
                        RouletteGame.LINEBET);
                addOutcome(n, line);
                addOutcome(n + 1, line);
                addOutcome(n + 2, line);
                addOutcome(n + 3, line);
                addOutcome(n + 4, line);
                addOutcome(n + 5, line);
            }
        }
        private void generateDozenBets() {
            Outcome dozen;
            int start, end;
            for (int d=0; d<3; d++) {
                start = d * 12 + 1;
                end = (d + 1) * 12;
                dozen = new Outcome(String.format(
                        "Dozen %d-%d", start, end), RouletteGame.DOZENBET);
                for (int i=start; i<=end; i++) {
                    addOutcome(i, dozen);
                }
            }
        }
        private void generateColumnBets() {
            Outcome column;
            for (int col=1; col<=3; col++) {
                column = new Outcome(String.format(
                        "Column %d", col), RouletteGame.COLUMNBET);
                for (int row=0; row<12; row++) {
                    addOutcome(col + row * 3, column);
                }
            }
        }
        private void generateEvenMoneyBets() {
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
                if (i < 19) addOutcome(i, low);
                else addOutcome(i, high);
                if (i % 2 == 0) addOutcome(i, even);
                else addOutcome(i, odd);
                if (reds.contains(i)) addOutcome(i, red);
                else addOutcome(i, black);
            }
        }
    }

    public Wheel2(Builder builder) {
        bins = builder.bins;
        rng = builder.rng;
        allOutcomes = builder.allOutcomes;
    }
    public Bin next() {
        return bins.get(rng.nextInt(bins.size()));
    }
    public Bin getBin(int bin) {
        return bins.get(bin);
    }
    public Set<Outcome> getOutcome(String name) {
        Set<Outcome> result = new HashSet<>();
        for (Outcome oc : allOutcomes) {
            if (oc.getName().startsWith(name))
                result.add(oc);
        }
        return result;
    }
}
