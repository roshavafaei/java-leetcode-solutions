class MinStack {
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int value) {
        mainStack.push(value);

        if (minStack.empty() || minStack.peek() >= value)
            minStack.push(value);
    }
    
    public void pop() {
        if (mainStack.empty())
            throw new IllegalStateException();

        int val = mainStack.pop();
        if (val == minStack.peek())
            minStack.pop();
    }
    
    public int top() {
        return mainStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
