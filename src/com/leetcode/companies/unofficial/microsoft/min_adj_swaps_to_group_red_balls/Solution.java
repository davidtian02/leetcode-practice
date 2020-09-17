package com.leetcode.companies.unofficial.microsoft.min_adj_swaps_to_group_red_balls;

// https://leetcode.com/discuss/interview-question/414660/
class Solution {

    private final int MAX_LIMIT = 1000000000;

    public int minSwaps(String sequence) {
        int swapCount = 0;
        Integer leftMostIndexOfR = null, rightMostIndexOfR = null;
        // first find left and right most R's. then find midpoint between.
        for (int i=0,len=sequence.length(); i<len; i++) {
            if (leftMostIndexOfR != null && rightMostIndexOfR != null) {
                break;
            }

            if (leftMostIndexOfR == null && sequence.charAt(i) == 'R') {
                leftMostIndexOfR = i;
            }

            if (rightMostIndexOfR == null && sequence.charAt(len-i-1) == 'R') {
                rightMostIndexOfR = len-i-1;
            }
        }

        if (leftMostIndexOfR == rightMostIndexOfR || leftMostIndexOfR.equals(rightMostIndexOfR)) {
            return 0; // only 1 element or both null
        }

//        pick the most center R, and then everyone else move to center?
        int midIndexOfR = findMidR(sequence, leftMostIndexOfR, rightMostIndexOfR);
        int leftCount = countLeft(sequence, leftMostIndexOfR, midIndexOfR);
        if (leftCount == -1) {
            return -1;
        }
        int rightCount = countRight(sequence, midIndexOfR, rightMostIndexOfR);

        // go from mid to both sides, counting # of swaps. return the lesser of the two?

        return leftCount + rightCount;
    }

    private int countLeft(String sequence, Integer leftMostIndexOfR, int midIndexOfR) {
        int sum = 0;
        int count = 1;
        for (int i=midIndexOfR-1; i>=leftMostIndexOfR; i--) { // TODO edge case only 1 R on leftmost
            if (count > MAX_LIMIT) {
                return -1;
            }

            if (sequence.charAt(i) == 'R') {
                count--;
                sum += count;
            }

            count++;
        }
        return sum;
    }

    private int countRight(String sequence, int midIndexOfR, Integer rightMostIndexOfR) {
        int sum = 0;
        int count = 1;
        for (int i=midIndexOfR+1; i<=rightMostIndexOfR; i++) { // TODO edge case only 1 R on rightMost?
            if (count > MAX_LIMIT) {
                return -1;
            }
            if (sequence.charAt(i) == 'R') {
                count--;
                sum += count;
            }

            count++;
        }
        return sum;
    }

    private int findMidR(String sequence, Integer leftMostIndexOfR, Integer rightMostIndexOfR) {
        int rCount = 0;
        for (int i=leftMostIndexOfR; i<=rightMostIndexOfR; i++) {
            if ('R' == sequence.charAt(i)) {
                rCount++;
            }
        }

        int mid = (rCount&0x01)==1?rCount/2+1 : rCount>>1;
        rCount = 0; // reset to reuse
        int index = -1;
        for (int i=leftMostIndexOfR; i<=rightMostIndexOfR; i++) {
            if ('R' == sequence.charAt(i)) {
                rCount++;
            }

            if (rCount == mid) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.minSwaps("RWR"));
        System.out.println(runner.minSwaps("RR"));
        System.out.println(runner.minSwaps("R"));
        System.out.println(runner.minSwaps("WWW"));
        System.out.println(runner.minSwaps("WWWRRRR"));
        System.out.println(runner.minSwaps("RWRWRWR"));
        System.out.println(runner.minSwaps("RWRWRWRWRWRWRWR"));
        System.out.println(runner.minSwaps("RWWWWWR"));
        System.out.println(runner.minSwaps("RWRWRWWWWWWRRRR"));
        System.out.println(runner.minSwaps("RWRWRWWWWWWRRRRR"));
        System.out.println(runner.minSwaps("RWRWRWRWWWWWWRRRR"));
        System.out.println(runner.minSwaps("WRWRWRWRWWWWWWRRRRW"));
    }
}
/**
 * 1
 * 0
 * 0
 * 0
 * 0
 * 4
 * 16
 * 5
 * 21
 * 21
 * 30
 * 30
 */