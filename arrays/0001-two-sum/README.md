# LeetCode #1 - Two Sum

## Problem

Given an array of integers and a target value, return the indices of two different elements whose values add up to the target.

---

## My Initial Idea

My first idea was to select one number and compare it with the other numbers in the array.

At first, I accidentally compared an element with itself. I then realized that I needed two different indices and that the two numbers did not have to be next to each other.

---

## Approach

I used two nested loops:

- The outer loop selects the first number.
- The inner loop starts from the position after the first number and checks every remaining number.
- If the sum of the two values equals the target, their indices are returned.

The inner loop starts at i + 1 to avoid:

- Using the same element twice
- Checking the same pair more than once

---

## Final Solution
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("No solution found.");
    }
}

---

## Complexity

| Complexity | Value |
|------------|-------|
| Time | O(n²) |
| Space | O(1) |

---

## What I Learned

- A Java method can return an array using int[].
- Two Sum returns indices, not the values themselves.
- The two selected elements do not need to be next to each other.
- Starting the inner loop at i + 1 prevents using the same element twice.
- LeetCode arrays use nums.length because every element in the provided array is valid.
- A correct brute-force solution can be improved later using a HashMap.

