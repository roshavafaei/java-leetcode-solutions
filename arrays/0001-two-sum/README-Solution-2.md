LeetCode 1 - Two Sum | Solution 2
About This Solution
This is my second solution for the Two Sum problem.
I previously solved this problem using nested loops (brute force) with a time complexity of:
O(n²)
I already documented that approach in the README for my first solution.
After learning more about hashing, I decided to solve the problem again using a HashMap to make the solution more efficient.
With this approach, the time complexity is reduced from:
O(n²) → O(n)
This README focuses on my second and optimized solution using a HashMap.
Approach - HashMap
For each number, I calculate the number needed to reach the target:
needed = target - currentNumber
Then I check whether that number has already been stored in the HashMap.
The HashMap stores:
number → index
If the needed number already exists, I return its index together with the current index.
Solution
public int[] twoSum(int[] nums, int target) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {

        int needed = target - nums[i];

        if (map.containsKey(needed)) {
            return new int[]{map.get(needed), i};
        }

        map.put(nums[i], i);
    }

    return new int[]{};
}
Example Walkthrough
nums = [2, 7, 11, 15]
target = 9
Initially:
map = {}
First Iteration
i = 0
nums[i] = 2

needed = 9 - 2 = 7
7 is not in the HashMap, so we store:
map = {
    2 → 0
}
Second Iteration
i = 1
nums[i] = 7

needed = 9 - 7 = 2
2 already exists in the HashMap:
2 → 0
So we return:
[0, 1]
Complexity
Previous Solution - Nested Loops
Time Complexity: O(n²)
Space Complexity: O(1)
Second Solution - HashMap
Time Complexity: O(n)
Space Complexity: O(n)
The HashMap uses additional memory, but it allows us to reduce the time complexity from O(n²) to O(n).
What I Learned
How to improve a brute-force solution using a HashMap.
How to trade additional space for better time complexity.
How to store a value and its index in a HashMap.
How to use containsKey() to check whether a value was previously seen.
How to use get() to retrieve the index of a previously seen value.
How to calculate the complement:
needed = target - currentNumber
Why we check the HashMap before adding the current number.
How the same problem can have multiple solutions with different time and space complexities.
Pattern
Hashing / HashMap
The main idea:
current number
      ↓
needed = target - current
      ↓
Have I seen needed before?
      ↓
YES → return both indices
NO  → store current number and its index
