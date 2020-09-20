# Find Bottom Left Tree Value

This is the solution to the [leetcode problem "Find Bottom Left Tree Value"](https://leetcode.com/problems/find-bottom-left-tree-value/).

The problem is as follows:

```
Given a binary tree, find the leftmost value in the last row of the tree:

Example 1:
Input:

      2
     / \
    1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.

```

# Solution

## Approach

In order to solve this problem, we should first breakdown the problem. We need to figure out a way to 
traverse to the bottom most row, and also find the left-most node's value within the last row. *(Note: we are **not** finding 
the bottom-most node left subtree)*. There are several ways to traverse a tree; however, how do we know when we've hit 
the last row? Furthermore, how do we also find the left-most element within that row?

We know that there's no direct way of accessing the last row of a tree; however, we also know that you can **find all nodes'
level/depth in a tree in linear time**. The last row of a tree just means find the nodes with the highest depth count. 
Then, we can aim to use a traversal order that allows us to hit the left-most element first. This should allow us to
then find the left-most bottom node. 

In order to traverse a binary tree, you have the option of pre-order/DFS, in-order, post-order, or BFS (level-traversal). All of these
should work, because they will all touch every node in the tree. For simplicity, we can pick DFS (or pre-order), because 
this allows us to hit the left-most node first within a level. So then solution can be summarized as: given a node, see
if it is the (first-occurring) highest depth count. If it is, then we assume this is the left-most value within that depth/level.
After traversing every node in the tree, we can return the node that we set at highest depth count. 

Please see attached image for example. 

![Implementation Visualization](https://github.com/davidtian02/leetcode-practice/blob/28de70e3aa47c379a45f4c095533057e8cc27ac1/src/com/leetcode/problems/medium/find_bottom_left_tree_value/dfs_diagram.jpg?raw=true)

## Test Cases

It is often helpful to first come up with your own test cases in order to understand the problem better *(Tip: often times 
if you are stuck on finding an optimal solution, you can just look for patterns in the answers)* as well as to take
care of the edge cases. Since the problem states root will not be given as null, we can eliminate that possibility already.

However, we can think of different cases for testing:
*  Only given 1 node
*  Multiple nodes in the last row, with different parents
*  Every parent only has left child. (Linked List)
*  Every parent only has right child. (Linked List)

After coming up with several scenarios, we can verify that our approach should work for every single case thought of.

## Implementation (Java)

```
class Solution {
    private int depth = 0;
    private int leftmostValue = -1;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return leftmostValue;
    }

    private void dfs(TreeNode root, int level) {
        if (level > depth) { // if we have a higher depth, we store the value because in DFS, we hit the leftmost value FIRST.
            leftmostValue = root.val;
            depth = level;
        }

        // children are at one level beyond the parent's level, so the children's depth is just the parent/current level + 1
        if (root.left != null) {
            dfs(root.left, level + 1); 
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
    }
}
```

## Complexity Analysis

*  Time complexity : ***O(N)*** where `N` is the number of nodes in the tree.
    * We traverse every element of the tree once, so this is a linear operation.  
*  Space complexity :***O(H)*** where `H` is the maximum height of the tree.
    * Because we use recursion in DFS here (alternatively, you could use a stack), we have to account for the amount of space that the recursion stack uses.