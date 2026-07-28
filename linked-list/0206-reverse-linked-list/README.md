# 206. Reverse Linked List

## Problem

Given the head of a singly linked list, reverse the list and return the new head.

### Example
Input:
1 -> 2 -> 3 -> 4 -> 5

Output:
5 -> 4 -> 3 -> 2 -> 1

---

## My Initial Idea

The main challenge was that every node only knows the address of the next node.

For example:
1 -> 2 -> 3 -> null

If I directly change:
current.next = previous;

I lose access to the rest of the list unless I first save the original next node.

That is why I used three references:

- current points to the node currently being processed.
- previous points to the already reversed part of the list.
- next temporarily saves the next node before changing any connection.

---

## Key Idea

For every node, I perform four steps:
ListNode next = current.next;
current.next = previous;
previous = current;
current = next;

Each line has a specific responsibility.

### 1. Save the next node
ListNode next = current.next;

Before reversing the connection, I save the next node.

Without this line, the rest of the linked list could be lost.

### 2. Reverse the current connection
current.next = previous;

The current node now points backward instead of forward.

### 3. Move previous
previous = current;

The reversed section grows by one node.

### 4. Move current
current = next;

The algorithm continues with the next unreversed node.

---

## Step-by-Step Example

Suppose the list is:
1 -> 2 -> 3 -> null

Initially:
previous = null
current = 1

### First iteration

Save the next node:
next = 2

Reverse the connection:
1 -> null

Move the references:
previous = 1
current = 2

Current state:
null <- 1    2 -> 3 -> null

### Second iteration

Save the next node:
next = 3

Reverse the connection:
2 -> 1 -> null

Move the references:
previous = 2
current = 3

Current state:
null <- 1 <- 2    3 -> null

### Third iteration

Save the next node:
next = null

Reverse the connection:
3 -> 2 -> 1 -> null

Move the references:
previous = 3
current = null

The loop ends because current is now null.

The new head is:
previous

Final list:
3 -> 2 -> 1 -> null

---

## Approach

1. Set current to head.
2. Set previous to null.
3. While current is not null:
   - Save current.next.
   - Reverse the next pointer.
   - Move previous forward.
   - Move current forward.
4. Set head to previous.
5. Return the new head.

---

## Solution
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode previous = null;

        if (head == null)
            return null;

        if (current.next == null)
            return head;

        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head = previous;
        return head;
    }
}

---

## Why next Is Necessary

This line is essential:
ListNode next = current.next;

Suppose the list is:
1 -> 2 -> 3

If I immediately write:
current.next = previous;

the connection from 1 to 2 disappears.

Without saving 2 first, I would no longer know how to reach the rest of the list.

So next protects the unreversed section of the linked list.

---

## Edge Cases

### Empty list
head = null

The method returns:
null

### One-node list
1 -> null

The list is already reversed, so the method returns the same node.

---

## Complexity

### Time Complexity
O(n)

Every node is visited once.

### Space Complexity
O(1)

Only a few references are used, regardless of the list size.

---

## What I Learned

- How to reverse links instead of moving node values.
- Why the next node must be saved before changing a connection.
- How previous, current, and next work together.
- How the reversed section grows one node at a time.
- Why previous becomes the new head at the end.
- How to modify a linked list in-place.
- How to solve the problem with constant extra space.
