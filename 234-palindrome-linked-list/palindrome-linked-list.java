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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode mid = findmid(head);
        ListNode th = mid.next;
        mid.next = null;
        th = reverse(th);
        while(th != null){
            if(head.val != th.val)
                return false;
            head = head.next;
            th = th.next;
        }
        return true;
    }
    private ListNode findmid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode reversehead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversehead;
    }
}