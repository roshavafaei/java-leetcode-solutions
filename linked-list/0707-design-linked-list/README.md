# 707. Design Linked List

## Problem

Design a singly linked list that supports:
get(index)
addAtHead(val)
addAtTail(val)
addAtIndex(index, val)
deleteAtIndex(index)

---

## Key Idea

I built the linked list using nodes instead of an array.

Each node stores:
int val;
Node next;

The list also keeps track of:
head
tail
size

- head points to the first node.
- tail points to the last node.
- size stores the number of nodes.

---

## Getting a Value by Index

A linked list does not provide direct index access like an array.

To reach an index, I start from head and move through next.
Node current = head;

for (int i = 0; i < index; i++) {
    current = current.next;
}

If the index is invalid, the method returns -1.

---

## Adding at the Head

To add a new node at the beginning:
node.next = head;
head = node;

If the list is empty, both head and tail must point to the new node.

---

## Adding at the Tail

To add a node at the end:
tail.next = node;
tail = node;

If the list is empty, both head and tail become the new node.

---

## Adding at an Index

There are three cases:
index == 0      → add at head
index == size   → add at tail
otherwise       → add between two nodes

For a middle insertion, I move to the node before the target index.
node.next = current.next;
current.next = node;

The order of these two lines is important because the rest of the list must not be lost.

---

## Deleting at an Index

There are also separate cases for deletion.

### Delete the head
head = head.next;

If the list becomes empty, tail must also become null.

### Delete a middle node

Move to the node before the target and skip the node being deleted:
current.next = current.next.next;

### Delete the tail

After removing the last node, tail must point to the previous node.

---

## Solution
class MyLinkedList {

    private class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);

        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }

        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size)
            return;

        if (index == 0) {
            addAtHead(val);
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        Node node = new Node(val);
        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        node.next = current.next;
        current.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;

        if (index == 0) {
            head = head.next;
            size--;

            if (size == 0)
                tail = null;

            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;

        if (index == size - 1)
            tail = current;

        size--;
    }
}

---

## Complexity

- get() — O(n)
- addAtHead() — O(1)
- addAtTail() — O(1)
- addAtIndex() — O(n)
- deleteAtIndex() — O(n)
- Space — O(n)

---

## What I Learned

- How to build a linked list using nodes.
- How head, tail, and size work together.
- Why index access requires traversal from head.
- How to insert a node without losing the rest of the list.
- How to delete a node by changing links.
- Why head and tail cases must be handled separately.
