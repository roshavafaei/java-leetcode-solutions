# 225. Implement Stack using Queues

## Problem

Implement a stack using a queue.

The stack must support:
push(x)
pop()
top()
empty()

A stack follows LIFO, while a queue follows FIFO.

---

## Key Idea

I used one queue.

Normally, a queue removes the oldest value first. To simulate a stack, I rearrange the queue after every push() so that the newest value moves to the front.

For example:
Before:
[1, 2]

push(3)

After adding:
[1, 2, 3]

After rotating old elements:
[3, 1, 2]

Now 3 is at the front, so pop() and top() behave like a stack.

---

## Approach

1. Save the current queue size.
2. Add the new value.
3. Move all previous elements from the front to the back.
4. The newest value becomes the front of the queue.

---

## Solution
import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {
    private Queue<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        int oldSize = queue.size();

        queue.add(x);

        for (int i = 0; i < oldSize; i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

---

## Complexity

- push() — O(n)
- pop() — O(1)
- top() — O(1)
- empty() — O(1)
- Space — O(n)

---

## What I Learned

- How to simulate LIFO behavior using a FIFO structure.
- Why rotating the queue after push() moves the newest value to the front.
- How one queue is enough to implement a stack.
- Why pop() and top() become simple after rearranging during push().
