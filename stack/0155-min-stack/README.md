public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        mainStack.push(value);

        if (minStack.empty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        int value = mainStack.pop();

        if (value == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

---

## Complexity

### Push
O(1)

### Pop
O(1)

### Top
O(1)

### Get Minimum
O(1)

### Space Complexity
O(n)

In the worst case, every pushed value becomes a new minimum and is stored in both stacks.

---

## What I Learned

- How to extend a normal stack with additional behavior.
- Why one minimum variable is not enough.
- How a second stack can store the history of minimum values.
- Why minStack.peek() always gives the current minimum.
- Why duplicate minimum values must also be stored.
- Why the push condition must use <=.
- How both stacks stay synchronized during pop().
- How to implement all required operations in O(1) time.
- The difference between building a stack from scratch and designing a new data structure using an existing stack.
