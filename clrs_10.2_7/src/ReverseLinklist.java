/**
 * Created by Administrator on 2017/5/21.
 */
public class ReverseLinklist {
    public static void main(String[] args) {

    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next=head;
        ListNode cur=head.next;
        while (cur != null) {
            ListNode tmp=cur.next;
            cur.next=dummy.next;
            dummy.next=cur;
            cur=tmp;
        }
        head.next=null;
        return dummy.next;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first=head,cur=head.next;
        while (cur != null) {
            ListNode tmp=cur.next;
            cur.next=first;
            first=cur;
            cur=tmp;
        }
        //easy wrong!
        head.next=null;
        return first;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode pre=null,cur=head;
        while (cur != null) {
            ListNode tmp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        return pre;
    }
    /*The recursive version is slightly trickier and the key is to work backwards. Assume that the
     rest of the list had already been reversed, now how do I reverse the front part? Let's assume
     the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     Assume from node nk+1 to nm had been reversed and you are at node nk.
    n1 → … → nk-1 → nk → nk+1 ← … ← nm
    We want nk+1’s next node to point to nk.
    So,
    nk.next.next = nk;
    Be very careful that n1's next must point to Ø. If you forget about this, your linked list has
    a cycle in it. This bug could be caught if you test your code with a linked list of size 2.*/
    public ListNode reverseList_recursive(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode p=reverseList_recursive(head.next);
        head.next.next=head;
        //easy wrong!
        head.next=null;
        return p;
    }
}
