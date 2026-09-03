LeetCode 125 - Valid Palindrome
Problem
Given a string s, determine whether it is a valid palindrome.
A string is considered a palindrome after:
Converting uppercase letters to lowercase.
Ignoring all non-alphanumeric characters.
Keeping both letters and numbers.
A palindrome reads the same from left to right and from right to left.
Example
Input
s = "A man, a plan, a canal: Panama"
After converting to lowercase and ignoring spaces and symbols:
amanaplanacanalpanama
Output
true
Because the string reads the same forward and backward.
Approach - Two Pointers
I used the Two Pointers pattern.
One pointer starts from the beginning of the string:
left →
The other pointer starts from the end:
← right
So conceptually:
left →  A man, a plan, a canal: Panama  ← right
Before comparing the characters, we check whether they are letters or digits.
If the character at left is not alphanumeric:
left++;
continue;
If the character at right is not alphanumeric:
right--;
continue;
continue skips the rest of the current loop iteration and starts the next iteration.
This allows us to ignore characters such as:
space
,
:
!
@
#
without checking every possible symbol individually.
Handling Letters and Numbers
The problem considers both letters and numbers valid characters.
Java provides:
Character.isLetterOrDigit(character)
For example:
'A' → true
'7' → true
'!' → false
' ' → false
',' → false
This means we do not need to manually check every possible special character.
Handling Uppercase and Lowercase
Uppercase and lowercase letters should be treated as the same character.
For example:
'A' == 'a'
for the purpose of this problem.
I convert the entire string to lowercase before processing it:
char[] sCharArray = s.toLowerCase().toCharArray();
This makes the later character comparison simpler.
Solution
public boolean isPalindrome(String s) {

    char[] sCharArray = s.toLowerCase().toCharArray();

    int left = 0;
    int right = sCharArray.length - 1;

    while (left < right) {

        if (!Character.isLetterOrDigit(sCharArray[left])) {
            left++;
            continue;
        }

        if (!Character.isLetterOrDigit(sCharArray[right])) {
            right--;
            continue;
        }

        if (sCharArray[left] != sCharArray[right])
            return false;

        left++;
        right--;
    }

    return true;
}
Why continue Is Used
When one of the pointers is pointing to a non-alphanumeric character, we move that pointer.
For example:
if (!Character.isLetterOrDigit(sCharArray[left])) {
    left++;
    continue;
}
After moving left, we do not want to immediately compare the characters.
Instead, we want to go back to the beginning of the while loop and check the new character again.
That is why continue is useful here.
Invalid character
       ↓
Move pointer
       ↓
continue
       ↓
Start the next loop iteration
       ↓
Check again
What I Learned
How the Two Pointers pattern works.
One pointer can start from the beginning while another starts from the end.
Character.isLetterOrDigit() can be used to identify alphanumeric characters.
Numbers are also considered valid characters in this problem.
continue skips the remaining code in the current loop iteration and starts the next iteration.
String.toLowerCase() can simplify case-insensitive comparisons.
We do not need to manually list every special character.
Two Pointers can avoid creating a cleaned and reversed version of the string.
Complexity
Time Complexity
O(n)
Each pointer moves through the string at most once.
Space Complexity
O(n)
In this implementation, toLowerCase() and toCharArray() create additional data based on the input string.
A version that directly works with s.charAt() and converts individual characters when comparing them could reduce the auxiliary space.
Pattern
Two Pointers
The key idea is:
left →              ← right
Move both pointers toward the center while ignoring invalid characters and comparing valid characters.
