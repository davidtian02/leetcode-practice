package com.leetcode.medianOfTwoSortedArrays;

import java.util.ArrayList;
import java.util.List;

// FIXME this should run in log(n) time
class Solution {
    // naive, in linear time - https://leetcode.com/submissions/detail/176567878/
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i=0;
        int j=0;
        while (i<=nums1.length && j<=nums2.length) {
            if (i==nums1.length) {
                while(j<nums2.length) {
                    list.add(nums2[j]);
                    j++;
                }
                break;
            } else if (j==nums2.length) {
                while(i<nums1.length) {
                    list.add(nums1[i]);
                    i++;
                }
                break;
            } else {
                if (nums1[i] < nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                } else {
                    list.add(nums2[j]);
                    j++;
                }
            }
        }

        int length = nums1.length + nums2.length;
        if ((length & 0x01) == 1) {
            return list.get(length/2);
        } else {
            return (list.get(length/2 - 1) + list.get(length/2))/2.0f;
        }
    }
}
