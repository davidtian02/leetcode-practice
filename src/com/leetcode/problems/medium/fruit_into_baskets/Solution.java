package com.leetcode.problems.medium.fruit_into_baskets;

class Solution {
    // 4:00
    public int totalFruit(int[] fruits) {
        Integer typeA = null, typeB = null;
        if (fruits.length < 3) {
            return fruits.length;
        }

        int max = 0;
        typeA = fruits[0];
        if (fruits[1] != fruits[0]) {
            typeB = fruits[1];
        }

        int count = 2;
        for (int i=2; i<fruits.length; i++) {
            if (fruits[i] == typeA) {
                count++;
            } else if (typeB == null) {
                typeB = fruits[i];
                count++;
            } else if (typeB == fruits[i]){
                count++;
            } else { // neither type is equal
                // switch baskets
                typeA = fruits[i-1];
                typeB = fruits[i];
                count = 2;
                // you need to reverse account for previously coulda picked up fruits

                // System.out.println("switching fruits, at: " + i);
                // System.out.println("typeA is: "+ typeA + ", typeB is " + typeB);
                int j=2;
                while(j>=0 && (fruits[i-j] == typeA || fruits[i-j] == typeB)) {
                    count++;
                    j++;
                }
            }

            if (count > max) {
                max = count;
            }
        }

        return max;
    }
}