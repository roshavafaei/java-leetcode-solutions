# 20. Valid Parentheses

## Problem

Given a string containing only the characters:

```text
( ) [ ] { }

determine whether the string is valid.

A string is valid when:

Every opening bracket is closed by the same type of bracket.
Brackets are closed in the correct order.
Every closing bracket has a corresponding opening bracket.

⸻

My Problem-Solving Process

At first, I did not try to solve the entire problem at once.

I reduced the problem to a much smaller version and started only with parentheses: ()

My first goal was simply to make this small case work correctly.
I used a stack and followed this basic idea:
Push an opening parenthesis onto the stack.
When a closing parenthesis appears, check the top of the stack.
If the stack is empty or the brackets do not match, return false.
At the end, the stack must be empty.
Once the logic worked correctly for parentheses, I added the other bracket types:
[ ]
{ }

This helped me avoid trying to solve too many things at the same time.

Instead of thinking about the full problem immediately, I solved one small part first, confirmed that it worked, and then expanded the solution.

⸻

Initial Version

My first working version checked every bracket type directly inside the main method.

public boolean isValid(String input) {
    Stack<Character> stack = new Stack<>();

    for (char ch : input.toCharArray()) {
        if (ch == '('  ch == '['  ch == '{')
            stack.push(ch);

        if (ch == ')'  ch == ']'  ch == '}') {
            if (stack.empty())
                return false;

            char top = stack.pop();

            if (
                (ch == ')' && top != '(') ||
                (ch == ']' && top != '[') ||
                (ch == '}' && top != '{')
            )
                return false;
        }
    }

    return stack.empty();
}

This version worked, but the main method contained several responsibilities:

Detecting opening brackets
Detecting closing brackets
Checking whether two brackets matched
Managing the stack

The code was correct, but it was becoming harder to read and extend.

⸻

First Refactor

The first refactor was to extract repeated bracket checks into helper methods.

Instead of writing this directly:
if (ch == '('  ch == '['  ch == '{')

I created:
private boolean isLeftBracket(char ch) {
    return ch == '('  ch == '['  ch == '{';
}

I did the same for closing brackets:
private boolean isRightBracket(char ch) {
    return ch == ')'  ch == ']'  ch == '}';
}

I also moved the matching logic into a separate method:
private boolean bracketsMatch(char left, char right) {
    return
        (right == ')' && left == '(') ||
        (right == ']' && left == '[') ||
        (right == '}' && left == '{');
}

After this refactor, the main method became easier to understand:
if (isLeftBracket(ch))
    stack.push(ch);

if (isRightBracket(ch)) {
    if (stack.empty())
        return false;

    char top = stack.pop();

    if (!bracketsMatch(top, ch))
        return false;
}

The main method now focused mostly on the algorithm, while the helper methods handled bracket details.

⸻

Second Refactor

In the second refactor, I removed the repeated bracket comparisons.

I created two parallel lists:
private final List<Character> leftBrackets =
        Arrays.asList('(', '[', '{');

private final List<Character> rightBrackets =
        Arrays.asList(')', ']', '}');

Each opening bracket and its matching closing bracket have the same index:
leftBrackets:   (   [   {
index:          0   1   2

rightBrackets:  )   ]   }
index:          0   1   2

This allowed me to simplify the helper methods:
private boolean isLeftBracket(char ch) {
    return leftBrackets.contains(ch);
}

private boolean isRightBracket(char ch) {
    return rightBrackets.contains(ch);
}

The matching logic also became much shorter:
private boolean bracketsMatch(char left, char right) {
    return leftBrackets.indexOf(left) ==
           rightBrackets.indexOf(right);
}

Instead of manually checking every possible pair, I compared their positions in the two lists.


⸻


Final Approach
The final solution uses a stack.
For each character:
If it is an opening bracket, push it onto the stack.
If it is a closing bracket:
Return false if the stack is empty.
Pop the most recent opening bracket.
Check whether the opening and closing brackets match.
After processing the full string, return whether the stack is empty.
The stack is the correct data structure because brackets must close in reverse order.
This follows the Last In, First Out principle.


⸻


Final Solution

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {

    private final List<Character> leftBrackets =
            Arrays.asList('(', '[', '{');

    private final List<Character> rightBrackets =
            Arrays.asList(')', ']', '}');

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (isLeftBracket(ch))
                stack.push(ch);

            if (isRightBracket(ch)) {
                if (stack.empty())
                    return false;

                char top = stack.pop();

                if (!bracketsMatch(top, ch))
                    return false;
            }
        }

        return stack.empty();
    }

    private boolean isLeftBracket(char ch) {
        return leftBrackets.contains(ch);
    }

    private boolean isRightBracket(char ch) {
        return rightBrackets.contains(ch);
    }

    private boolean bracketsMatch(char left, char right) {
        return leftBrackets.indexOf(left) ==
               rightBrackets.indexOf(right);
    }
}

⸻

Example

Input: "([])"

Stack process:
(  -> push
[  -> push
]  -> pop [
)  -> pop (

The stack is empty at the end so the result is:
true

⸻

Complexity

Time Complexity
0(n)

The string is processed once.

The bracket lists contain only three items, so contains() and indexOf() operate on a constant-sized collection.

Space Complexity
O(n)

In the worst case, every character is an opening bracket and is stored in the stack.


⸻


What I Learned
How to use a stack for matching nested structures.
Why the most recent opening bracket must match the next closing bracket.
How to detect a closing bracket when the stack is empty.
How to break a problem into a smaller working version first.
How starting only with () made the full problem easier to solve.
How to expand a working solution step by step.
How to refactor repeated conditions into helper methods.
How to make the main method easier to read.
How parallel lists can simplify matching logic.
That solving the problem and improving the code are two separate stages.
