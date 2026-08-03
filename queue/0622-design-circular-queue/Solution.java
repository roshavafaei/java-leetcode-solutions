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

        items[front] = 0;
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

        int rearIndex = (rear - 1 + items.length) % items.length;
        return items[rearIndex];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == items.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
