# 11. Container With Most Water

## Problem

Given an array height, each value represents the height of a vertical line.

Choose two lines that can hold the maximum amount of water between them.

Return the maximum area.

## Approach

I used the Two Pointers technique.

One pointer starts at the beginning of the array and the other starts at the end:
left = 0
right = height.length - 1

For every pair of pointers, I calculate:
width = right - left
height = min(height[left], height[right])
area = width * height

Then I update the maximum area found so far.

The important part is deciding which pointer to move.

The amount of water is limited by the shorter line, so I move the pointer pointing to the shorter height.
If left height is smaller:
    left++

Otherwise:
    right--

This gives us a chance to find a taller line while still keeping as much width as possible.

## Example

For:
height = [1,8,6,2,5,4,8,3,7]

One of the best pairs is:
index 1 -> height 8
index 8 -> height 7

Width:
8 - 1 = 7

Usable height:
min(8, 7) = 7

Area:
7 * 7 = 49

So the maximum area is:
49

## Key Idea

Instead of checking every possible pair with a nested loop, the two pointers move toward each other.

At each step, the shorter side is discarded because keeping it while reducing the width cannot produce a better area.

## Complexity

- Time: O(n)
- Space: O(1)
