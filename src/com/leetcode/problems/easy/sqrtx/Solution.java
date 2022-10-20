package com.leetcode.problems.easy.sqrtx;

class Solution {
    public int mySqrt(int x) {
        int max = 46340; // square root of 2^31 - 2
        if (x >= max * max) {
            return max;
        }
        return bst(x, 0, max);
    }

    private int bst(int targetProduct, int left, int right) {
        int mid = ((right - left) >> 1) + left;
        int l = check(targetProduct, left);
        int m = check(targetProduct, mid);
        int r = check(targetProduct, right);

        if (l >= 0) return l;
        if (m >= 0) return m;
        if (r >= 0) return r;

        return (mid*mid > targetProduct) ? bst(targetProduct, left+1, mid-1) : bst(targetProduct, mid+1, right-1);
    }

    private int check(int target, int num) {
        int p1, p2;
        p1 = num * num;
        p2 = (num+1) * (num+1);
        if (p1 <= target && target <= p2) {
            return target == p2 ? num+1 : num;
        }

        return -1;
    }

    public int mySqrt1(int x) {
        int i=1;
        if (x < 2) return x;
        int product = 0;
        while (product <= x && product >= 0) {
            product = i * i;
            i++;
        }
        return i-2;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.mySqrt(2147483647));
        System.out.println(runner.mySqrt(4));
        System.out.println(runner.mySqrt(8));
        System.out.println(runner.mySqrt(0));
        System.out.println(runner.mySqrt(1));
        System.out.println(runner.mySqrt(2));
        System.out.println(runner.mySqrt(3));
        System.out.println(runner.mySqrt(4));
        System.out.println(runner.mySqrt(5));
        System.out.println(runner.mySqrt(6));
        System.out.println(runner.mySqrt(25));
        System.out.println(runner.mySqrt(999));
        System.out.println(runner.mySqrt(2147395600));
    }
}
