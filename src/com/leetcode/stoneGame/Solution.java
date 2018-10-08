package com.leetcode.stoneGame;

import javafx.util.Pair;

import java.util.*;

class Solution {
    public boolean stoneGame(int[] piles) {
        return canAlexWin(piles, 0, piles.length -1, 0, 0);
    }

    Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>();

    private boolean canAlexWin(int[] piles, int i, int j, int alex, int lee) {
        if (i >= j-1) {
            if (alex + piles[i] > lee + piles[j] || alex + piles[j] > lee + piles[i]) {
                cache.put(new Pair<>(i, j), true);
                return true;
            } else {
                cache.put(new Pair<>(i, j), false);
                return false;
            }
        }

        boolean result;
        boolean totalResult = false;

        Pair<Integer, Integer> pair = new Pair<>(i+1, j-1);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        } else {
            result = canAlexWin(piles, i+1, j-1, alex+piles[i], lee+piles[j]);
            if (result) {
                cache.put(pair, true);
                // return true;
            }
            totalResult = result || totalResult;

            result = canAlexWin(piles, i+1, j-1, alex+piles[j], lee+piles[i]);
            if (result) {
                cache.put(pair, true);
                // return true;
            }

            totalResult = result || totalResult;

            // cache.put(pair, totalResult);
        }

        pair = new Pair<>(i+2, j);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        } else {
            result = canAlexWin(piles, i+2, j, alex + piles[i], lee + piles[i + 1]);
            if (result) {
                cache.put(pair, true);
                // return true;
                // } else {
                //     cache.put(pair, false);
            }
            totalResult = result || totalResult;
        }

        pair = new Pair<>(i, j-2);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        } else {
            result = canAlexWin(piles, i, j - 2, alex + piles[j], lee + piles[j - 1]);
            if (result) {
                cache.put(pair, true);
                // return true;
                // } else {
                //     cache.put(pair, false);
            }
            totalResult = result || totalResult;
        }

        // cache.put(new Pair<>(i, j), totalResult); // this won't work
        return totalResult;
        // alex takes left, lee takes right
        // alex takes right, lee takes left
        // alex takes left, lee takes left
        // alex takes right, lee takes right
    }
}