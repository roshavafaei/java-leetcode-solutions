class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            
            if (isOperator(str)) {
                int first = stack.pop();
                int second = stack.pop();
                
                stack.push(calculateTheResult(first, second, str));
            }
            
            else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("/") || string.equals("*");
    }

    private int calculateTheResult(int first, int second, String operator) {

        if(operator.equals("+"))
            return second + first;

        else if (operator.equals("-"))
            return second - first;

        else if (operator.equals("/"))
            return second / first;

        else return second * first;
    }
}
