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
        if(head == null || head.next == null)
            return head;
        ListNode mid = findmid(head);
        ListNode th = mid.next;
        mid.next = null;
        return merge(sortList(head),sortList(th));
    }
    private ListNode findmid(ListNode head){
        ListNode s = head;
        ListNode f = head;
        while(f.next != null && f.next.next != null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    private ListNode merge(ListNode head1, ListNode head2){
        ListNode D = new ListNode(-1);
        ListNode t = D;
        while(head1 != null && head2 != null){
            if(head1.val < head2.val){
                t.next = head1;
                head1 = head1.next;
            }
            else{
                t.next = head2;
                head2 = head2.next;
            }
            t = t.next;
        }
        if(head1 == null)
            t.next = head2;
        else
            t.next = head1;
        return D.next;
    }
}