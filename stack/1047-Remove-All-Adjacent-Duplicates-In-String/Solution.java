class Solution {
    public String removeDuplicates(String s) {
      Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (!stack.empty() && stack.peek() == ch) 
                stack.pop();
            
            else stack.push(ch);
        }
        
        StringBuffer result = new StringBuffer();
        
        for (char ch : stack)
            result.append(ch);
            
        
        return result.toString();  
    }
}
