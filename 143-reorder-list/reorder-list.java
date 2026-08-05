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
    public void reorderList(ListNode head) {
        ListNode mid = findmid(head);
        ListNode th = mid.next;
        mid.next = null;
        th=reverse(th);
        ListNode head1 = head;
        ListNode t1 = head1.next;
        ListNode head2 = th;
        ListNode t2 = null;
        if(head2 != null)
            t2 = head2.next;
        while(head2 != null){
            head1.next = head2;
            head2.next = t1;
            head1 = t1;
            if(t1 != null)
                t1 = t1.next;
            head2 = t2;
            if(t2 != null)
                t2 = t2.next;
        }
    }
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode reversehead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversehead;
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
}