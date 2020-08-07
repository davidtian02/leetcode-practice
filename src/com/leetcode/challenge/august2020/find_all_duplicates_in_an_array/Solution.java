package com.leetcode.challenge.august2020.find_all_duplicates_in_an_array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// https://leetcode.com/explore/featured/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3414/
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dupes = new LinkedList<>();
        for (int i=0; i<nums.length; i++) {
            if (i != nums[i]-1) {
                startSwapping(nums, i, dupes);
            }
        }

        return dupes;
    }

    private void startSwapping(int[] nums, int i, List<Integer> dupes) {
        int index = i;
        int tempVal;
        while (index != (nums[index]-1) && nums[index] != -1) {
            tempVal = nums[index];
            if (nums[index] == nums[tempVal-1]) { // attempt to swap
                dupes.add(tempVal);
                nums[index] = -1;
                return; // do this?
            }
            nums[index] = nums[tempVal-1];
            nums[tempVal-1] = tempVal;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }

    private List<Integer> findDuplicates2(int[] nums) {
        Set<Integer> alreadySeen = new HashSet<>();
        List<Integer> dupes = new LinkedList<>();
        for (int i=0; i<nums.length; i++) {
            if (alreadySeen.contains(nums[i])) {
                dupes.add(nums[i]);
            } else {
                alreadySeen.add(nums[i]);
            }
        }
        return dupes;
    }
}
