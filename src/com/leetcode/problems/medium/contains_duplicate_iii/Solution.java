package com.leetcode.problems.medium.contains_duplicate_iii;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// https://leetcode.com/problems/contains-duplicate-iii/
class Solution {
    // bucket strat. each number hash by (maxDelta/t) into a bucket. if 2 numbers hit the same bucket or adjacent buckets, need to check
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for (int n : nums) {
            if (n<min) min = n;
            if (n>max) max = n;
        }

        if (k == 0) {
            return false;
        }

        long offset = min;
        Map<Integer, Integer> buckets = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int bucketIndex = (int)((nums[i] - offset) / (t+1));
            if (checkBucketsContain(buckets, bucketIndex, nums[i], t)) {
                return true;
            }

            buckets.put(bucketIndex, nums[i]);
            if (buckets.size() > k) {
                int indexToRemove = (int)((nums[i-k] - offset) / (t+1));
                buckets.remove(indexToRemove);
            }
        }

        return false;
    }

    private boolean checkBucketsContain(Map<Integer, Integer> buckets, int bucketIndex, int target, int maxAllowedDistance) {
        if (buckets.containsKey(bucketIndex)) {
            if (Math.abs(target-buckets.get(bucketIndex)) <= maxAllowedDistance) {
                return true;
            }
        }

        if (buckets.containsKey(bucketIndex-1)) {
            long prev = buckets.get(bucketIndex-1);
            if (Math.abs(target-prev) <= maxAllowedDistance) {
                return true;
            }
        }
        if (buckets.containsKey(bucketIndex+1)) {
            long next = buckets.get(bucketIndex+1);
            if (Math.abs(next-target) <= maxAllowedDistance) {
                return true;
            }
        }

        return false;
    }

    // we add elements into some structure (of size k) that can keep track of the "bubble" of nearby elements.
    // then we find floor/ceiling of the closest neighbor because it's sorted
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> bubble = new TreeSet<>();
        for (int i=0; i<nums.length; i++) {
            Integer minAbove = bubble.ceiling(nums[i]);
            if (minAbove != null && minAbove - ((long)nums[i]) <= t) {
                return true;
            }
            Integer maxBelow = bubble.floor(nums[i]);
            if (maxBelow != null && ((long)nums[i]) - maxBelow <= t) {
                return true;
            }
            bubble.add(nums[i]);
            if (bubble.size() > k) {
                bubble.remove(nums[i-k]);
            }
        }

        return false;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
//        System.out.println(runner.containsNearbyAlmostDuplicate(new int[]{2,0,-2,2}, 2, 1));
    }
}
