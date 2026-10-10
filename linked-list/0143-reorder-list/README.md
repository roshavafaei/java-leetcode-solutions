LeetCode 143 – Reorder List
Difficulty: Medium
Topic: Linked List, Two Pointers
Problem Link: https://leetcode.com/problems/reorder-list/
Problem Description
Given the head of a singly linked list:
L0 → L1 → L2 → ... → Ln
Reorder the list into the following pattern:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
We must modify the connections between nodes without changing their values.
Example
Input:
1 → 2 → 3 → 4 → 5
Output:
1 → 5 → 2 → 4 → 3
Approach – Two Pointers + Reverse + Merge
We can solve this problem in four steps.
Step 1: Find the Middle
Use two pointers:
• slow moves one node at a time.
• fast moves two nodes at a time.
When fast reaches the end, slow points to the middle node.
Step 2: Split the Linked List
Save the beginning of the second half and disconnect the two halves.
ListNode secondHalfHead = slow.next;
slow.next = null;
For example:
First half:  1 → 2 → 3
Second half: 4 → 5
Step 3: Reverse the Second Half
Reverse the second half using three pointers:
• previous – the previous node
• current – the current node
• nextNode – temporarily stores the next node
After reversing:
First half:  1 → 2 → 3
Second half: 5 → 4
At the end of the reverse operation, previous points to the new head of the second half.
Step 4: Merge the Two Halves
Merge the two lists by alternating nodes from each half.
Before changing any connections, save the next node of each half to avoid losing references.
First half:  1 → 2 → 3
Second half: 5 → 4

Result:      1 → 5 → 2 → 4 → 3
Java Solution
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        // Step 1: Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list
        ListNode secondHalfHead = slow.next;
        slow.next = null;

        // Step 3: Reverse the second half
        ListNode previous = null;
        ListNode current = secondHalfHead;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        // Step 4: Merge both halves
        ListNode firstHalfCurrent = head;
        ListNode secondHalfCurrent = previous;

        while (secondHalfCurrent != null) {
            ListNode firstHalfNext = firstHalfCurrent.next;
            ListNode secondHalfNext = secondHalfCurrent.next;

            firstHalfCurrent.next = secondHalfCurrent;
            secondHalfCurrent.next = firstHalfNext;

            firstHalfCurrent = firstHalfNext;
            secondHalfCurrent = secondHalfNext;
        }
    }
}
Complexity Analysis
Time Complexity: O(n)
• Finding the middle takes O(n).
• Reversing the second half takes O(n).
• Merging both halves takes O(n).
Overall time complexity is O(n).
Space Complexity: O(1)
We only use a constant number of pointers and do not create any additional data structures.
Key Takeaways
• The slow and fast pointer technique helps find the middle of a linked list.
• Reversing a linked list requires carefully updating next references.
• Always save the next node before changing links.
• After reversing, previous becomes the new head of the reversed list.
• Two linked lists can be merged in-place without extra memory.
