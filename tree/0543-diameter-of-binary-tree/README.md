543. Diameter of Binary Tree
Problem
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes.
The path does not necessarily have to pass through the root.
The length of the path is measured by the number of edges, not the number of nodes.
Example
Consider this tree:
        1
       / \
      2   3
     / \
    4   5
One of the longest paths is:
4 → 2 → 1 → 3
Another longest path is:
5 → 2 → 1 → 3
Each path contains 3 edges.
Therefore:
Diameter = 3
Important Idea
For every node, we calculate:
height of left subtree
+
height of right subtree
This gives us the longest path that passes through that node.
For example:
        1
       / \
      2   3
     / \
    4   5
At node 1:
left height  = 2
right height = 1

diameter through node 1 = 2 + 1 = 3
So the important idea is:
diameter through a node = leftHeight + rightHeight
Why Do We Check Every Node?
The longest path does not necessarily pass through the root.
For example:
          1
         /
        2
       / \
      3   4
     /     \
    5       6
A long path can be:
5 → 3 → 2 → 4 → 6
This path does not pass through node 1.
Therefore, we cannot calculate the diameter only at the root.
We have to calculate:
leftHeight + rightHeight
for every node and keep the largest value we have seen.
Solution
class Solution {

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {

        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        diameter = Math.max(
            diameter,
            leftHeight + rightHeight
        );

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
Understanding the Recursion
Consider again:
        1
       / \
      2   3
     / \
    4   5
We start with:
height(1)
But before we can calculate the height of 1, we need the heights of its left and right subtrees.
So recursion goes down the tree.
Eventually we reach node 4.
Node 4
4
Both children are null.
height(null) = 0
height(null) = 0
Therefore:
leftHeight = 0
rightHeight = 0
Diameter through node 4:
0 + 0 = 0
Height of node 4:
1 + max(0, 0)
= 1
So:
height(4) = 1
The 1 represents node 4 itself.
Node 5
The same thing happens:
height(5) = 1
Diameter through node 5:
0 + 0 = 0
Node 2
Now recursion returns to node 2.
We already know:
leftHeight = height(4) = 1
rightHeight = height(5) = 1
So the diameter through node 2 is:
1 + 1 = 2
The path is:
4 → 2 → 5
There are 2 edges.
Now we update:
diameter = 2
Height of node 2:
1 + max(1, 1)
= 2
So:
height(2) = 2
Node 3
Node 3 has no children.
Therefore:
height(3) = 1
and:
diameter through 3 = 0
Node 1
Finally we return to node 1.
We have:
leftHeight = height(2) = 2
rightHeight = height(3) = 1
Diameter through node 1:
2 + 1 = 3
So:
diameter = max(2, 3)
         = 3
Height of node 1:
1 + max(2, 1)
= 3
The final answer is:
3
Height vs Diameter
This is the most important distinction in this problem.
Height
Height asks:
What is the longest downward path from this node?
Therefore:
return 1 + Math.max(leftHeight, rightHeight);
We choose only one side because a downward path can continue either left or right.
Diameter
Diameter asks:
What is the longest path connecting two nodes through this node?
Therefore:
leftHeight + rightHeight
We use both sides.
So remember:
HEIGHT
1 + max(left, right)

DIAMETER
left + right
Why Is There No -1?
It may look like we should calculate:
leftHeight + rightHeight - 1
because height counts nodes.
But notice how the heights are being used.
For this tree:
    2
   / \
  4   5
We get:
leftHeight = 1
rightHeight = 1
The diameter is:
1 + 1 = 2
And the actual path is:
4 → 2 → 5
which contains exactly 2 edges.
So:
diameter = leftHeight + rightHeight
already gives us the number of edges.
No subtraction is necessary.
Why Do We Return Height Instead of Diameter?
This line:
return 1 + Math.max(leftHeight, rightHeight);
returns the height because the parent needs the height of its children.
But while calculating that height, we also calculate:
leftHeight + rightHeight
and use it to update the global diameter:
diameter = Math.max(diameter, leftHeight + rightHeight);
So the recursive method has two jobs:
1. Calculate the height for the parent.

2. Update the maximum diameter.
Time Complexity
O(n)
Every node is visited once.
If the tree contains n nodes, we process all n nodes.
Space Complexity
O(h)
where h is the height of the tree.
This space is used by the recursion call stack.
For a balanced tree:
O(log n)
For a completely skewed tree:
O(n)
Key Takeaway
For each node:
leftHeight = height(left)
rightHeight = height(right)
Use both sides to check the diameter:
leftHeight + rightHeight
Use the larger side to return the height:
1 + max(leftHeight, rightHeight)
So the main formulas are:
Diameter through current node:

leftHeight + rightHeight


Height of current node:

1 + max(leftHeight, rightHeight)
The maximum leftHeight + rightHeight found anywhere in the tree is the final diameter.
