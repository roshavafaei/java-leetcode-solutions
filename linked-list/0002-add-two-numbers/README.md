LeetCode 2 - Add Two Numbers
Problem
We are given two non-empty linked lists that represent two non-negative integers.
The digits are stored in reverse order, and each node contains a single digit.
For example:
l1 = 2 → 4 → 3
l2 = 5 → 6 → 4
These represent:
342
465
So:
342 + 465 = 807
The result should also be returned in reverse order:
7 → 0 → 8


⸻


Initial Idea
At first, I thought about storing the calculated digits in a Stack and then popping them to reverse the result.
However, this is unnecessary because the input linked lists are already stored in reverse order.
That means we can directly process the nodes from left to right, just like doing normal addition starting from the ones digit.


⸻


Final Approach
I traverse both linked lists at the same time.
For each position:
Get the current value from l1.
Get the current value from l2.
If one list has already ended, use 0 for its value.
Add both values together with the previous carry.
Use % 10 to get the digit for the current result node.
Use / 10 to calculate the new carry.
Add the new digit to the result linked list.
Move both pointers forward.
A dummy node is used to make building the result linked list easier.


⸻


Example
l1 = 8 → 3 → 2
l2 = 7 → 6 → 5
These represent:
238 + 567 = 805
Step 1
8 + 7 = 15

digit = 15 % 10 = 5
carry = 15 / 10 = 1

result:
5
Step 2
3 + 6 + 1 = 10

digit = 0
carry = 1

result:
5 → 0
Step 3
2 + 5 + 1 = 8

digit = 8
carry = 0

result:
5 → 0 → 8
Final result:
5 → 0 → 8


⸻


Important Edge Cases
Different List Lengths
The two linked lists are not guaranteed to have the same length.
For example:
l1 = 9 → 9 → 9
l2 = 1
If one list ends earlier, its value should be treated as 0.


⸻


Remaining Carry
After both linked lists are finished, there may still be a carry.
Example:
9 + 1 = 10
The result becomes:
0 → 1
So after the loop, we need to check whether carry > 0.


⸻


Solution
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    int carry = 0;

    while (l1 != null || l2 != null) {

        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;

        int addition = val1 + val2 + carry;

        int digit = addition % 10;
        carry = addition / 10;

        current.next = new ListNode(digit);
        current = current.next;

        if (l1 != null)
            l1 = l1.next;

        if (l2 != null)
            l2 = l2.next;
    }

    if (carry > 0) {
        current.next = new ListNode(carry);
    }

    return dummy.next;
}


⸻


What I Learned
The linked lists are already stored in reverse order, so there is no need to reverse them again.
A Stack is unnecessary for this problem.
When two linked lists have different lengths, a missing node can be treated as value 0.
% 10 is useful for extracting the current digit.
/ 10 is useful for calculating the carry.
A dummy node simplifies constructing a new linked list.


⸻


Complexity
Let:
m = length of l1
n = length of l2
Time Complexity
O(max(m, n))
We traverse each list only once.
Space Complexity
O(max(m, n))
The returned linked list contains approximately as many nodes as the longer input list, possibly one extra node for the final carry.
If the output space is not counted as auxiliary space, the extra working space is:
O(1)
