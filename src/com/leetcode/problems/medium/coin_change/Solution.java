package com.leetcode.problems.medium.coin_change;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/coin-change/
class Solution {
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> cache = new HashMap<>();

        List<Integer> coinBag = Arrays.stream(coins).sorted().boxed().collect(Collectors.toList());
        Collections.reverse(coinBag);
        int[] sortedCoins = coinBag.stream().mapToInt(a -> a).toArray();

        Integer result = checkCoins(sortedCoins, amount, cache);
        return result == null ? -1 : result;
    }

    private Integer checkCoins(int[] bag, int target, Map<Integer, Integer> cache) {
        if (target <= 0) {
            return target == 0 ? 0 : -1;
        }
        if (cache.containsKey(target)) {
            return cache.get(target);
        }

        int minCoins = Integer.MAX_VALUE;
        boolean hasSolution = false;
        for (int coin : bag) {
            int count = checkCoins(bag, target - coin, cache);
            if (count >= 0) { // wasn't impossible
                minCoins = Math.min(minCoins, count+1);
                hasSolution = true;
            }
        }

        minCoins = hasSolution ? minCoins : -1;
        cache.put(target, minCoins);
        return minCoins;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.coinChange(new int[]{1,2,5}, 11));
        System.out.println(runner.coinChange(new int[]{186,419,83,408}, 6249));
        System.out.println(runner.coinChange(new int[]{77,82,84,80,398,286,40,136,162}, 9794));
    }
}
