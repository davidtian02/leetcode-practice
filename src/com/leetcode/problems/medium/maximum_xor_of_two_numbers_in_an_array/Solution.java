package com.leetcode.problems.medium.maximum_xor_of_two_numbers_in_an_array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3462/
class Solution {
    // in order to find the "max" we need to find all possibilities of the xor such that the other xor element exists in the array/set
    // so to do this, you need to first assume the maximum bits. obviously 0x11111111... is the biggest
    // but you need to see what can be formed in the array.
    // by keeping tracking of your MSB 1's/0's, you guarantee that at each step, you have the max that is generatable from the array.
    public int findMaximumXOR(int[] nums) {
        if (nums.length == 1) return 0;

        // first find max, see how long the MSB is
        int msbs = findSeedMask(nums); // most significant bits.
        int max = 0;
        int terminator = msbs;
        while (terminator > 0) {
            final int mask = msbs, tempMax = max, tempTerminator = terminator;
            Set<Integer> set = Arrays.stream(nums).map(n->n&mask).boxed().collect(Collectors.toSet());
            boolean lacksBit = set.stream().noneMatch(a->set.contains(a ^ (tempMax | tempTerminator)));
            max = (max | (lacksBit? 0 : terminator));
            msbs = msbs | (msbs >> 1); // to make this sequence 1000 -> 1100 -> 1110 -> 1111
            terminator >>= 1;
        }

        return max;
    }

    private int findSeedMask(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int i = 1;
        while ((max>=i) && i<(1<<30)) {
            i <<= 1;
        }
        return i; // actually this is one over
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.findMaximumXOR(new int[]{8,10,2}));
        System.out.println(runner.findMaximumXOR(new int[]{2147483647,2147483646,2147483645}));
        System.out.println(runner.findMaximumXOR(new int[]{2,3,5}));
        System.out.println(runner.findMaximumXOR(new int[]{2,1}));
        System.out.println(runner.findMaximumXOR(new int[]{0, 0}));
        System.out.println(runner.findMaximumXOR(new int[]{0}));
        System.out.println(runner.findMaximumXOR(new int[]{1,1}));
    }
}
