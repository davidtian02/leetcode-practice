package com.leetcode.companies.unofficial.microsoft.number_of_fractions_that_sum_up_to_1;

import javafx.util.Pair;

import java.util.*;

// https://leetcode.com/discuss/interview-question/684355/
class Solution {

    // can denoms be 0? edges
    public int numFractions(int[] numerators, int[] denominators) {
        // make sure more than 1 element
        if (numerators.length < 1) {
            return 0;
        }
        reduceBasis(numerators, denominators);
        List<Pair<Integer, Integer>> fractions = new ArrayList<>();
        for (int i=0; i<numerators.length; i++) {
            fractions.add(new Pair<>(numerators[i], denominators[i]));
        }
        fractions.sort(Comparator.comparingInt(Pair::getValue));

        return countByGroups(fractions);
    }

    private int countByGroups(List<Pair<Integer, Integer>> fractions) {
        int sum = 0;
        Pair prev = fractions.get(0);
        List<Pair<Integer, Integer>> sameDenominators = new LinkedList<>();
        sameDenominators.add(prev);
        for (int i=1; i<fractions.size(); i++) {
            Pair current = fractions.get(i);
            if (prev.getValue() != current.getValue()) {
                sum += countAllThatSumToOne(sameDenominators);
                sameDenominators = new LinkedList<>();
            }
            sameDenominators.add(current);

            prev = current;
        }

        sum += countAllThatSumToOne(sameDenominators);

        return sum;
    }

    private int countAllThatSumToOne(List<Pair<Integer, Integer>> sameDenominators) {
        Map<Integer, Integer> numeratorFrequency = new HashMap<>();
        int count = 0;
        int denominator = sameDenominators.get(0).getValue();
        for (Pair<Integer, Integer> p : sameDenominators) {
            if (numeratorFrequency.containsKey(p.getKey()))
                numeratorFrequency.put(p.getKey(), numeratorFrequency.get(p.getKey()) + 1);
            else numeratorFrequency.put(p.getKey(), 1);
        }

        while (!numeratorFrequency.isEmpty()) {
            Map.Entry<Integer, Integer> fraction = numeratorFrequency.entrySet().iterator().next();
            Integer picked = fraction.getKey();
            numeratorFrequency.put(picked, numeratorFrequency.get(picked) - 1);
            if (numeratorFrequency.get(picked) == 0) {
                numeratorFrequency.remove(picked);
            }
            int complement = denominator - picked;
            if (numeratorFrequency.containsKey(complement)) {
                count += numeratorFrequency.get(complement);
            }
            // what about 0/1 and 1/1 ? hmm.. more on this
        }

        return count;
    }

    private void reduceBasis(int[] numerators, int[] denominators) {
        // can we use modulo? TODO
        // first off, make sure numerator less than denom.
        for (int i=0; i<numerators.length; i++) {
            if (numerators[i] > denominators[i]) {
                numerators[i] = numerators[i] % denominators[i];
            }
            Pair<Integer, Integer> reduced = reduceBasis(numerators[i], denominators[i]);
            numerators[i] = reduced.getKey();
            denominators[i] = reduced.getValue();
        }
    }

    private Pair<Integer, Integer> reduceBasis(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        return new Pair<>(numerator/gcd, denominator/gcd);
    }

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.numFractions(new int[]{1,1}, new int[]{2,2}));
        System.out.println(runner.numFractions(new int[]{1,2,3,4}, new int[]{5,5,5,5}));
        System.out.println(runner.numFractions(new int[]{1,1,2}, new int[]{3,2,3}));
        System.out.println(runner.numFractions(new int[]{1,1,1}, new int[]{2,2,2}));
        System.out.println(runner.numFractions(new int[]{1,2,3,1,2,12,8,4}, new int[]{5,10,15,2,4,15,10,5}));
        System.out.println(runner.numFractions(new int[]{0,2}, new int[]{2,2}));
        System.out.println(runner.numFractions(new int[]{1,1}, new int[]{1,1}));
        System.out.println(runner.numFractions(new int[]{1}, new int[]{1}));
    }
}
