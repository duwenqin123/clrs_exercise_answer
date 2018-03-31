import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/5/13.
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        System.out.println(Integer.compare(5, 4));
    }
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode mergeKLists1(ListNode[] lists) {
        if(lists==null || lists.length==0)
            return null;
        PriorityQueue<ListNode> queue=new PriorityQueue<>(lists.length,(ListNode p,ListNode q)->{
            return Integer.compare(p.val, q.val);
        });
        for (ListNode listNode : lists) {
            if(listNode!=null)
                queue.offer(listNode);
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre=dummy;
        while (!queue.isEmpty()) {
            ListNode cur=queue.remove();
            pre.next=cur;
            pre=cur;
            if(cur.next!=null)
                queue.offer(cur.next);
        }
        return dummy.next;
    }
}
