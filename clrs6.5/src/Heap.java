import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/13.
 */
public class Heap<E extends Comparable<E>> {
    public static void main(String[] args) {
        Integer[] elements = {4,1,3,2,16,9,10,14,8,7};
        //Heap<Integer> heap=new Heap<>(elements);
        heapSort(elements);
        for (Integer d : elements) {
            System.out.print(d+" ");
        }
        System.out.println();
    }

    public static void heapSort(Integer[] elements) {
        Heap<Integer> heap = new Heap<>(elements);
        for(int i=elements.length-1;i>=0;i--) {
            elements[i]=heap.heapRemoveMax();
        }
    }
    private ArrayList<E> list = new ArrayList<>();

    public Heap() {

    }

    public Heap(E[] objects) {
        for (E object : objects) {
            list.add(object);
        }
        for(int i=list.size()/2-1;i>=0;i--) {
            maxHeapIfy(i);
        }
    }

    private void maxHeapIfy(int i) {
        //easy wrong!
        if(list.isEmpty())
            return;
        E key=list.get(i);
        while (true) {
            int left=2*i+1;
            int right=2*i+2;
            int largest=i;
            E maxValue=key;
            if (left < list.size() && list.get(left).compareTo(key)>0) {
                largest=left;
                maxValue = list.get(left);
            }
            if (right < list.size() && list.get(right).compareTo(maxValue) > 0) {
                largest=right;
                maxValue = list.get(right);
            }
            if(i==largest) {
                list.set(i, key);
                return;
            }
            list.set(i, maxValue);
            i=largest;
        }
    }

    public E heapMax() {
        return list.get(0);
    }

    public E heapRemoveMax() {
        E max = heapMax();
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        maxHeapIfy(0);
        return max;
    }

    public void heapInsert(E element) {
        list.add(element);
        int i=list.size()-1;
        while (i > 0 && list.get((i-1)/2).compareTo(element) > 0) {
            list.set(i, list.get((i - 1) / 2));
            i=(i-1)/2;
        }
        list.set(i, element);
    }
}
