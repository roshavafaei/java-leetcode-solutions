/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        
        if (head == null || head.next == null)
            return head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        ListNode first;
        ListNode last;

        if (left.val <= right.val) {
            first = left;
            last = left;
            left = left.next;
        }
        else {
            first = right;
            last = right;
            right = right.next;
        }
        
        while (left != null && right != null) {
            if (left.val <= right.val) {
                last.next = left;
                last = left;
                left = left.next;
            }
            
            else {
                last.next = right;
                last = right;
                right = right.next;
            }
        }
        
        if (left != null)
            last.next = left;
        
        else last.next = right;
        
        return first;
    }
}
