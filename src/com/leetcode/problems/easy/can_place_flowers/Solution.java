package com.leetcode.problems.easy.can_place_flowers;

// https://leetcode.com/problems/can-place-flowers/
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n > (1+(flowerbed.length>>1))) {
            return false;
        } else if (n == 0) {
            return true;
        }

        if (flowerbed.length < 2) {
            return flowerbed[0] == 0;
        }

        for (int i=0; i<flowerbed.length; i++) {
            if (n == 0) {
                break;
            }

            if (flowerbed[i] == 0) {
                if (i==0) {
                    if (flowerbed[i+1]==0) {
                        n--;
                        flowerbed[i] = 1;
                    }
                } else if (i==flowerbed.length-1) {
                    if (flowerbed[i-1]==0) {
                        n--;
                        flowerbed[i] = 1;
                    }
                } else {
                    if (flowerbed[i-1]==0 && flowerbed[i+1]==0) { // bound
                        n--;
                        flowerbed[i] = 1;
                    }
                }
            }
        }

        return n <= 0;
    }
}
