# LeetCode #19 - Remove Nth Node From End of List

## Problem

Given the head of a singly linked list, remove the nth node from the end of the list and return the head of the modified list.

---

## My First Idea

My initial solution used the classic fast and slow pointer technique with an additional previous pointer.

The algorithm worked correctly, but I had to handle the case where the head node needed to be removed separately.

---

## What Was Wrong?

Although the solution was correct, I noticed that handling the head as a special case made the code slightly more complicated.

I wanted to simplify the implementation while keeping the same time and space complexity.

---

## Refactoring

After reviewing my first solution, I refactored it using a dummy node.

The dummy node is placed before the head of the list, allowing every deletion—including deleting the head—to be handled in exactly the same way.

This removed the need for a separate edge-case condition and made the implementation cleaner.

---

## Final Approach

1. Create a dummy node before the head.
2. Initialize both fast and slow pointers at the dummy node.
3. Move fast forward n + 1 steps.
4. Move both pointers together until fast reaches the end.
5. slow will be positioned immediately before the node that should be removed.
6. Remove the target node by skipping it.

---

## Complexity

| Complexity | Value |
|------------|-------|
| Time | O(n) |
| Space | O(1) |

---

## What I Learned

- Fast & Slow Pointer technique
- Dummy Node pattern
- Refactoring after reaching a correct solution
- Simplifying edge cases instead of adding more conditions

---

## If I Solved It Again

I would immediately consider whether introducing a dummy node could eliminate special-case logic before writing the final implementation.
