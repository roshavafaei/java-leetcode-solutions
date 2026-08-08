# 1047. Remove All Adjacent Duplicates In String

## Problem

Given a string s, repeatedly remove adjacent duplicate characters until no more duplicate pairs remain.

Return the final string.

## Approach

I used a Stack to keep track of the characters.

For each character in the string:

- If the stack is not empty and the current character is equal to the top of the stack, I remove the top element with pop().
- Otherwise, I push the current character onto the stack.

This works because adjacent duplicates cancel each other out.

After processing the whole string, the characters left in the stack form the final answer.

## Example

For:
s = "abbaca"

The stack changes like this:
a      -> [a]

b      -> [a, b]

b      -> [a]
         duplicate, so pop

a      -> []
         duplicate, so pop

c      -> [c]

a      -> [c, a]

Final result:
"ca"

## Important Note

At first, I built the final string by repeatedly using pop().

Because a stack follows LIFO order, that reversed the result.

Instead, I iterate through the remaining stack from beginning to end and append each character to a StringBuilder.

## Complexity

- Time: O(n)
- Space: O(n)
