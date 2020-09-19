package com.leetcode.problems.hard.validate_number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String... args) {
//        other();

//        new Solution().isNumber("84656e656D");
        System.out.println(Float.parseFloat("210D"));
    }

    private static void other() {
        //        System.out.println(Integer.parseInt("2e10"));
        System.out.println(Integer.parseInt("+210"));
//        System.out.println(Integer.parseInt("656D"));
//        System.out.println(Integer.parseInt("1,000"));
        System.out.println(Float.parseFloat("210f"));
        System.out.println(Float.parseFloat("+210"));


//        System.out.println(Float.parseFloat("1,000"));

        String[] es = "10e ee003".split("e");
        String[] es1 = "10e003".split("e");
        String[] es2 = "4e1.e".split("e");
        System.out.println(es.length);
        System.out.println(es2.length);
        for (String token : es2) {
            System.out.println(token);
        }

        System.out.println("========================");

        Pattern pattern = Pattern.compile("[0-9]{1}?");
        Matcher matcher = pattern.matcher("11325x");
        System.out.println(matcher.find());

        System.out.println("1133".matches("[0-9]{3}"));
        System.out.println("========================");

        String input = "   blah   ";
        String regexLeadingWhitespace = "\\s+.*";
        String regexTrailingWhitespace = ".*\\s+";

        boolean leadingMatches = Pattern.matches(regexLeadingWhitespace, input);
        boolean trailingMatches = Pattern.matches(regexTrailingWhitespace, input);

        System.out.println(leadingMatches);
        System.out.println(trailingMatches);
    }
}
