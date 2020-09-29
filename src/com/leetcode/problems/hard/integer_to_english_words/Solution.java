package com.leetcode.problems.hard.integer_to_english_words;

// https://leetcode.com/problems/integer-to-english-words/
class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        int upToThousand = num % 1000;
        int upToMillion = 0;
        int upToBillion = 0;
        int upToTrillion = 0;
        if (num >= 1000) {
            num /= 1000;
            upToMillion = num % 1000;
        }
        if (num >= 1000) {
            num /= 1000;
            upToBillion = num % 1000;
        }
        if (num >= 1000) {
            num /= 1000;
            upToTrillion = num % 1000;
        }

        StringBuilder sb = new StringBuilder();
        if (upToTrillion > 0) {
            sb.append(englishify(upToTrillion)).append("Billion ");
        }

        if (upToBillion > 0) {
            sb.append(englishify(upToBillion)).append("Million ");
        }

        if (upToMillion > 0) {
            sb.append(englishify(upToMillion)).append("Thousand ");
        }

        sb.append(englishify(upToThousand));
        return sb.toString().trim();
    }

    private StringBuilder englishify(int num) {
        StringBuilder sb = new StringBuilder();
        if (num / 100 > 0) {
            sb.append(getDigitWord(num/100)).append("Hundred ");
        }
        num %= 100;
        if (num >= 10 && num < 20) { // special words
            sb.append(getTwoDigitCombinedWord(num));
        } else {
            if (num >= 20) {
                sb.append(getTwoDigitWord(num/10));
            }
            if (num % 10 != 0) {
                sb.append(getDigitWord(num%10));
            }
        }

        return sb;
    }

    private StringBuilder getDigitWord(int n) {
        switch(n) {
            case 1: return new StringBuilder("One ");
            case 2: return new StringBuilder("Two ");
            case 3: return new StringBuilder("Three ");
            case 4: return new StringBuilder("Four ");
            case 5: return new StringBuilder("Five ");
            case 6: return new StringBuilder("Six ");
            case 7: return new StringBuilder("Seven ");
            case 8: return new StringBuilder("Eight ");
            case 9: return new StringBuilder("Nine ");
        }
        return new StringBuilder("ERROR!" + n);
    }

    private StringBuilder getTwoDigitWord(int n) {
        switch(n) {
            case 2: return new StringBuilder("Twenty ");
            case 3: return new StringBuilder("Thirty ");
            case 4: return new StringBuilder("Forty ");
            case 5: return new StringBuilder("Fifty ");
            case 6: return new StringBuilder("Sixty ");
            case 7: return new StringBuilder("Seventy ");
            case 8: return new StringBuilder("Eighty ");
            case 9: return new StringBuilder("Ninety ");
        }
        return new StringBuilder("ERROR!" + n);
    }

    private StringBuilder getTwoDigitCombinedWord(int n) {
        switch(n) {
            case 10: return new StringBuilder("Ten ");
            case 11: return new StringBuilder("Eleven ");
            case 12: return new StringBuilder("Twelve ");
            case 13: return new StringBuilder("Thirteen ");
            case 14: return new StringBuilder("Fourteen ");
            case 15: return new StringBuilder("Fifteen ");
            case 16: return new StringBuilder("Sixteen ");
            case 17: return new StringBuilder("Seventeen ");
            case 18: return new StringBuilder("Eighteen ");
            case 19: return new StringBuilder("Nineteen ");
        }
        return new StringBuilder("ERROR!" + n);
    }
}
