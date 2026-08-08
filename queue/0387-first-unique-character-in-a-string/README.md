# 387. First Unique Character in a String

## Problem

Given a string s, return the index of the first character that appears only once.

If there is no unique character, return -1.

## Approach

I solved this problem using a Queue together with a HashMap.

The Queue stores the indices of the characters in their original order.

The HashMap stores the frequency of each character in the string.

First, I add all indices to the queue and count how many times each character appears.

Then I check the index at the front of the queue.

If the character at that index appears more than once, I remove that index from the queue and continue checking the next one.

Because a queue follows FIFO order, the first index that remains at the front and belongs to a character with frequency 1 is the first unique character.

If all indices are removed, there is no unique character and I return -1.

## Example

For:
s = "loveleetcode"

The frequency map contains the number of occurrences of each character.

The queue initially contains:
[0, 1, 2, 3, ...]

Characters at the front that appear more than once are removed.

Eventually, index 2 remains at the front because:
s.charAt(2) = 'v'

and 'v' appears only once.

So the result is:
2

## Key Idea

The queue preserves the original order of the characters, while the hash map tells us whether the character at the front of the queue is unique or repeated.

## Complexity

- Time: O(n)
- Space: O(n)
