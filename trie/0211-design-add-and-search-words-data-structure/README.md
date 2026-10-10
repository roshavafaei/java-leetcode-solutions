LeetCode 211 – Design Add and Search Words Data Structure
Difficulty: Medium
Language: Java
Data Structure: Trie (Prefix Tree)
Algorithm: Depth-First Search (DFS), Recursion, Backtracking
Problem Description
Design a data structure that supports two operations:
addWord(String word): Adds a word to the dictionary.
search(String word): Returns true if the word matches a previously added word.
The special character . can match any single letter.
Example
WordDictionary dictionary = new WordDictionary();
dictionary.addWord("bad");
dictionary.addWord("dad");
dictionary.addWord("mad");
dictionary.search("pad"); // false
dictionary.search("bad"); // true
dictionary.search(".ad"); // true
dictionary.search("b.."); // true
Approach: Trie + Recursive DFS
We use a Trie to store words efficiently.
Each Trie node contains:
children: A HashMap mapping characters to child nodes.
isEndOfWord: A boolean indicating whether a complete word ends at this node.
1. Adding Words
Start from the root and process each character.
Check whether the current node has a child for the character.
If not, create a new node.
Move to that child.
After processing all characters, mark the final node as the end of a word.
2. Searching Words
There are two cases:
Case 1: Normal character
If the character is not ., follow the corresponding child.
If that child doesn’t exist, return false.
Otherwise, recursively search the next character.
if (ch != '.') {
    if (!current.hasChild(ch))
        return false;
    return searchRecursive(
        current.getChild(ch), word, index + 1
    );
}
Case 2: Wildcard .
The dot can represent any character.
Therefore, we must try all children of the current node.
for (Node child : current.children.values()) {
    if (searchRecursive(child, word, index + 1))
        return true;
}
return false;
If any child produces a successful match, return true.
If every possible path fails, return false.
We use recursion because each wildcard can create multiple possible paths.
Java Solution
import java.util.HashMap;
import java.util.Map;
class WordDictionary {
    private class Node {
        private char value;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;
        public Node(char value) {
            this.value = value;
        }
        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }
        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }
        public Node getChild(char ch) {
            return children.get(ch);
        }
    }
    private Node root = new Node(' ');
    public WordDictionary() {
    }
    public void addWord(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }
    public boolean search(String word) {
        return searchRecursive(root, word, 0);
    }
    private boolean searchRecursive(
            Node current, String word, int index) {
        if (index == word.length())
            return current.isEndOfWord;
        char ch = word.charAt(index);
        if (ch != '.') {
            if (!current.hasChild(ch))
                return false;
            return searchRecursive(
                current.getChild(ch), word, index + 1
            );
        }
        for (Node child : current.children.values()) {
            if (searchRecursive(child, word, index + 1))
                return true;
        }
        return false;
    }
}
Dry Run
Suppose the Trie contains:
root
 ├── b ── a ── d*
 ├── d ── a ── d*
 └── m ── a ── d*
* indicates the end of a word.
Search:
search(".ad");
Start at root, index = 0.
The first character is ., so try the root’s children.
Suppose we choose b.
Recursively search from node b, with index = 1.
The next character is a, so move to child a.
The next character is d, so move to child d.
index == word.length() and isEndOfWord == true.
Return true.
The successful result propagates back through the recursive calls.
Important: If the b path failed, the algorithm would continue checking other children instead of immediately returning false.
Complexity Analysis
Let:
L = length of the word.
N = number of nodes in the Trie.
C = maximum number of children per node (26 for lowercase English letters).
Operation Time Complexity
addWord() O(L)
search() without dots O(L)
search() with wildcards O(C^L) worst case
Space Complexity:
Adding a word: O(L) additional space in the worst case.
Recursive search: O(L) auxiliary call-stack space.
Trie storage: O(N).
Key Takeaways
A Trie stores words character by character.
Normal characters follow one specific path.
The wildcard . requires exploring multiple possible paths.
Recursive DFS allows us to explore these paths.
A failed branch does not mean the entire search has failed.
We return true as soon as one complete matching word is found.
isEndOfWord ensures that we match complete words, not just prefixes.
Pattern learned: Trie + DFS + Backtracking.
