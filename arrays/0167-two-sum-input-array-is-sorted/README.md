LeetCode 167 - Two Sum II: Input Array Is Sorted
Problem
Given a sorted integer array numbers and an integer target, find two numbers whose sum is equal to target.
Return the indices of these two numbers.
The important detail is that the answer must use 1-based indexing.
Example
Input
numbers = [2, 7, 11, 15]
target = 9
Output
[1, 2]
Because:
2 + 7 = 9
Their Java array indices are:
0 and 1
But the problem uses 1-based indexing, so we return:
[1, 2]
Main Idea - Two Pointers
Since the array is already sorted, I can use two pointers.
One pointer starts from the beginning:
int left = 0;
The other starts from the end:
int right = numbers.length - 1;
For example:
numbers = [2, 7, 11, 15]

           ↓          ↓
          left       right
Then I calculate:
int sum = numbers[left] + numbers[right];
There are three possible situations.
1. Sum equals target
if (sum == target)
I found the answer.
Because the problem requires 1-based indices:
return new int[]{left + 1, right + 1};
2. Sum is greater than target
if (sum > target)
The sum is too large.
Since the array is sorted, moving right to the left gives me a smaller number.
right--;
3. Sum is smaller than target
If:
sum < target
the sum is too small.
Since the array is sorted, moving left to the right gives me a larger number.
left++;
Example Walkthrough
numbers = [2, 7, 11, 15]
target = 9
Initially:
[2, 7, 11, 15]
 ↑           ↑
left        right
Calculate:
2 + 15 = 17
Since:
17 > 9
move right:
[2, 7, 11, 15]
 ↑       ↑
left    right
Now:
2 + 11 = 13
Again:
13 > 9
Move right:
[2, 7, 11, 15]
 ↑  ↑
left right
Now:
2 + 7 = 9
We found the target.
Java indices:
left = 0
right = 1
But the problem requires 1-based indexing:
[1, 2]
Solution
public int[] twoSum(int[] numbers, int target) {

    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {

        int sum = numbers[left] + numbers[right];

        if (sum == target)
            return new int[]{left + 1, right + 1};

        else if (sum > target)
            right--;

        else
            left++;
    }

    return new int[]{};
}
Why Two Pointers Instead of HashMap?
The original Two Sum problem can be efficiently solved using a HashMap.
That solution has:
Time:  O(n)
Space: O(n)
However, this problem gives us an important additional property:
The input array is already sorted.
Because the array is sorted, I can use Two Pointers and decide which pointer to move based on the current sum.
This gives:
Time:  O(n)
Space: O(1)
So both approaches can have O(n) time complexity, but Two Pointers does not require a HashMap.
The problem also specifically requires constant extra space, making Two Pointers the appropriate approach.
Why Does Moving the Pointers Work?
Because the array is sorted:
small ----------------------> large
If the current sum is too large:
numbers[left] + numbers[right] > target
I need a smaller number, so:
right--;
If the current sum is too small:
numbers[left] + numbers[right] < target
I need a larger number, so:
left++;
This allows me to eliminate impossible pairs without checking every combination.
Complexity
Time Complexity
O(n)
Each pointer moves only in one direction.
left moves from left to right and right moves from right to left.
Space Complexity
O(1)
I only use a few variables:
left
right
sum
No additional data structure grows with the size of the input.
What I Learned
How to recognize the Two Pointers pattern.
A sorted array can often eliminate the need for a HashMap.
If the sum is too large, move the right pointer left.
If the sum is too small, move the left pointer right.
Two Pointers can achieve O(n) time with O(1) extra space.
The problem uses 1-based indexing, so I must return:
left + 1
right + 1
instead of the normal Java array indices.
Pattern
Two Pointers
A useful pattern to remember:
Two Sum + Unsorted Array
        ↓
     HashMap
        ↓
Time: O(n)
Space: O(n)


Two Sum + Sorted Array
        ↓
   Two Pointers
        ↓
Time: O(n)
Space: O(1)
