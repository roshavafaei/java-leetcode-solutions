LeetCode 226 - Invert Binary Tree
Problem
Given the root of a binary tree, invert the tree and return its root.
Inverting a binary tree means swapping the left and right child of every node.
Example
Input
        4
      /   \
     2     7
    / \   / \
   1   3 6   9
Output
        4
      /   \
     7     2
    / \   / \
   9   6 3   1
Every node has its left and right children swapped.
Approach - Recursion
I solved this problem using recursion.
For every node:
If the node is null, return null.
Swap the left and right children.
Recursively invert the left subtree.
Recursively invert the right subtree.
Return the current root.
Solution
public TreeNode invertTree(TreeNode root) {

    if (root == null)
        return null;

    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
}
Why No Separate Check for Left or Right Being Null?
At first, I thought I needed separate conditions for cases where:
root.left == null
or:
root.right == null
But the swap already handles those cases correctly.
For example:
left = 2
right = null
After:
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
we get:
left = null
right = 2
So no extra condition is needed.
Example Walkthrough
Consider:
      4
     / \
    2   7
First call:
invertTree(4)
Swap:
left = 2
right = 7
becomes:
left = 7
right = 2
Now the tree is:
      4
     / \
    7   2
Then recursion continues:
invertTree(root.left);
invertTree(root.right);
which means:
invertTree(7)
invertTree(2)
Each subtree is inverted in exactly the same way.
Base Case
The base case is:
if (root == null)
    return null;
This stops the recursion when there is no node left to process.
Without this base case, recursion would continue trying to access children of null.
Understanding the Recursion
Each recursive call handles one node.
The current call pauses while the recursive call works on the subtree.
For example:
invertTree(4)
    |
    |-- invertTree(7)
    |      |
    |      |-- invertTree(...)
    |
    |-- invertTree(2)
           |
           |-- invertTree(...)
After each subtree is finished, execution returns back to the previous call.
Complexity
Time Complexity
O(n)
Every node is visited once.
Space Complexity
O(h)
The recursive call stack depends on the height of the tree.
Where:
h = height of the tree
In the worst case, for a completely unbalanced tree:
O(n)
What I Learned
How to invert a binary tree using recursion.
How swapping left and right children changes the tree structure.
Why separate checks for left == null or right == null are unnecessary.
How the base case stops recursion.
How recursive calls process each subtree independently.
How to modify a tree in place without creating a new tree.
Pattern
Tree / Recursion
The main idea is:
current node
    ↓
swap left and right
    ↓
invert left subtree
    ↓
invert right subtree
    ↓
return root
