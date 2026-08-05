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
    public ListNode oddEvenList(ListNode head) {
        ListNode dod = new ListNode(-1);
        ListNode de = new ListNode(-1);
        ListNode to = dod;
        ListNode te = de;
        int index = 1;
        while(head != null){
            if(index % 2 == 0){
                te.next = head;
                te = head;
            }
            else{
                to.next = head;
                to = head;
            }
            head = head.next;
            index++;
        }
        te.next = null;
        to.next = de.next;
        return dod.next;
    }
}