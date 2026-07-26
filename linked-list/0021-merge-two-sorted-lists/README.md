# LeetCode #21 - Merge Two Sorted Lists

## Problem

Given the heads of two sorted linked lists, merge them into one sorted linked list and return the head of the merged list.

The merged list should be created by connecting the existing nodes from the two input lists.

---

## My Initial Idea

At first, I was unsure how to find the head because, in my own Linked List class, I had fields such as first, last, or head.

I then realized that LeetCode directly provides the heads of the two lists through the parameters list1 and list2.

---

## Approach

I used two references for the merged list:

- head stores the first node of the merged list.
- tail stores the last node currently connected to the merged list.

First, I compare the first nodes of both lists to determine the initial head and tail.

Then, while both lists still contain nodes:

1. Compare the current values of list1 and list2.
2. Connect the smaller node to tail.next.
3. Move tail to the selected node.
4. Move forward in the list from which the node was selected.

When one list becomes empty, the remaining part of the other list is attached directly to tail.next.

---

## Final Solution
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;

        if (list2 == null)
            return list1;

        ListNode head;
        ListNode tail;

        if (list1.val <= list2.val) {
            head = list1;
            tail = list1;
            list1 = list1.next;
        } else {
            head = list2;
            tail = list2;
            list2 = list2.next;
        }

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }

        if (list1 != null)
            tail.next = list1;
        else
            tail.next = list2;

        return head;
    }
}

---

## Complexity

| Complexity | Value |
|------------|-------|
| Time | O(n + m) |
| Space | O(1) |

---

## What I Learned

- In LeetCode, list1 and list2 are already the heads of the input lists.
- head should continue pointing to the first node of the merged list.
- tail moves forward as nodes are connected.
- Only the pointer belonging to the selected list should move forward.
- Once one list ends, the remainder of the other sorted list can be attached directly.
- Existing nodes can be reused without creating a new node for every value.
