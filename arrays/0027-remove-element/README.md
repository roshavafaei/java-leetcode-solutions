# 27. Remove Element

## Problem

Given an integer array nums and an integer val, remove all occurrences of val in-place.

The order of the remaining elements may be changed.

Return the number of elements that are not equal to val.

The first k positions of the array must contain the remaining valid elements.

The values after index k - 1 do not matter.

---

## My Initial Understanding

This problem looked similar to Remove Duplicates from Sorted Array because both problems require modifying the original array in-place.

At first, I had to understand that I did not need to physically delete elements or resize the array.

Instead, I only needed to copy the valid values to the beginning of the same array.

For example:
nums = [3, 2, 2, 3]
val = 3

A valid final array can be:
[2, 2, 2, 3]

The returned value is:
2

Only the first two positions matter:
[2, 2]

---

## Key Idea

I used the Two Pointers technique.

In this problem, the pointers are integer indexes:

- fast scans every element in the array.
- slow points to the next position where a valid value should be written.

A value is valid when:
nums[fast] != val

When a valid value is found:
nums[slow] = nums[fast];
slow++;

The value is copied into the next valid position, and slow moves forward.

If the current value is equal to val, nothing is written and slow does not move.

---

## Important Difference from Remove Duplicates

In Remove Duplicates from Sorted Array:
slow = index of the last unique value

So the answer was:
slow + 1

In Remove Element:
slow = number of valid values

It also represents the next position where a valid value should be written.

So the answer is:
slow

---

## Step-by-Step Example
nums = [2, 3, 4, 2, 2]
val = 3

Initially:
slow = 0
fast = 0

[2, 3, 4, 2, 2]
 ↑
slow
fast

Since:
2 != 3

the value is valid.
nums[slow] = nums[fast];
slow++;

The first 2 stays at index 0.

Now:
slow = 1
fast = 1

The current value is 3, which is equal to val.

Nothing is copied, and slow stays at index 1.

Next, fast reaches 4:
[2, 3, 4, 2, 2]
    ↑  ↑
  slow fast

Since:
4 != 3

the value is copied:
nums[1] = nums[2];

The array becomes:
[2, 4, 4, 2, 2]

The value 3 was overwritten because it was the value that needed to be removed.

The process continues for the remaining 2 values.

Final array:
[2, 4, 2, 2, 2]

The valid section is:
[2, 4, 2, 2]

The returned value is:
4

---

## Approach

1. Initialize slow at index 0.
2. Start fast at index 0.
3. Move fast through the entire array.
4. If nums[fast] is not equal to val:
   - Copy it into nums[slow].
   - Increment slow.
5. Return slow.

---

## Solution
class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }
}

---

## Complexity

### Time Complexity
O(n)

The fast pointer visits every element once.

### Space Complexity
O(1)

No additional array is created.

The original array is modified in-place.

---

## What I Learned

- How to use two moving indexes on an array.
- How to modify an array in-place without deleting elements.
- How to overwrite values that are no longer needed.
- Why fast must start at index 0.
- Why slow only moves when a valid value is found.
- Why slow represents both the next write position and the number of valid elements.
- Why the values after the returned length do not matter.
- The difference between returning slow and returning slow + 1.
