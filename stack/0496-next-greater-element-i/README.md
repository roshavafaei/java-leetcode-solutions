# 496. Next Greater Element I

## Problem

Given two arrays, nums1 and nums2, every element of nums1 also exists in nums2.

For each element in nums1, find the first greater element that appears to its right in nums2.

If there is no greater element on the right, return -1 for that value.

### Example
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]

For each value in nums1:
4 → There is no greater value to its right in nums2 → -1
1 → The first greater value to its right is 3       → 3
2 → There is no value to its right                  → -1

Result:
[-1, 3, -1]

---

## My Initial Idea

My first idea was to solve the problem by searching through the arrays directly.

For every element in nums1, I could:

1. Find the same value inside nums2.
2. Start from the next position.
3. Search for the first value greater than it.
4. Store the result in a separate answer array.

The general idea would look like this:
For every value in nums1:
    Find its position in nums2
    Search all values to its right

This approach is simple and easy to understand, but it requires nested loops.

In the worst case, for every value in nums1, we may search through most or all of nums2.

The time complexity would be approximately:
O(nums1.length × nums2.length)

I wanted to avoid repeatedly searching through nums2, so I started thinking about whether the answers could be calculated only once and then reused.

---

## Changing the Approach

Instead of searching nums2 again for every element in nums1, I decided to process nums2 once and calculate the next greater element for all of its values.

To do that, I used:
Monotonic Stack
HashMap

The stack helps find the next greater element efficiently.

The HashMap stores the result for each value:
value → next greater value

For example:
1 → 3
3 → 4
4 → -1
2 → -1

After building this map, I can get the answer for every element in nums1 without searching through nums2 again.

---

## Why Use a Stack?

The stack stores values whose next greater element has not been found yet.

While moving through nums2, the current value is compared with the value at the top of the stack.

If the current value is greater than the stack's top value, then the current value is the next greater element for that smaller value.
while (!stack.isEmpty() && current > stack.peek()) {
    int smaller = stack.pop();
    nextGreater.put(smaller, current);
}

After resolving all smaller values from the top of the stack, the current value is pushed:
stack.push(current);

---

## Step-by-Step Example

Consider:
nums2 = [1, 3, 4, 2]

Initially:
stack = []
map = {}

### Current value: 1

The stack is empty, so 1 is pushed.
stack = [1]
map = {}

The next greater element of 1 is still unknown.

### Current value: 3

The top of the stack is 1.
3 > 1

Therefore, 3 is the next greater element of 1.
1 → 3

After removing 1 and pushing 3:
stack = [3]
map = {1=3}

### Current value: 4

The top of the stack is 3.
4 > 3

Therefore:
3 → 4

After removing 3 and pushing 4:
stack = [4]
map = {
    1=3,
    3=4
}

### Current value: 2

The top of the stack is 4.
2 > 4

This condition is false, so 2 cannot be the next greater element of 4.

The value 2 is pushed onto the stack:
stack = [4, 2]

---

## Remaining Values

After processing all elements of nums2, the stack contains:
[4, 2]

These values do not have a greater element to their right.

Therefore:
4 → -1
2 → -1

The final map becomes:
1 → 3
3 → 4
4 → -1
2 → -1

---

## Building the Answer

Now the answers for nums1 can be retrieved directly from the HashMap.
nums1 = [4, 1, 2]
4 → -1
1 → 3
2 → -1

Result:
[-1, 3, -1]

---

## Solution
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];

        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int current : nums2) {
            while (!stack.isEmpty() && current > stack.peek()) {
                int smaller = stack.pop();
                nextGreater.put(smaller, current);
            }

            stack.push(current);
        }

        while (!stack.isEmpty()) {
            nextGreater.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            answer[i] = nextGreater.get(nums1[i]);
        }

        return answer;
    }
}

---

## Why the Inner while Loop Is Not O(n²)

The code contains a while loop inside a for loop:
for (int current : nums2) {
    while (!stack.isEmpty() && current > stack.peek()) {
        stack.pop();
    }
}

At first, this may look like a regular nested loop with O(n²) complexity.

However, each element of nums2:
is pushed onto the stack once
is popped from the stack at most once

An element that has already been popped will never be processed by the while loop again.

Therefore, the total number of stack operations is linear.

This is called amortized analysis.

---

## Complexity

Let:
n = nums2.length
m = nums1.length

Processing nums2 with the monotonic stack takes:
O(n)

Building the answer for nums1 takes:
O(m)

Total time complexity:
O(n + m)

Space complexity:
O(n)

The stack and HashMap may both store information for the elements of nums2.

---

## What I Learned

- A problem with array inputs does not have to be solved using only arrays.
- A simple nested-loop solution can be correct but not optimal.
- Repeated searches can sometimes be replaced by preprocessing.
- A monotonic stack can efficiently find next greater elements.
- A HashMap can store calculated answers for fast lookup.
- A loop inside another loop does not always mean O(n²).
- Each stack element is pushed once and popped at most once.
