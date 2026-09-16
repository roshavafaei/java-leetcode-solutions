LeetCode 543 - Diameter of Binary Tree
Problem
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes.
The path may or may not pass through the root.
The length of the path is measured by the number of edges between the nodes.
Example
Consider this tree:
            1
           / \
          2   3
         / \
        4   5
       /
      6
The longest path is:
6 → 4 → 2 → 1 → 3
This path contains 4 edges:
6 → 4    edge 1
4 → 2    edge 2
2 → 1    edge 3
1 → 3    edge 4
Therefore:
diameter = 4
Important Idea
The diameter does not necessarily have to pass through the root of the entire tree.
For every node, we calculate:
left height + right height
This gives us the length of the longest path that passes through that specific node.
We calculate this for every node and keep the largest value we have seen.
Approach - DFS / Recursion
For every node:
Find the height of the left subtree.
Find the height of the right subtree.
Calculate the possible diameter through the current node.
Update the global maximum diameter.
Return the height of the current node to its parent.
The possible diameter through the current node is:
left + right
The height returned to the parent is:
1 + max(left, right)
These two calculations have different purposes.
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

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}
Understanding the Recursion
Consider again:
            1
           / \
          2   3
         / \
        4   5
       /
      6
The recursion first keeps going down the tree.
It starts with:
height(1)
Then:
height(2)
Then:
height(4)
Then:
height(6)
Node 6 is a leaf.
Its left and right children are both null.
Therefore:
height(null) = 0
So for node 6:
left = 0
right = 0
The possible diameter through node 6 is:
left + right
= 0 + 0
= 0
The height of node 6 is:
1 + max(0, 0)
= 1
So:
height(6) = 1
Now recursion returns to node 4.
For node 4:
left = 1
right = 0
The possible diameter through node 4 is:
1 + 0 = 1
So:
diameter = 1
The height returned by node 4 is:
1 + max(1, 0)
= 2
Therefore:
height(4) = 2
Now recursion eventually returns to node 2.
Its left subtree has height:
2
and its right subtree, which contains node 5, has height:
1
So:
left = 2
right = 1
The possible diameter through node 2 is:
left + right
= 2 + 1
= 3
The path is:
6 → 4 → 2 → 5
So the global diameter becomes:
diameter = 3
Node 2 then returns its height:
1 + max(2, 1)
= 3
Therefore:
height(2) = 3
Finally, recursion returns to node 1.
For node 1:
left = 3
right = 1
The possible diameter through node 1 is:
left + right
= 3 + 1
= 4
The path is:
6 → 4 → 2 → 1 → 3
So:
diameter = 4
This becomes the final answer.
How the Diameter Changes
During recursion, the diameter is updated like this:
Node 6:

left = 0
right = 0

left + right = 0

diameter = 0
Then:
Node 4:

left = 1
right = 0

left + right = 1

diameter = 1
Then:
Node 5:

left = 0
right = 0

left + right = 0

diameter remains 1
Then:
Node 2:

left = 2
right = 1

left + right = 3

diameter = 3
Then:
Node 3:

left = 0
right = 0

left + right = 0

diameter remains 3
Finally:
Node 1:

left = 3
right = 1

left + right = 4

diameter = 4
Final result:
4
Height vs Diameter
This was the most important part of the problem for me.
The method calculates two different things.
This line:
diameter = Math.max(diameter, left + right);
checks:
What is the longest path if it passes through the current node?
But this line:
return 1 + Math.max(left, right);
returns:
What is the height of the current node?
The parent needs the height in order to calculate its own possible diameter.
So:
Every node checks its possible diameter,
but returns its height.
This is the key idea behind the solution.
Why Do We Use left + right?
Suppose:
left = 3
right = 2
This means we can travel 3 edges down one side and 2 edges down the other side.
Therefore, the path passing through the current node contains:
3 + 2 = 5 edges
So:
left + right
directly gives us the possible diameter through the current node.
Why Do We Return 1 + max(left, right)?
A node can only return one path upward to its parent.
It cannot return both its left and right paths.
So it chooses the longer side:
Math.max(left, right)
and adds 1 for the current node:
return 1 + Math.max(left, right);
For example:
left = 3
right = 1
The height is:
1 + max(3, 1)
= 4
Recursive Flow
The recursion first goes down:
height(1)
    ↓
height(2)
    ↓
height(4)
    ↓
height(6)
    ↓
null
Then the answers start coming back up:
null → 0

6 → height 1

4 → height 2

2 → height 3

1 → height 4
While returning upward, every node also checks:
left + right
and updates the maximum diameter.
Complexity
Time Complexity
O(n)
Every node is visited once.
Space Complexity
O(h)
where h is the height of the tree because of the recursive call stack.
For a balanced tree:
O(log n)
For a completely skewed tree:
O(n)
What I Learned
How to calculate the diameter of a binary tree using recursion.
How to calculate subtree heights recursively.
Why the diameter may not pass through the root.
How to keep track of a global maximum during recursion.
Why left + right represents the possible diameter through a node.
Why the recursive method returns 1 + max(left, right).
How one recursive method can calculate one value while returning another value.
How recursion first travels down the tree and then builds the answer while returning back up.
The difference between height and diameter.
The diameter in this problem is measured using edges.
Pattern
Tree / DFS / Recursion
The main pattern is:
Go down recursively
        ↓
Get left height
        ↓
Get right height
        ↓
Check diameter through current node
        ↓
Update global maximum
        ↓
Return current height to parent
The key formulas are:
Diameter through current node:

left + right
and:
Height of current node:

1 + max(left, right)
