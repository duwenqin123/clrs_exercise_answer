import com.sun.corba.se.spi.ior.iiop.MaxStreamFormatVersionComponent;

/**
 * Created by Administrator on 2017/5/21.
 */
public class StaticList {
    public static void main(String[] args) {
        StaticList list=new StaticList();
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        list.remove(3);
        list.print();
    }
    private static final int maxSize=100;
    private LinkNode[] elems;
    private int avil;
    public StaticList(){
        elems=new LinkNode[100];
        //不要忘了下面这部分
        for (int i = 0; i < 100; i++) {
            elems[i]=new LinkNode();
        }
        elems[0].link=-1;
        avil=1;
        for(int i=1;i<maxSize-1;i++ ) {
            elems[i].link=i+1;
        }
        elems[maxSize-1].link=-1;
    }
    int search(int k){
        int cur=elems[0].link;
        while (cur != -1 && elems[cur].val != k) {
            cur=elems[cur].link;
        }
        return cur;
    }

    boolean insert(int k) {
        if(avil==-1)
            return false;
        int q=avil;
        avil=elems[avil].link;
        elems[q].val=k;
        elems[q].link=-1;
        int p=0;
        while (elems[p].link != -1) {
            p=elems[p].link;
        }
        elems[p].link=q;
        return true;
    }

    boolean remove(int k) {
        int pre=0,cur=elems[0].link;
        while (cur != -1 && elems[cur].val != k) {
            pre=cur;
            cur=elems[cur].link;
        }
        if (cur != -1) {
            elems[pre].link=elems[cur].link;
            elems[cur].link=avil;
            avil=cur;
            return true;
        }
        return false;
    }

    void print() {
        int cur=elems[0].link;
        while (cur != -1) {
            System.out.print(elems[cur].val+" ");
            cur=elems[cur].link;
        }
        System.out.println();
    }
}
class LinkNode{
    int val;
    int link;

    LinkNode() {

    }
}
