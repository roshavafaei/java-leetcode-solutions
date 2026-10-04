LeetCode 102 - Binary Tree Level Order Traversal
Problem
Given the root of a binary tree, return the values of its nodes level by level, from left to right.
Example:
        3
       / \
      9   20
         /  \
        15   7
Output:
[
  [3],
  [9, 20],
  [15, 7]
]
Each inner list represents one level of the tree.
Approach - BFS with Queue
To traverse the tree level by level, we use Breadth-First Search (BFS).
A Queue is useful because it processes nodes in FIFO order:
First In → First Out
We store the actual TreeNode objects in the queue, not just their values, because we need access to their left and right children.
Queue<TreeNode> queue = new ArrayDeque<>();
queue.add(root);
Why Do We Use levelSize?
At the beginning of each level, we save the current size of the queue:
int levelSize = queue.size();
This tells us exactly how many nodes belong to the current level.
For example:
Queue = [9, 20]

levelSize = 2
So we process exactly two nodes.
While processing them, their children may be added to the queue, but those children belong to the next level.
Step-by-Step Example
Consider:
        3
       / \
      9   20
         / \
        15  7
Level 1
Initially:
Queue = [3]
levelSize = 1
Remove 3:
currentLevel = [3]
Add its children:
Queue = [9, 20]
Result:
[[3]]
Level 2
Now:
Queue = [9, 20]
levelSize = 2
Process 9 and 20:
currentLevel = [9, 20]
9 has no children.
20 has two children, so add 15 and 7:
Queue = [15, 7]
Result:
[
  [3],
  [9, 20]
]
Level 3
Now:
Queue = [15, 7]
levelSize = 2
Process both:
currentLevel = [15, 7]
Neither has children.
Queue becomes empty.
Final result:
[
  [3],
  [9, 20],
  [15, 7]
]
Solution
public List<List<Integer>> levelOrder(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();

    if (root == null)
        return result;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    while (!queue.isEmpty()) {

        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {

            TreeNode current = queue.remove();

            currentLevel.add(current.val);

            if (current.left != null)
                queue.add(current.left);

            if (current.right != null)
                queue.add(current.right);
        }

        result.add(currentLevel);
    }

    return result;
}
Important Idea
We do not recursively call:
levelOrder(root.left);
levelOrder(root.right);
That would be thinking in terms of DFS.
Instead, BFS uses the queue to keep track of which nodes should be visited next:
        3
       / \
      9   20
         / \
        15  7

Queue:

[3]
 ↓
[9, 20]
 ↓
[15, 7]
 ↓
[]
The queue naturally moves through the tree one level at a time.
Complexity
Time Complexity
O(n)
Every node is visited exactly once.
Space Complexity
O(n)
In the worst case, the queue may contain many nodes from one level of the tree.
Key Takeaway
For level-order traversal, think:
BFS → Queue
The key pattern is:
while (!queue.isEmpty()) {

    int levelSize = queue.size();

    for (int i = 0; i < levelSize; i++) {
        // process nodes of the current level
        // add their children to the queue
    }
}
levelSize separates the nodes of the current level from the nodes of the next level.
