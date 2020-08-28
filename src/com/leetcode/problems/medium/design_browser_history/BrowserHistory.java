package com.leetcode.problems.medium.design_browser_history;

// https://leetcode.com/problems/design-browser-history/
class BrowserHistory {
    Node head, current;
    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        current = head;
    }

    public void visit(String url) {
        Node n = new Node(url);
        current.left = n;
        n.right = current;
        current = n;
    }

    public String back(int steps) {
        int i=0;
        while (current != head && i < steps) {
            current = current.right;
            i++;
        }
        return current.url;
    }

    public String forward(int steps) {
        int i=0;
        while (current.left != null && i < steps) {
            current = current.left;
            i++;
        }
        return current.url;
    }

    static class Node {
        String url;
        Node(String url) {this.url = url;}
        Node left, right;
    }

    public static void main(String... args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        System.out.println(browserHistory.back(1));                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        System.out.println(browserHistory.back(1));                   // You are in "facebook.com", move back to "google.com" return "google.com"
        System.out.println(browserHistory.forward(1));                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
        System.out.println(browserHistory.forward(2));                // You are in "linkedin.com", you cannot move forward any steps.
        System.out.println(browserHistory.back(2));                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        System.out.println(browserHistory.back(7));                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */