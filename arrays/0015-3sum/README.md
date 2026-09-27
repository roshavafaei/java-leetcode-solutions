3Sum
Problem
Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] such that:
nums[i] + nums[j] + nums[k] == 0
The solution must not contain duplicate triplets.
Example:
Input:
nums = [-1, 0, 1, 2, -1, -4]

Output:
[[-1, -1, 2], [-1, 0, 1]]
Approach
We use:
Sorting + Two Pointers
First, sort the array.
For example:
Original:
[-1, 0, 1, 2, -1, -4]

Sorted:
[-4, -1, -1, 0, 1, 2]
Then we choose one number using i.
For the remaining two numbers, we use two pointers:
left = i + 1
right = nums.length - 1
So the structure looks like:
        i   left             right
        ↓    ↓                 ↓
[-4,   -1,  -1,   0,   1,     2]
For every position of i, we calculate:
int sum = nums[i] + nums[left] + nums[right];
There are three possible cases.
Case 1: sum == 0
We found a valid triplet.
result.add(Arrays.asList(
    nums[i],
    nums[left],
    nums[right]
));
Then move both pointers:
left++;
right--;
Case 2: sum < 0
The sum is too small.
Because the array is sorted, we need a larger number.
So we move left to the right:
left++;
Example:
[-4, -1, 0, 1, 2]
      ↑           ↑
     left        right

left →
Moving left to the right gives us a larger value.
Case 3: sum > 0
The sum is too large.
We need a smaller number.
So we move right to the left:
right--;
left →           ← right
The important rule is:
sum < 0  → left++
sum > 0  → right--
Handling Duplicates
The problem requires unique triplets.
For example, we don't want:
[-1, 0, 1]
[-1, 0, 1]
[-1, 0, 1]
Duplicate i
After sorting, duplicate values are next to each other.
Therefore:
if (i > 0 && nums[i] == nums[i - 1])
    continue;
If the current value of i is the same as the previous value, we skip it.
Duplicate left
After finding a valid triplet, we first move:
left++;
Then we check whether the new value is the same as the value we just used:
while (left < right &&
       nums[left] == nums[left - 1])
    left++;
For example:
[-2, 0, 0, 0, 2]
     ↑
   old left
After left++:
[-2, 0, 0, 0, 2]
        ↑
       left
Since:
nums[left] == nums[left - 1]

0 == 0
we skip this duplicate.
Duplicate right
The same idea applies to right.
After:
right--;
we check:
while (left < right &&
       nums[right] == nums[right + 1])
    right--;
Notice the difference:
left  compares with left - 1
right compares with right + 1
This is because:
left moves  →
right moves ←
Example Walkthrough
Consider:
nums = [-1, 0, 1, 2, -1, -4]
After sorting:
[-4, -1, -1, 0, 1, 2]
Eventually i points to -1:
[-4, -1, -1, 0, 1, 2]
      ↑   ↑           ↑
      i  left       right
Calculate:
-1 + -1 + 2 = 0
So:
[-1, -1, 2]
is added to the result.
Move both pointers.
Later we get:
-1 + 0 + 1 = 0
So:
[-1, 0, 1]
is also added.
Final result:
[
    [-1, -1, 2],
    [-1, 0, 1]
]
Java Solution
import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    left++;
                    right--;

                    while (left < right &&
                           nums[left] == nums[left - 1])
                        left++;

                    while (left < right &&
                           nums[right] == nums[right + 1])
                        right--;

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        return result;
    }
}
Time Complexity
Sorting takes:
O(n log n)
The outer loop runs approximately n times.
For every i, the two pointers scan the remaining part of the array in O(n) time.
Therefore:
O(n × n) = O(n²)
Overall:
O(n log n) + O(n²)
which simplifies to:
O(n²)
Space Complexity
Ignoring the output list, the algorithm uses only a few variables:
i
left
right
sum
Therefore, the extra space is approximately:
O(1)
The sorting implementation may use additional internal memory depending on the language/library.
Key Idea
Instead of checking every possible combination using three nested loops:
O(n³)
we sort the array and fix one number.
Then we find the other two numbers using Two Pointers:
Fix one number
      +
Two Pointers
      ↓
O(n²)
The most important rule to remember is:
sum == 0 → found a triplet

sum < 0  → left++

sum > 0  → right--
And duplicates must be skipped so that every triplet appears only once.
