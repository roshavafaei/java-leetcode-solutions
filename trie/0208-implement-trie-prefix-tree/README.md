LeetCode 208 - Implement Trie (Prefix Tree)
Problem
Implement a Trie (Prefix Tree) that supports the following operations:
insert(word) - Inserts a word into the Trie.
search(word) - Returns true if the complete word exists in the Trie.
startsWith(prefix) - Returns true if there is any word in the Trie that starts with the given prefix.
What is a Trie?
A Trie is a tree data structure used for storing strings.
Each node represents a character.
For example, if we insert:
apple
app
The Trie looks like:
root
 |
 a
 |
 p
 |
 p  ← End of "app"
 |
 l
 |
 e  ← End of "apple"
We use isEndOfWord to determine whether a node represents the end of a complete word.
Approach
Each node contains:
A character value
Its children
A boolean isEndOfWord
The root node does not represent an actual character.
root → characters → ... → end of word
Insert
To insert a word, start from the root and process every character.
For each character:
Check if the current node already has that child.
If it does not exist, create it.
Move current to that child.
After processing all characters, mark the last node as the end of a word.
public void insert(String word) {
    Node current = root;

    for (char ch : word.toCharArray()) {
        if (!current.hasChild(ch))
            current.addChild(ch);

        current = current.getChild(ch);
    }

    current.isEndOfWord = true;
}
Example:
insert("cat")

root
 |
 c
 |
 a
 |
 t
 *
* means:
isEndOfWord = true
Finding the Last Node
Both search() and startsWith() need to perform the same traversal.
Instead of duplicating that code, we create a helper method:
private Node findLastNodeOf(String str) {
    Node current = root;

    for (char ch : str.toCharArray()) {
        if (!current.hasChild(ch))
            return null;

        current = current.getChild(ch);
    }

    return current;
}
This method follows the characters of the given string through the Trie.
If the path does not exist:
return null
If the entire path exists:
return current
where current is the node corresponding to the last character.
For example:
Trie contains: apple

findLastNodeOf("app")
returns the second p node:
root
 |
 a
 |
 p
 |
 p  ← returned node
 |
 l
 |
 e
Search
search() checks whether a complete word exists.
First, find the last node:
Node node = findLastNodeOf(word);
Then two conditions must be true:
1. The path exists.
2. The last node is marked as EndOfWord.
Implementation:
public boolean search(String word) {
    Node node = findLastNodeOf(word);

    return node != null && node.isEndOfWord;
}
Example:
If the Trie contains:
apple
Then:
search("apple") → true
search("app")   → false
app exists as a path, but its last p is not necessarily the end of a complete word.
startsWith
startsWith() only checks whether the given prefix exists as a path.
It does not care about isEndOfWord.
public boolean startsWith(String prefix) {
    Node node = findLastNodeOf(prefix);

    return node != null;
}
Example:
If the Trie contains:
apple
Then:
startsWith("app") → true
startsWith("ap")  → true
startsWith("apple") → true
startsWith("xyz") → false
The prefix does not need to be a complete word.
Search vs startsWith
This is the main difference:
search()
    path must exist
    +
    last node must be EndOfWord

startsWith()
    path only needs to exist
For example:
Trie contains: "apple"

                 search()     startsWith()

"apple"             true          true
"app"               false         true
"ap"                false         true
"xyz"               false         false
Why Use findLastNodeOf?
Without the helper method, both search() and startsWith() would contain almost identical traversal code.
Instead of repeating:
Node current = root;

for (char ch : str.toCharArray()) {
    if (!current.hasChild(ch))
        return ...;

    current = current.getChild(ch);
}
we move this responsibility into:
findLastNodeOf()
Now each method has a clear responsibility:
findLastNodeOf()
        ↓
Find the path and return its last node.

search()
        ↓
Does the path exist AND represent a complete word?

startsWith()
        ↓
        Does the path exist?
This avoids duplicated code and makes the implementation easier to maintain.
Complexity
Let L be the length of the input word or prefix.
Insert
Time:  O(L)
Search
Time:  O(L)
startsWith
Time:  O(L)
We only need to process each character once.
Key Takeaways
A Trie stores strings character by character.
isEndOfWord distinguishes a complete word from a prefix.
search() requires isEndOfWord = true.
startsWith() only requires the path to exist.
findLastNodeOf() contains the shared traversal logic.
Extracting shared logic into a helper method avoids code duplication.
