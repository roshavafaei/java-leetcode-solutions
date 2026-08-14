238. Product of Array Except Self
Problem
Given an integer array nums, return an array answer where:
answer[i]
is the product of all elements in nums except nums[i].
The solution must run in O(n) time and cannot use division.
Approach
I solved this problem using prefix and suffix products.
This problem was initially difficult for me because I was not sure how to multiply all the elements before and after each index without including the current element itself.
The key idea is to solve the problem in two passes.
1. Prefix Pass
First, I move from left to right.
For each index, I store the product of all elements that appear before it.
For example:
nums = [1, 2, 3, 4]
After the prefix pass:
answer = [1, 1, 2, 6]
because:
index 0 -> 1
index 1 -> 1
index 2 -> 1 * 2 = 2
index 3 -> 1 * 2 * 3 = 6
The current element is not included because answer[i] is updated before nums[i] is added to prefix.
2. Suffix Pass
Then, I move from right to left.
I keep a suffix variable containing the product of all elements to the right of the current index.
For each index:
answer[i] = answer[i] * suffix;
Then I update the suffix:
suffix = suffix * nums[i];
Again, the current value is added to suffix only after its answer has been calculated.
For:
nums = [1, 2, 3, 4]
the final result becomes:
[24, 12, 8, 6]
Key Idea
For every index:
answer[i]
=
product of everything before i
×
product of everything after i
Using two passes allows us to calculate this without division and without checking the whole array for every index.
Complexity
Time: O(n)
Extra Space: O(1), excluding the output array
