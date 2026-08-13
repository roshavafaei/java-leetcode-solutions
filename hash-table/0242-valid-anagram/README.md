# 242. Valid Anagram

## Problem

Given two strings s and t, determine whether t is an anagram of s.

Two strings are anagrams if they contain exactly the same characters with the same frequencies, only in a different order.

## Approach

I used a HashMap<Character, Integer> to count how many times each character appears in s.

First, I check whether the two strings have the same length. If their lengths are different, they cannot be anagrams.

Then I build a frequency map for s.

For example:
s = "anagram"

a -> 3
n -> 1
g -> 1
r -> 1
m -> 1

Next, I iterate through t.

For every character:

- If the character does not exist in the map, return false.
- If its count has already reached 0, return false.
- Otherwise, decrease its count by 1.

If the entire second string is processed successfully, the two strings are valid anagrams.

## Key Idea

The values stored in the HashMap represent how many occurrences of each character are still available.

While processing t, each matching character uses one of those occurrences.

If a character is missing or used more times than it appears in s, the strings are not anagrams.

## Complexity

- Time: O(n)
- Space: O(n)
