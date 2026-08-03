class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];

        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int current : nums2) {
            while (!stack.empty() && current > stack.peek()) {
                int small = stack.pop();
                nextGreater.put(small, current);
            }
            stack.push(current);
        }
        
        while (!stack.empty())
            nextGreater.put(stack.pop(), -1);
        
        for (int i = 0; i < nums1.length; i++)
            answer[i] = nextGreater.get(nums1[i]);
        
        return answer;
    }
}
