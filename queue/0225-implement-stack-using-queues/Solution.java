class MyStack {
    Queue<Integer> queue;

    public MyStack() {
      queue = new ArrayDeque<>();  
    }
    
    public void push(int x) {
        int oldSize = queue.size();

        queue.add(x);
        for (int i = 0; i < oldSize; i++)
            queue.add(queue.remove());
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
