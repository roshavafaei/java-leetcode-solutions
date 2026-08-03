# 622. Design Circular Queue

## Problem

Design a circular queue with a fixed capacity.

The queue must support:
enQueue(value)
deQueue()
Front()
Rear()
isEmpty()
isFull()

A circular queue behaves like a normal FIFO queue, but when an index reaches the end of the array, it can return to the beginning and reuse available positions.

---

## My Initial Idea

Since the queue has a fixed capacity, I decided to use an array to store its values.
private int[] items;

I also needed variables to track the state of the queue:
private int front;
private int rear;
private int count;

Their responsibilities are:
front → index of the first element
rear  → index of the next empty position
count → number of stored elements

The most important point is that rear does not point to the last element.

It points to the position where the next value will be inserted.

---

## Creating the Circular Movement

In a normal array, moving forward with:
rear++;

eventually moves the index outside the array.

To make the queue circular, I used modulo:
rear = (rear + 1) % items.length;

The same idea is used for front:
front = (front + 1) % items.length;

Suppose the array length is 5.
Valid indexes: 0, 1, 2, 3, 4

The indexes move like this:
0 → 1 → 2 → 3 → 4 → 0

For example:
rear = 4

Then:
rear = (4 + 1) % 5
rear = 5 % 5
rear = 0

This allows the queue to return to the beginning of the array.

---

## My First Mistake: Returning the Index

At first, I returned front and rear directly:
return front;
return rear;

But these variables store indexes, not the values inside the array.

For example:
index:  0   1   2
items: [1,  2,  3]

If:
front = 0

then returning front gives:
0

But the actual front value is:
items[front]

which gives:
1

So the correct Front() method returns:
return items[front];

---

## Why items[rear] Does Not Work

At first, I thought the rear value could be returned with:
return items[rear];

But after inserting an element, rear immediately moves to the next empty position.

Suppose the capacity is 3.

Initially:
index:  0   1   2
items: [_,  _,  _]

rear = 0

After inserting 1:
items[rear] = 1;
rear = (rear + 1) % items.length;

The state becomes:
index:  0   1   2
items: [1,  _,  _]

rear = 1

The last value is stored at index 0, but rear already points to index 1.

Therefore:
items[rear]

accesses the next empty position instead of the last inserted element.

---

## Why items[rear - 1] Is Not Always Safe

Since the last element is one position before rear, I considered:
items[rear - 1]

This works when rear is greater than zero.

But when:
rear = 0

it becomes:
items[-1]

Negative indexes are invalid in Java.

This situation happens when rear wraps from the end of the array back to the beginning.

---

## Finding the Last Element Safely

To move one position backward in a circular array, I used:
int rearIndex = (rear - 1 + items.length) % items.length;

This means:
Move one position backward.
If that would produce -1, wrap around to the last index.

For example, if:
rear = 0
items.length = 5

then:
(0 - 1 + 5) % 5
= 4 % 5
= 4

So the previous position of index 0 is correctly calculated as index 4.

If:
rear = 3
items.length = 5

then:
(3 - 1 + 5) % 5
= 7 % 5
= 2

So the previous position is index 2.

The correct rear value is therefore:
return items[(rear - 1 + items.length) % items.length];

---

## Why count Is Necessary

In a circular queue, front and rear can be equal in two different situations.

The queue may be empty:
front = 0
rear = 0
count = 0

Or the queue may be full:
front = 0
rear = 0
count = items.length

Because front == rear alone cannot distinguish these cases, I used count.
public boolean isEmpty() {
    return count == 0;
}
public boolean isFull() {
    return count == items.length;
}

---

## Solution
class MyCircularQueue {

    private int[] items;
    private int front;
    private int rear;
    private int count;

    public MyCircularQueue(int k) {
        items = new int[k];
        front = 0;
        rear = 0;
        count = 0;
    }

    public boolean enQueue(int value) {
    if (isFull())
            return false;

        items[rear] = value;
        rear = (rear + 1) % items.length;
        count++;

        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;

        front = (front + 1) % items.length;
        count--;

        return true;
    }

    public int Front() {
        if (isEmpty())
            return -1;

        return items[front];
    }

    public int Rear() {
        if (isEmpty())
            return -1;

        int rearIndex =
                (rear - 1 + items.length) % items.length;

        return items[rearIndex];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }
}

---

## Complexity

Every operation takes constant time:
enQueue() → O(1)
deQueue() → O(1)
Front()   → O(1)
Rear()    → O(1)
isEmpty() → O(1)
isFull()  → O(1)

Space complexity:
O(k)

where k is the queue capacity.

---

## What I Learned

- How to implement a circular queue using an array.
- How modulo makes an index return to the beginning of an array.
- front points to the first stored value.
- rear points to the next empty insertion position.
- Returning an index is different from returning the value at that index.
- items[rear] does not give the last value because rear has already moved.
- items[rear - 1] fails when rear is zero.
- Adding the array length before modulo prevents a negative index.
- count distinguishes an empty queue from a full queue when front == rear.
