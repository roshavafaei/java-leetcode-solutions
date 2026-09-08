LeetCode 94 - Binary Tree Inorder Traversal
Problem
Given the root of a binary tree, return the inorder traversal of its nodes' values.
Inorder traversal follows this order:
Left → Root → Right
Approach - Recursion
I solved this problem using recursion.
For every node:
Traverse the left subtree.
Add the current node's value.
Traverse the right subtree.
Combine the results and return them.
The base case happens when root == null.
In that case, there is nothing to traverse, so I return an empty list.
Solution
public List<Integer> inorderTraversal(TreeNode root) {

    if (root == null)
        return new ArrayList<>();

    List<Integer> leftResult = inorderTraversal(root.left);
    List<Integer> rightResult = inorderTraversal(root.right);

    List<Integer> result = new ArrayList<>();

    result.addAll(leftResult);
    result.add(root.val);
    result.addAll(rightResult);

    return result;
}
Example
For this tree:
        10
       /  \
      5    15
     / \
    2   7
The inorder traversal is:
Left → Root → Right
The recursive calls eventually return:
inorder(2)  → [2]
inorder(7)  → [7]
inorder(5)  → [2, 5, 7]
inorder(15) → [15]
inorder(10) → [2, 5, 7, 10, 15]
Final result:
[2, 5, 7, 10, 15]
Understanding the Recursion
One of the most important things I learned from this problem is how recursive calls work.
When a method calls itself, the current method call does not disappear.
Instead, it pauses and waits for the recursive call to finish.
For example:
inorder(10)
    ↓
inorder(5)
    ↓
inorder(2)
    ↓
inorder(null)
When root == null, the base case is reached and an empty list is returned.
Then the recursive calls start returning back up:
inorder(null) → []
inorder(2)    → [2]
inorder(5)    → [2, 5, 7]
inorder(10)   → [2, 5, 7, 10, 15]
Another important point is that when a recursive call returns, the method does not start again from the beginning.
It continues from the line where the recursive call was made.
For example:
List<Integer> leftResult = inorderTraversal(root.left);

// After the left recursive call returns,
// execution continues from here.

List<Integer> rightResult = inorderTraversal(root.right);
This helped me understand the recursive call stack more clearly.
Complexity
Time Complexity: O(n)
Every node in the tree is visited once.
Space Complexity: O(h)
The recursive call stack depends on the height of the tree, where h is the height of the binary tree.
In the worst case, for a completely unbalanced tree, the space complexity can become O(n).
What I Learned
How inorder traversal follows Left → Root → Right
How to use recursion to traverse a binary tree
How the base case stops recursive calls
How recursive calls pause and return back through the call stack
How to combine the results returned from recursive calls
The difference between add() and addAll() when working with lists
