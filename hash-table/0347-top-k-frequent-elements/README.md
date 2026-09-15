LeetCode 347 - Top K Frequent Elements
Problem
Given an integer array nums and an integer k, return the k most frequent elements.
Example
Input
nums = [1,1,1,2,2,3]
k = 2
Output
[1,2]
The frequencies are:
1 → 3 times
2 → 2 times
3 → 1 time
Since k = 2, we need the two numbers with the highest frequencies.
Therefore:
[1,2]
Approach - HashMap + Sorting
I solved this problem using a HashMap and sorting.
The solution has three main steps:
Count the frequency of each number using a HashMap.
Put all unique numbers into a list.
Sort the numbers based on their frequencies and return the first k elements.
Solution
public int[] topKFrequent(int[] nums, int k) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int num : nums) {
        if (map.containsKey(num)) {
            map.put(num, map.get(num) + 1);
        } else {
            map.put(num, 1);
        }
    }

    List<Integer> keys = new ArrayList<>(map.keySet());

    keys.sort((a, b) -> map.get(b) - map.get(a));

    int[] answer = new int[k];

    for (int i = 0; i < k; i++) {
        answer[i] = keys.get(i);
    }

    return answer;
}
Step 1 - Count the Frequencies
First, I use a HashMap to count how many times each number appears.
Map<Integer, Integer> map = new HashMap<>();
The structure of the map is:
number → frequency
For example:
nums = [1,1,1,2,2,3]
After counting:
1 → 3
2 → 2
3 → 1
The code:
for (int num : nums) {
    if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
    } else {
        map.put(num, 1);
    }
}
If the number already exists in the map, I increase its frequency by one.
Otherwise, I add it to the map with a frequency of 1.
Step 2 - Get the Unique Numbers
Next, I take all the keys from the HashMap:
List<Integer> keys = new ArrayList<>(map.keySet());
For example, if the map is:
1 → 3
2 → 2
3 → 1
then:
keys = [1,2,3]
The important point is that the list contains the numbers themselves, not their frequencies.
Step 3 - Sort by Frequency
Now I sort the numbers based on their frequency in the HashMap.
keys.sort((a, b) -> map.get(b) - map.get(a));
This sorts the keys from the highest frequency to the lowest frequency.
For example:
50  → 3
7   → 2
100 → 1
After sorting:
keys = [50,7,100]
The numbers are not being sorted based on their actual values.
They are being sorted based on:
map.get(number)
which represents their frequency.
Understanding the Comparator
This line:
keys.sort((a, b) -> map.get(b) - map.get(a));
compares two numbers from the keys list.
For example:
a = 1
b = 2
From the map:
map.get(1) = 3
map.get(2) = 2
The comparator calculates:
map.get(b) - map.get(a)

2 - 3

= -1
A negative result means a should come before b.
Therefore, 1 comes before 2.
Using:
map.get(b) - map.get(a)
sorts the elements from highest frequency to lowest frequency.
Step 4 - Take the First K Elements
After sorting, the most frequent numbers are at the beginning of the list.
For example:
keys = [1,2,3]
k = 2
We only need:
1,2
So I create an array of size k:
int[] answer = new int[k];
Then copy the first k elements:
for (int i = 0; i < k; i++) {
    answer[i] = keys.get(i);
}
The result becomes:
answer = [1,2]
Finally:
return answer;
Full Walkthrough
For:
nums = [1,1,1,2,2,3]
k = 2
First, build the frequency map:
1 → 3
2 → 2
3 → 1
Then get the keys:
[1,2,3]
Sort the keys based on their frequencies:
1 → frequency 3
2 → frequency 2
3 → frequency 1
So the sorted list is:
[1,2,3]
Since:
k = 2
take the first two elements:
[1,2]
Final answer:
[1,2]
Complexity
Let n be the number of elements in nums, and m be the number of unique elements.
Building the HashMap takes:
O(n)
Sorting the unique elements takes:
O(m log m)
In the worst case, every number is unique:
m = n
So the overall time complexity can become:
O(n log n)
The space complexity is:
O(n)
because the HashMap and the list of unique elements can contain up to n elements.
What I Learned
How to count frequencies using a HashMap.
How to store data as number → frequency.
How to get all keys from a HashMap using map.keySet().
How to convert the keys into an ArrayList.
How to sort elements based on values stored in a HashMap.
How a custom comparator works in Java.
How to sort frequencies from highest to lowest.
How to return only the first k most frequent elements.
Why keeping the number and its frequency connected is easier than sorting the frequencies separately.
Pattern
Hashing + Sorting
The main idea is:
nums
  ↓
count frequencies
  ↓
HashMap<number, frequency>
  ↓
get unique numbers
  ↓
sort numbers by frequency
  ↓
take first k elements
  ↓
result
Note
This solution is simple and easy to understand, but it is not the most optimal solution because it uses sorting.
A more optimized approach can solve the problem in O(n) time using Bucket Sort.
