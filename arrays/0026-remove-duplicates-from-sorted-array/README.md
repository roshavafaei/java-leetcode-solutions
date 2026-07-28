# 26. Remove Duplicates from Sorted Array

## Problem

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place so that each unique element appears only once.

The relative order of the elements must remain the same.

Return the number of unique elements.

The first k positions of the array must contain the unique values.

---

## My Initial Confusion

At first, I thought I needed to actually delete duplicate values from the array.

Because I had previously implemented removeAt() in a dynamic array, my first idea was to shift all remaining elements to the left whenever I found a duplicate.

However, this problem does not require physically deleting values or resizing the array.

It only requires placing the unique values at the beginning of the same array.

The values after the returned length do not matter.

For example:
Input:
[1, 1, 2, 2, 3]

Valid final array:
[1, 2, 3, 2, 3]

Returned value:
3

Only the first three values are checked:
[1, 2, 3]

---

## Key Idea

I used the Two Pointers technique.

In this problem, the pointers are not real memory pointers.

They are two integer indexes:

- fast scans the array and searches for new values.
- slow points to the last unique value stored at the beginning of the array.

The array is sorted, so duplicate values are next to each other.

This means I only need to compare:
nums[fast]

with:
nums[slow]

If they are equal, the value is a duplicate and fast continues.

If they are different, a new unique value has been found.

Then:
slow++;
nums[slow] = nums[fast];

The new value is copied into the next position of the unique section.

---

## Step-by-Step Example
Input:
[1, 1, 1, 2, 2, 3, 3, 4]

Initially:
slow = 0
fast = 1

[1, 1, 1, 2, 2, 3, 3, 4]
 ↑  ↑
slow fast

The values are equal, so fast moves forward.

When fast reaches 2:
[1, 1, 1, 2, 2, 3, 3, 4]
 ↑        ↑
slow     fast

The values are different:
nums[fast] != nums[slow]

So:
slow++;
nums[slow] = nums[fast];

The array becomes:
[1, 2, 1, 2, 2, 3, 3, 4]
    ↑
   slow

The same process continues for 3 and 4.

Final array:
[1, 2, 3, 4, 2, 3, 3, 4]

The valid section is:
[1, 2, 3, 4]

The returned value is:
4

---

## Approach

1. Return 0 if the array is empty.
2. Initialize slow at index 0.
3. Start fast at index 1.
4. Move fast through the array.
5. When nums[fast] is different from nums[slow]:
   - Move slow forward.
   - Copy nums[fast] into nums[slow].
6. Return slow + 1.

slow + 1 is returned because slow is an index and array indexes start at 0.

---

## Solution
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
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

- Two pointers on an array are two moving indexes, not linked-list references.
- A sorted array makes duplicate detection easier because equal values are adjacent.
- In-place modification does not always mean deleting or shifting elements.
- I can overwrite unnecessary duplicate positions with new unique values.
- The values after the returned length are irrelevant.
- slow tracks the last stored unique value.
- fast searches for the next unique value.
- The number of unique values is slow + 1.
