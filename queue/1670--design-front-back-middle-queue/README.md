# 1670. Design Front Middle Back Queue

## Problem

Design a queue that supports adding and removing elements from three positions:

- Front
- Middle
- Back

If the queue is empty, pop operations should return -1.

When there are two possible middle positions, the frontmost middle must be used.

## Approach

I used two Deque<Integer> structures:
left
right

The queue is split into two parts.

I keep them balanced using this rule:
left.size() == right.size()
or
left.size() == right.size() + 1

Because of this rule, the frontmost middle element is always at the end of left.

So:
Front  -> first element of left
Middle -> last element of left
Back   -> last element of right

If right is empty, the back element is also in left.

## Balancing

After push and pop operations, the two deques may become unbalanced.

If left becomes too large, I move its last element to the front of right.

If right becomes larger than left, I move its first element to the end of left.

This keeps the middle position predictable.

## pushMiddle

pushMiddle needs special handling.

If both sides have the same size, the new value can simply be added to the end of left.

If left already has one extra element, its last element is moved to the beginning of right, and the new value becomes the new last element of left.

This ensures that the inserted value becomes the correct frontmost middle.

## Example

For:
[1, 2, 3, 4, 5, 6]

The structure can be represented as:
left  = [1, 2, 3]
right = [4, 5, 6]

The frontmost middle is:
3

which is exactly the last element of left.

## Complexity

Because ArrayDeque supports operations at both ends efficiently:

- pushFront: O(1)
- pushMiddle: O(1)
- pushBack: O(1)
- popFront: O(1)
- popMiddle: O(1)
- popBack: O(1)

Space complexity:

- O(n)
