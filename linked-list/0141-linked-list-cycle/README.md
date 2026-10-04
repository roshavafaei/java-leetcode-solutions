LeetCode 141 - Linked List Cycle
Problem
Given the head of a linked list, determine whether the linked list contains a cycle.
A cycle exists when a node can be reached again by continuously following the next pointer.
Example:
3 → 2 → 0 → -4
    ↑         |
    └─────────┘
Here, the last node points back to a previous node, so the linked list contains a cycle.
Approach - Fast & Slow Pointers
We use two pointers:
slow moves one node at a time.
fast moves two nodes at a time.
slow = slow.next;
fast = fast.next.next;
If the linked list contains a cycle, the faster pointer will eventually catch the slower pointer.
When:
fast == slow
both pointers are pointing to the exact same node, which means a cycle exists.
If there is no cycle, fast will eventually reach the end of the linked list (null).
Why Check fast and fast.next?
Before doing:
fast = fast.next.next;
we must make sure both fast and fast.next exist.
Therefore:
while (fast != null && fast.next != null)
If either one is null, we have reached the end of the linked list, so there is no cycle.
Solution
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow)
            return true;
    }

    return false;
}
Example
Consider:
3 → 2 → 0 → -4
    ↑         |
    └─────────┘
Initially:
slow = 3
fast = 3
After each iteration:
slow: 3 → 2 → 0 → -4 → 2 → ...
fast: 3 → 0 → 2 → -4 → 0 → ...
Since the list contains a cycle, fast eventually catches slow.
At that point:
fast == slow
and we return:
true
Complexity
Time Complexity
O(n)
The pointers traverse the linked list until they either meet or fast reaches the end.
Space Complexity
O(1)
We only use two pointers and no additional data structure.
Key Idea
This algorithm is called Floyd's Cycle Detection Algorithm (also known as the Tortoise and Hare Algorithm).
The main idea is:
If two pointers move at different speeds inside a cycle, the faster pointer will eventually catch the slower pointer.
