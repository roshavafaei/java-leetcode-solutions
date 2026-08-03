# 148. Sort List

## Problem

Given the head of a singly linked list, sort the list in ascending order and return the new head.

### Example
Input:
4 → 2 → 1 → 3

Output:
1 → 2 → 3 → 4

---

## My Initial Idea

The first challenge was deciding how to sort a linked list efficiently.

A linked list does not provide direct access by index like an array, so algorithms that depend heavily on random access are not a natural fit.

I decided to use Merge Sort because it works very well with linked lists.

The main idea is:
1. Split the list into two halves.
2. Sort each half recursively.
3. Merge the two sorted halves.

---

## Why Merge Sort?

Merge Sort is a good choice for linked lists because:

- The list can be split using pointers.
- Two sorted linked lists can be merged by changing links.
- No array conversion is required.
- The time complexity is O(n log n).

---

## Finding the Middle

To split the list, I used three pointers:
ListNode slow = head;
ListNode fast = head;
ListNode prev = null;

Their roles are:
slow → moves one node at a time
fast → moves two nodes at a time
prev → stays one node behind slow

When fast reaches the end, slow is at the beginning of the second half.

For example:
4 → 2 → 1 → 3
        ↑
       slow
    ↑
   prev

Then the connection is removed with:
prev.next = null;

The list becomes:
4 → 2

1 → 3

Now there are two completely separate linked lists.

---

## The Base Case

At first, it was important to understand when recursion should stop.

A list with zero or one node is already sorted.
if (head == null || head.next == null) {
    return head;
}

Without this base case, the method would continue calling itself indefinitely.

---

## Sorting Each Half

After splitting the list, both halves still may be unsorted.

For example:
4 → 2

is not sorted yet.

So each half must be passed back into the same method:
ListNode left = sortList(head);
ListNode right = sortList(slow);

This is the recursive part of Merge Sort.

For example:
4 → 2 → 1 → 3

is divided into:
4 → 2
1 → 3

Then each half is divided again:
4    2    1    3

Single-node lists are already sorted.

Then they are merged back:
2 → 4
1 → 3

Finally:
1 → 2 → 3 → 4

---

## An Important Mistake

After creating:
ListNode left = sortList(head);
ListNode right = sortList(slow);

the merge must use left and right.

These variables contain the sorted versions of the two halves.

Using the old head and slow pointers would ignore the results returned by recursion.
head and slow → original unsorted halves
left and right → recursively sorted halves

---

## Merging the Sorted Halves

The merge process uses two pointers:
first → head of the merged list
last  → final node of the merged list

First, the smaller starting node is selected:
if (left.val <= right.val) {
    first = left;
    last = left;
    left = left.next;
} else {
    first = right;
    last = right;
    right = right.next;
}

Then the smaller node is repeatedly attached to the result:
while (left != null && right != null) {
    if (left.val <= right.val) {
        last.next = left;
        last = left;
        left = left.next;
    } else {
        last.next = right;
        last = right;
        right = right.next;
    }
}

Each selected pointer must move forward.

Otherwise, it stays on the same node and the loop may never finish.

---

## Time Limit Exceeded Mistake

In the first version of the merge logic, after selecting right, the wrong pointer was moved forward.

Instead of:
right = right.next;

another pointer was updated.

As a result, right stayed on the same node.

This caused the merge loop to repeatedly process the same node and led to:
Time Limit Exceeded

The correct rule is:
If a node is taken from left, move left.
If a node is taken from right, move right.

---

## Connecting the Remaining Nodes

The main merge loop stops when either left or right becomes null.

At that point, the other list may still contain nodes.

Since that remaining part is already sorted, it can be connected directly:
if (left != null) {
    last.next = left;
} else {
    last.next = right;
}

---

## Solution
class Solution {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        ListNode first;
        ListNode last;

        if (left.val <= right.val) {
            first = left;
            last = left;
            left = left.next;
        } else {
            first = right;
            last = right;
            right = right.next;
        }

        while (left != null && right != null) {

            if (left.val <= right.val) {
                last.next = left;
                last = left;
                left = left.next;
            } else {
                last.next = right;
                last = right;
                right = right.next;
            }
        }

        if (left != null) {
            last.next = left;
        } else {
            last.next = right;
        }

        return first;
    }
}

---

## Complexity

### Time Complexity
O(n log n)

The list is repeatedly divided into halves, and every recursion level processes all nodes during merging.

### Space Complexity
O(log n)

The algorithm changes the existing links instead of creating another list, but recursion uses call-stack space.

---

## What I Learned

- Merge Sort is a natural sorting algorithm for linked lists.
- Slow and fast pointers can find the middle of a list.
- A third pointer can preserve the node before the middle.
- Setting prev.next = null separates the list into two halves.
- A recursive algorithm must have a clear base case.
- Each half must be sorted before merging.
- The merge must use the sorted left and right results.
- After selecting a node, its pointer must move forward.
- Updating the wrong pointer can create an infinite loop.
- The remaining nodes can be connected directly after one list ends.
