LeetCode 150 - Evaluate Reverse Polish Notation
Problem
We are given an array of strings representing an arithmetic expression written in Reverse Polish Notation (RPN).
Valid operators are:
+
-
*
/
In Reverse Polish Notation, the operator comes after its operands.
For example:
["2", "1", "+", "3", "*"]
represents:
(2 + 1) * 3
So the result is:
9
Initial Understanding
At first, the main challenge was understanding how Reverse Polish Notation works.
In a normal expression:
2 + 1
the operator is between the operands.
In RPN:
2 1 +
the operator comes after the two operands.
This makes a Stack a natural choice because when we encounter an operator, the two most recent numbers are exactly the operands we need.
Approach
We iterate through every token in the input array.
If the token is a number:
Convert it from String to int and push it into the stack.
stack.push(Integer.parseInt(str));
If the token is an operator:
Pop the first number from the stack.
Pop the second number from the stack.
Perform the operation.
Push the result back into the stack.
At the end, only one number remains in the stack.
That number is the final result.
Example
Input:
["2", "1", "+", "3", "*"]
Step 1
Token = 2
Push it into the stack:
Stack:

2
Step 2
Token = 1
Push it into the stack:
Stack:

1  <- top
2
Step 3
Token = +
Pop two numbers:
first = 1
second = 2
Calculate:
2 + 1 = 3
Push the result:
Stack:

3
Step 4
Token = 3
Push it:
Stack:

3  <- top
3
Step 5
Token = *
Pop two numbers:
first = 3
second = 3
Calculate:
3 * 3 = 9
Push the result:
Stack:

9
So the final answer is:
9
Important Detail: Operand Order
The order of the popped values is very important, especially for subtraction and division.
For example:
["10", "3", "-"]
Before processing -, the stack looks like:
Stack:

3   <- top
10
The first pop gives:
first = stack.pop();   // 3
The second pop gives:
second = stack.pop();  // 10
The correct operation is:
second - first
So:
10 - 3 = 7
NOT:
3 - 10 = -7
The same rule applies to division:
second / first
Solution
public int evalRPN(String[] tokens) {

    Stack<Integer> stack = new Stack<>();

    for (String str : tokens) {

        if (isOperator(str)) {

            int first = stack.pop();
            int second = stack.pop();

            stack.push(calculateTheResult(first, second, str));

        } else {

            stack.push(Integer.parseInt(str));
        }
    }

    return stack.pop();
}

private boolean isOperator(String string) {

    return string.equals("+")
            || string.equals("-")
            || string.equals("/")
            || string.equals("*");
}

private int calculateTheResult(int first, int second, String operator) {

    if (operator.equals("+"))
        return second + first;

    else if (operator.equals("-"))
        return second - first;

    else if (operator.equals("/"))
        return second / first;

    else
        return second * first;
}
What I Learned
Reverse Polish Notation places the operator after its operands.
Stack is useful because we always need the most recently added operands.
We can convert a String to an integer using:
Integer.parseInt(str);
When an operator appears, we pop two values from the stack.
The first popped value is the right operand.
The second popped value is the left operand.
Operand order is especially important for subtraction and division.
After performing an operation, we push the result back into the stack.
At the end, the only remaining value in the stack is the final answer.
Helper methods such as isOperator() and calculateTheResult() make the solution cleaner and easier to understand.
Complexity
Let n be the number of tokens.
Time Complexity
O(n)
We iterate through all tokens once.
Each stack operation (push and pop) takes O(1) time.
Space Complexity
O(n)
In the worst case, multiple operands can be stored in the stack.
Key Takeaway
When solving an RPN problem, think:
Number -> Push
Operator -> Pop two values -> Calculate -> Push result
The most important detail is:
first  = first pop
second = second pop

operation = second operator first 
