package com.leetcode.problems.hard.serialize_and_deserialize_binary_tree;


import java.math.BigInteger;
import java.util.*;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class Codec {
    // edges? how far? linked list? how many? only ints? or... can be null?
    private static final String NULL = "*";
    private static final String DELIMITER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL + DELIMITER); // for null; // TODO delimiter
            return;
        }

        sb.append(root.val).append(DELIMITER);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] tokens = data.split(DELIMITER);
        int len = tokens.length;
        if (tokens[tokens.length - 1].isEmpty()) {
            len--;
        }

        Stack<String> stack = new Stack<>();
        for (int i=len-1; i>=0; i--) {
            stack.push(tokens[i]);
        }

        String r = stack.pop();
        TreeNode root = new TreeNode(Integer.parseInt(r));
        deserializeHelper(root, stack);
        return root;
    }

    private void deserializeHelper(TreeNode root, Stack<String> stack) {
        if (root == null) {
            return;
        }

        String current = stack.pop();
        if (!NULL.equals(current)) {
            root.left = new TreeNode(Integer.parseInt(current));
            deserializeHelper(root.left, stack);
        }

        current = stack.pop();
        if (!NULL.equals(current)) {
            root.right = new TreeNode(Integer.parseInt(current));
            deserializeHelper(root.right, stack);
        }
    }

//    private static final String DELIMITER = ",";
//    private static final String INDEX_VALUE_DELIMITER = "~";
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        // tree encode. or see if u can do in-order traversal to encode just the index of where it would be if using a pyramid. but deserialize might be awk.
//        Queue<IndexableTreeNode> queue = new LinkedList<>();
//        if (root == null) {
//            return null;
//        }
//        BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
//
//        queue.add(new IndexableTreeNode(root, BigInteger.ZERO));
//        while (!queue.isEmpty()) {
//            IndexableTreeNode head = queue.remove();
//            sb.append(serializeNodeValueWithIndex(head));
//            if (head.node.left != null) {
//                queue.add(new IndexableTreeNode(head.node.left, BigInteger.ONE.add(head.index.add(head.index))));
//            }
//            if (head.node.right != null) {
//                queue.add(new IndexableTreeNode(head.node.right, two.add(head.index.add(head.index))));
//            }
//        }
//
//        return sb.toString();
//    }
//
//    private String serializeNodeValueWithIndex(IndexableTreeNode node) {
//        return node.index + INDEX_VALUE_DELIMITER + node.node.val + DELIMITER;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data == null) {
//            return null;
//        }
//        String tokens[] = data.split(DELIMITER);
//        Map<BigInteger, TreeNode> reference = new HashMap<>();
//        for (String t : tokens) {
//            String[] kv = t.split(INDEX_VALUE_DELIMITER);
//            BigInteger index = new BigInteger(kv[0]);
//            int val = Integer.parseInt(kv[1]);
//            reference.put(index, new TreeNode(val));
//        }
//
//        for (String t : tokens) {
//            String[] kv = t.split(INDEX_VALUE_DELIMITER);
//            BigInteger index = new BigInteger(kv[0]);
//
//            TreeNode current = reference.get(index);
//            BigInteger left = (index.add(index).add(BigInteger.ONE));
//            if (reference.containsKey(left)) {
//                current.left = reference.get(left);
//            }
//            BigInteger right = (index.add(index).add(BigInteger.valueOf(2)));
//            if (reference.containsKey(right)) {
//                current.right = reference.get(right);
//            }
//        }
//
//        return reference.get(BigInteger.ZERO);
//    }
//
//    static class IndexableTreeNode {
//        TreeNode node;
//        BigInteger index;
//        IndexableTreeNode(TreeNode n, BigInteger i) {
//            node = n;
//            index = i;
//        }
//    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    private static void test1() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.left.right = new TreeNode(12);
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test2() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(0);
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test3() {
        Codec codec = new Codec();
        TreeNode root = null;
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test4() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(14);
        root.right.right.right.right = new TreeNode(30);
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test5() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test6() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        root.right.right.right.right.right = new TreeNode(6);
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }

    private static void test7() {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(0);
        TreeNode temp = root;
        int i=1;
        while (i<1000) {
            temp.right = new TreeNode(i);
            temp = temp.right;
            i++;
        }
        String intermediate = codec.serialize(root);
        System.out.println(intermediate);
        TreeNode result = codec.deserialize(intermediate);
        System.out.println(result);
    }
}
