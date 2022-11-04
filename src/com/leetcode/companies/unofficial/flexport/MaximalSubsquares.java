package com.leetcode.companies.unofficial.flexport;

public class MaximalSubsquares {

    public int findMaxSubSquareSize(int[][] matrix) {
        int[][] cache = new int[matrix.length+1][matrix[0].length+1]; // assume valid matrix. else would check this
        int max = Integer.MIN_VALUE;

        for (int row=1; row<cache.length; row++) {
            for (int col=1; col<cache[0].length; col++) {
                int i = row-1;
                int j = col-1;
                if (matrix[i][j] == 0) {
                    cache[row][col] = 0;
                } else {
                    if (cache[row-1][col] > 0 && cache[row][col-1] > 0) {
                        int localMin = Math.min(cache[row-1][col], cache[row][col-1]);
                        localMin = Math.min(cache[row-1][col-1], localMin); // revisit
                        int t = (int) (Math.sqrt(localMin) + 1);
                        cache[row][col] = t*t;
                    } else {
                        cache[row][col] = 1;
                    }
                }

                max = Math.max(max, cache[row][col]);
            }
        }

        return max;
    }

    public static void main(String... args) {
        test1();
    }

    private static void test1() {
        int result = new MaximalSubsquares().findMaxSubSquareSize(new int[][]{
                new int[]{1, 1},
                new int[]{1, 1},
        });

        System.out.println(result == 4); // should be 4
    }
}
