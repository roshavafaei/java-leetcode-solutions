# 217. Contains Duplicate

## Approach

My first idea was to use a HashMap and store each number as a key.

That worked, but then I realized I did not actually need to store any extra value or index.

I only needed to know whether a number had already appeared before.

So I switched to a HashSet, which is simpler and more suitable for this problem.

## Solution
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num))
                return true;

            set.add(num);
        }

        return false;
    }
}

## Complexity
Time: O(n)
Space: O(n)
