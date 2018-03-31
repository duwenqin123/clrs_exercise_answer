import java.util.Stack;

/**
 * Created by Administrator on 2017/5/21.
 */
public class QueueByTwoStack<T>{
    private Stack<T> s1=new Stack<T>();
    private Stack<T> s2=new Stack<T>();

    QueueByTwoStack() {
    }
    public void enqueue(T x){
        s1.push(x);
    }
    public T dequeue(){
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public static void main(String[] args) {
        QueueByTwoStack<Integer> queue=new QueueByTwoStack<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
