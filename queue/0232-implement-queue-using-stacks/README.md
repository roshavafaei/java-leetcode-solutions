# 232. Implement Queue using Stacks

## Problem

Implement a queue using two stacks.

The queue must support:
push(x)
pop()
peek()
empty()

A queue follows FIFO, while a stack follows LIFO.

---

## Key Idea

I used two stacks:
stack1
stack2

- stack1 stores newly added values.
- stack2 stores values in queue order.

For example, after pushing:
1, 2, 3

stack1 contains:
top
 ↓
 3
 2
 1

After moving everything to stack2:
top
 ↓
 1
 2
 3

Now the oldest value is on top.

---

## Approach

### Push

New values always go into stack1.
public void push(int x) {
    stack1.push(x);
}

### Move Elements

Elements move from stack1 to stack2 only when stack2 is empty.
private void moveStack1ToStack2() {
    if (stack2.empty()) {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
    }
}

This preserves FIFO order.

### Pop and Peek

Before pop() or peek(), make sure stack2 contains the oldest values.
moveStack1ToStack2();

Then use:
stack2.pop();

or:
stack2.peek();

### Empty

The queue is empty only when both stacks are empty.
return stack1.empty() && stack2.empty();

---

## Solution
import java.util.Stack;

class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    private void moveStack1ToStack2() {
        if (stack2.empty()) {
            while (!stack1.empty())
                stack2.push(stack1.pop());
        }
    }

    public MyQueue() {
       stack1 = new Stack<>();
       stack2 = new Stack<>(); 
    }
    
    public void push(int x) {
     stack1.push(x);   
    }
    
    public int pop() {
        if (empty())
            throw new IllegalStateException();

        moveStack1ToStack2();
        return stack2.pop();
    }
    
    public int peek() {
        if (empty())
            throw new IllegalStateException();

        moveStack1ToStack2();
        return stack2.peek();
    }
    
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

   
}

---

## Complexity

- push() — O(1)
- pop() — amortized O(1)
- peek() — amortized O(1)
- empty() — O(1)
- Space — O(n)

---

## What I Learned

- How two stacks can simulate a queue.
- How transferring elements reverses their order.
- Why elements move only when stack2 is empty.
- Why both stacks must be checked in empty().
- How FIFO behavior can be created using LIFO structures.
