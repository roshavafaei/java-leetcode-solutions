LeetCode 739 - Daily Temperatures
Problem
Given an array temperatures, return an array answer where:
answer[i] represents how many days we have to wait after day i to get a warmer temperature.
If there is no warmer day in the future, answer[i] should be 0.
Example
Input:
[73, 74, 75, 71, 69, 72, 76, 73]
Output:
[1, 1, 4, 2, 1, 1, 0, 0]
Approach - Monotonic Stack
We use a stack to keep the indices of days whose next warmer temperature has not been found yet.
We store indices instead of temperatures because we need to calculate the number of days between two temperatures.
For each temperature:
Check the index at the top of the stack.
If the current temperature is warmer than the temperature at that index, we have found the answer for that previous day.
Pop that index from the stack.
Calculate the difference between the current index and the previous index.
Continue this process while the current temperature is warmer.
Push the current index onto the stack.
The stack therefore keeps indices of temperatures that are still waiting for a warmer day.
Example Walkthrough
For:
[73, 71, 69, 72]
When we reach 72 at index 3:
Stack contains:
[0, 1, 2]
Index 2 → temperature 69
72 > 69
So:
answer[2] = 3 - 2 = 1
Pop index 2.
Now index 1 is on top:
temperature = 71
72 > 71
So:
answer[1] = 3 - 1 = 2
Pop index 1.
Now index 0 is on top:
temperature = 73
72 is not greater than 73, so we stop.
Java Solution
public int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stack = new Stack<>();
    int[] answer = new int[temperatures.length];

    for (int i = 0; i < temperatures.length; i++) {

        while (!stack.empty() &&
               temperatures[i] > temperatures[stack.peek()]) {

            int prevIndex = stack.pop();
            answer[prevIndex] = i - prevIndex;
        }

        stack.push(i);
    }

    return answer;
}
