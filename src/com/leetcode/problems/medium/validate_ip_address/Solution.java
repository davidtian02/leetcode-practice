package com.leetcode.problems.medium.validate_ip_address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://leetcode.com/problems/validate-ip-address/
public class Solution {

    public String validIPAddress(String IP) {
        if (isIPV4(IP)) {
            return "IPv4";
        } else if (isIPV6(IP)) {
            return "IPv6";
        }

        return "Neither";
    }

    private boolean isIPV4(String ip) {
        Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
        Matcher matcher = pattern.matcher(ip);
        if (matcher.find()) {
            if (matcher.groupCount() != 4) {
                return false;
            }
            String[] tokens = new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)};
            int[] nums = new int[4];
            for (int i=0; i<nums.length; i++) {
                try {
                    nums[i] = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (!tokens[i].equals(""+nums[i]) || nums[i] < 0 || nums[i] > 255) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean isIPV6(String ip) {
        Pattern pattern = Pattern.compile("^([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+):([0-9a-fA-F]+)$");
        Matcher matcher = pattern.matcher(ip);
        if (matcher.find()) {
            if (matcher.groupCount() != 8) {
                return false;
            }
            String[] tokens = new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6), matcher.group(7), matcher.group(8)};
            for (String t : tokens) {
                if (t.length() > 4) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static void main(String...args) {
//        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
//        Matcher matcher = pattern.matcher("9999222.2.8.111");
//        if (matcher.find()) {
//            System.out.println(matcher.groupCount());
////            System.out.println(matcher.group());
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            String last = matcher.group(4);
//            System.out.println(last);
//        } else {
//            System.out.println("nope");
//        }


//        Pattern pattern = Pattern.compile("([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+)");
//        Matcher matcher = pattern.matcher("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
//        if (matcher.find()) {
//            System.out.println(matcher.groupCount());
//            System.out.println(matcher.group());
//            System.out.println(matcher.group(1));
//        } else {
//            System.out.println("nope");
//        }

        Solution runner = new Solution();
//        System.out.println(runner.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
//        System.out.println(runner.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        System.out.println(runner.validIPAddress("1e1.4.5.6"));
    }
}
