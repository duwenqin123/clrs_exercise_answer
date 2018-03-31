/**
 * Created by Administrator on 2017/5/21.
 */
public class DoubleLinklistWithOnePointer {
    private ListNode tail,dummy;
    final int nil=0;
    public DoubleLinklistWithOnePointer(){
        dummy=new ListNode(-1);
        dummy.np=nil^nil;
        tail=dummy;
    }
    public void insert(int k){
        ListNode newNode=new ListNode(k);
        newNode.np=tail^nil;
    }
    class ListNode {
        int val;
        int np;
        ListNode(int x) { val = x; }
    }
}
