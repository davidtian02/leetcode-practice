package com.leetcode.twosums;

public class Main {

    /**
     * see submission at https://leetcode.com/submissions/detail/171178969/ accepted.
     */

    public static void main(String[] args) {
	    System.out.println("hi");

	    Solution runner = new Solution();
//        testCase(runner, new int[]{2, 7, 11, 19}, 9);
        testCase(runner, new int[]{3, 2, 4}, 6);
    }

    private static void testCase(Solution runner, int[] inputArray, int target) {
        int[] ints = runner.twoSum(inputArray, target);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        System.out.println("");
    }
}
