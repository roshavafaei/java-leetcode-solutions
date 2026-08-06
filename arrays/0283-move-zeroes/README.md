# 283. Move Zeroes

## Approach

I used two pointers:

- fast scans the array.
- slow marks the next position for a non-zero value.

First, all non-zero values are moved to the beginning while keeping their original order.

Then, the remaining positions are filled with zeroes.

## Solution
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}

## Complexity
Time: O(n)
Space: O(1)
