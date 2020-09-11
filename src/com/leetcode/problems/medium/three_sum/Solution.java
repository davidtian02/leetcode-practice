package com.leetcode.problems.medium.three_sum;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/3sum/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.containsKey(n) ? 1+freq.get(n) : 1);
        }
        List<List<Integer>> solutions = findCombos(nums, freq);
        solutions = unique(solutions);
        return solutions;
    }

    private List<List<Integer>> findCombos(int[] nums, Map<Integer, Integer> freq) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i=0; i<nums.length; i++) {
            if (i>0 && nums[i]==nums[i-1]) {
                continue; // so we skip overloops
            }
            for (int j=i+1; j<nums.length; j++) {
                int a = nums[i];
                int b = nums[j];
                int c = -(a+b);
                freq.put(a, freq.get(a)-1);
                freq.put(b, freq.get(b)-1);
                if (freq.containsKey(c) && freq.get(c) > 0) {
                    result.add(Arrays.asList(a,b,c));
                }
                freq.put(a, freq.get(a)+1);
                freq.put(b, freq.get(b)+1);
            }
        }
        return result;
    }

    private List<List<Integer>> unique(List<List<Integer>> solutions) {
        Set<String> uniq = new HashSet<>();
        for (List<Integer> list : solutions) {
            Collections.sort(list); // TODO can do customized code later, since only 3 elements
            uniq.add(list.toString());
        }

        List<List<Integer>> result = new LinkedList<>();
        for (String u : uniq) {
            String[] nums = u.substring(1,u.length()-1).split(", ");
            result.add(Arrays.stream(nums).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
        }
        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        System.out.println(runner.threeSum(new int[]{0}));
//        System.out.println(runner.threeSum(new int[]{0,2,5}));
//        System.out.println(runner.threeSum(new int[]{-1,0,1}));
//        System.out.println(runner.threeSum(new int[]{-1,-5,-4,1,5,10,2,6,1}));
        System.out.println(runner.threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
    }
}
