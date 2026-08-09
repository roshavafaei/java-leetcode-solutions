class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int max = 0;
        int right = height.length -1;
        
        while (left < right) {
        int w = right - left;
        int h = Math.min(height[right], height[left]);
        int area = w * h;
           
        if (area > max)
            max = area;
        
        if (height[left] < height[right]) 
            left++;
        
        else 
            right--;
        }
        
        return max;         
    }
}
