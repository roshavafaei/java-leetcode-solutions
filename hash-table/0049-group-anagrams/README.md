LeetCode 49 - Group Anagrams
Problem
Given an array of strings strs, group the anagrams together.
Two words are anagrams if they contain the same characters with the same frequencies, but possibly in a different order.
Example
Input
["eat", "tea", "tan", "ate", "nat", "bat"]
Output
[
  ["eat", "tea", "ate"],
  ["tan", "nat"],
  ["bat"]
]
The order of the groups does not matter.
Main Idea
The key observation is that all anagrams become the same string after sorting their characters.
For example:
eat -> aet
tea -> aet
ate -> aet
So these three words belong to the same group.
Another example:
tan -> ant
nat -> ant
These two words belong to another group.
This means the sorted version of each word can be used as a key in a HashMap.
HashMap Structure
I used:
Map<String, List<String>> map = new HashMap<>();
The HashMap stores:
sorted word -> list of original words
For example:
"aet" -> ["eat", "tea", "ate"]

"ant" -> ["tan", "nat"]

"abt" -> ["bat"]
Each unique sorted string has its own list.
Sorting Each Word
A String cannot be directly sorted using Arrays.sort().
First, I convert the String into a char[]:
char[] chars = strs[i].toCharArray();
Then I sort the character array:
Arrays.sort(chars);
After that, I convert it back into a String:
String sortedString = new String(chars);
For example:
"tea"

↓

['t', 'e', 'a']

↓

['a', 'e', 't']

↓

"aet"
Creating a Group
If the sorted word does not already exist in the HashMap:
if (!map.containsKey(sortedString))
    map.put(sortedString, new ArrayList<>());
we create a new empty list for that key.
For example:
"aet" -> []
Then the original word is added to the list:
map.get(sortedString).add(strs[i]);
So:
"aet" -> ["eat"]
If another word has the same sorted form, we do not create a new list.
For example:
"tea" -> "aet"
Since "aet" already exists:
"aet" -> ["eat", "tea"]
Example Walkthrough
Input:
["eat", "tea", "tan", "ate", "nat", "bat"]
Step 1
word = "eat"
sorted = "aet"
"aet" does not exist yet, so create a new list:
"aet" -> []
Add "eat":
"aet" -> ["eat"]
Step 2
word = "tea"
sorted = "aet"
"aet" already exists.
Add "tea":
"aet" -> ["eat", "tea"]
Step 3
word = "tan"
sorted = "ant"
Create a new group:
"ant" -> ["tan"]
Step 4
word = "ate"
sorted = "aet"
Add it to the existing "aet" group:
"aet" -> ["eat", "tea", "ate"]
Step 5
word = "nat"
sorted = "ant"
Add it to the "ant" group:
"ant" -> ["tan", "nat"]
Step 6
word = "bat"
sorted = "abt"
Create a new group:
"abt" -> ["bat"]
Final HashMap
"aet" -> ["eat", "tea", "ate"]

"ant" -> ["tan", "nat"]

"abt" -> ["bat"]
The problem does not need the keys.
It only needs the grouped lists.
So we return:
return new ArrayList<>(map.values());
Solution
public List<List<String>> groupAnagrams(String[] strs) {

    Map<String, List<String>> map = new HashMap<>();

    for (int i = 0; i < strs.length; i++) {

        char[] chars = strs[i].toCharArray();
        Arrays.sort(chars);

        String sortedString = new String(chars);

        if (!map.containsKey(sortedString))
            map.put(sortedString, new ArrayList<>());

        map.get(sortedString).add(strs[i]);
    }

    return new ArrayList<>(map.values());
}
Why map.values()?
The HashMap contains:
key -> value
For this problem:
"aet" -> ["eat", "tea", "ate"]
The key is only used for grouping.
The final answer only needs the lists.
So:
map.values()
gives us:
[
  ["eat", "tea", "ate"],
  ["tan", "nat"],
  ["bat"]
]
Since the method must return a List<List<String>>, we convert the values into an ArrayList:
return new ArrayList<>(map.values());
Complexity
Let:
n = number of strings
k = average length of each string
Sorting one string takes approximately:
O(k log k)
We do this for every string.
Time Complexity
O(n * k log k)
Space Complexity
O(n * k)
The HashMap stores all of the strings grouped into lists.
What I Learned
How to use a sorted string as a HashMap key.
Why anagrams produce the same result after sorting.
How to convert a String to a char[].
How to sort characters using Arrays.sort().
How to convert a char[] back into a String using:
new String(chars)
How to use a HashMap where each value is a List<String>.
How to create a new list only when a key does not already exist.
How to add values to an existing list inside a HashMap.
How to retrieve all HashMap values using:
map.values()
How hashing can be used to group related values.
Pattern
Hashing / Grouping
The main idea is:
word
  ↓
sort characters
  ↓
use sorted word as key
  ↓
store original word in the list for that key
Example:
eat ─┐
tea ─┼─> aet -> ["eat", "tea", "ate"]
ate ─┘

tan ─┐
nat ─┴─> ant -> ["tan", "nat"]

bat ───> abt -> ["bat"]
