import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Administrator on 2017/5/3.
 */
public class MergeSort {
    public static void main(String[] args) {
        Integer[] numbers=new Integer[4_000_000];
        for(int i=0;i<numbers.length;i++)
            numbers[i]=i+1;
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(numbers));
        Collections.shuffle(list);
        list.toArray(numbers);
        int[] array = new int[numbers.length];
        for(int i=0;i<array.length;i++)
            array[i]=numbers[i];
        final int[] k_array = {1,10,20,50,80,100, 200, 300,500,800, 1000,1200,1500,1800, 2000, 3000, 5000, 10000};
        for (int k : k_array) {
            long startTime = System.currentTimeMillis();
            mergeSort(array, k);
            long endTime=System.currentTimeMillis();
            System.out.println(k+":"+(endTime-startTime));
        }
    }

    public static void mergeSort(int[] numbers,int k) {
        mergeSortImprove(numbers, 0, numbers.length - 1,k);
    }

    public static void mergeSort(int[] numbers, int begin, int end) {
        if (begin < end) {
            int mid=begin+(end-begin)/2;
            mergeSort(numbers, begin, mid);
            mergeSort(numbers, mid + 1, end);
            merge(numbers, begin, mid, end);
        }
    }
    public static void mergeSortImprove(int[] numbers, int begin, int end,int k) {
        if (end - begin + 1 <= k) {
            insertionSort(numbers, begin, end);
        }
        else{
            int mid=begin+(end-begin)/2;
            mergeSort(numbers, begin, mid);
            mergeSort(numbers, mid + 1, end);
            merge(numbers, begin, mid, end);
        }
    }

    public static void insertionSort(int[] numbers, int begin, int end) {
        for(int i=1;i<numbers.length;i++) {
            int temp = numbers[i];
            int j=i-1;
            while (j >= 0 && numbers[j] > temp) {
                numbers[j+1]=numbers[j];
                j--;
            }
            numbers[j+1]=temp;
        }
    }

    public static void merge(int[] numbers, int begin, int mid, int end) {
        int[] L = new int[mid - begin + 1+1];
        int[] R = new int[end - mid + 1];
        System.arraycopy(numbers, begin, L, 0, mid - begin + 1);
        System.arraycopy(numbers, mid + 1, R, 0, end - mid);
        L[L.length-1]=Integer.MAX_VALUE;
        R[R.length-1]=Integer.MAX_VALUE;
        int i=0,j=0;
        for(int k=begin;k<=end;k++) {
            if (L[i] <= R[j]) {
                numbers[k] = L[i++];
            }
            else
                numbers[k] = R[j++];
        }
    }

}
