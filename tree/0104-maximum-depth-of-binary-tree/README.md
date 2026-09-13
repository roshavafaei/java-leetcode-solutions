LeetCode 104 - Maximum Depth of Binary Tree
Problem
Given the root of a binary tree, return its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Example
        3
       / \
      9   20
         /  \
        15   7
The longest path can be:
3 → 20 → 15
or:
3 → 20 → 7
Both paths contain 3 nodes.
So the maximum depth is:
3
Approach - Recursion
For every node, I calculate:
1 + max(left subtree depth, right subtree depth)
The 1 represents the current node itself.
So:
return 1 + Math.max(leftDepth, rightDepth);
The base case is:
if (root == null)
    return 0;
A null node contributes 0 to the depth.
Solution
public int maxDepth(TreeNode root) {

    if (root == null)
        return 0;

    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return 1 + Math.max(leftDepth, rightDepth);
}
Understanding the Recursion
Consider this tree:
    3
   /
  9
First:
maxDepth(3)
calls:
maxDepth(9)
For node 9:
left = null
right = null
So:
leftDepth = 0
rightDepth = 0
Then:
maxDepth(9)
= 1 + max(0, 0)
= 1
The result 1 means the subtree rooted at 9 contains one node in its longest path.
Now we return to node 3.
For node 3:
leftDepth = 1
rightDepth = 0
So:
maxDepth(3)
= 1 + max(1, 0)
= 2
The longest path is:
3 → 9
which contains 2 nodes.
Why Does a Leaf Have Depth 1?
This problem defines maximum depth using the number of nodes in the path.
For a single leaf node:
9
its children are both null:
maxDepth(null) = 0
So:
maxDepth(9)
= 1 + max(0, 0)
= 1
The 1 represents the leaf node itself.
Node Count vs Edge Count
This was an important point for me.
Depth and height can sometimes be defined using either nodes or edges, depending on the problem.
A common edge-based definition looks like this:
        3      depth = 0
       /
      9        depth = 1
     /
    5          depth = 2
Here, depth is the number of edges from the root.
However, LeetCode 104 defines maximum depth as the number of nodes along the longest path.
So for:
        3
       /
      9
     /
    5
the answer is:
3
because the path contains:
3 → 9 → 5
which has 3 nodes.
Key Idea
The recursive formula is:
depth(node)
=
1 + max(
    depth(node.left),
    depth(node.right)
)
And the base case is:
depth(null) = 0
So recursively:
null → 0
leaf → 1
parent of leaf → 2
...
Complexity
Time Complexity
O(n)
Every node is visited once.
Space Complexity
O(h)
The recursive call stack depends on the height of the tree.
For a balanced tree:
O(log n)
For a completely skewed tree:
O(n)
What I Learned
How to calculate the maximum depth of a binary tree using recursion.
How recursive calls return subtree depths.
Why the current node contributes +1.
Why null returns 0.
Why a leaf returns 1 in this problem.
The difference between counting depth using nodes and counting it using edges.
Why it is important to read the exact definition of depth or height in each problem.
Pattern
Tree / DFS / Recursion
The main idea is:
current node
    ↓
find left depth
    ↓
find right depth
    ↓
take the larger one
    ↓
add 1 for the current node
