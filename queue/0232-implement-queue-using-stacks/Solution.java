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
