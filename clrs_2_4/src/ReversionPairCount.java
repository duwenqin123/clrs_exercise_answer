/**
 * Created by Administrator on 2017/5/4.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ReversionPairCount {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int[] numbers = new int[n];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i]=input.nextInt();
        }
        System.out.println(mergeSortCount(numbers));
    }

    public static int mergeSortCount(int[] numbers) {
        return mergeSortCount(numbers, 0, numbers.length - 1);
    }

    public static int mergeSortCount(int[] numbers, int begin, int end) {
        int result=0;
        if (begin < end) {
            int mid=begin+(end-begin)/2;
            result+=mergeSortCount(numbers, begin, mid);
            result+=mergeSortCount(numbers, mid + 1, end);
            result+=merge(numbers, begin, mid, end);
        }
        return result;
    }




    public static int merge(int[] numbers, int begin, int mid, int end) {
        int count=0;
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
            else {
                count+=L.length-1-i;
                numbers[k] = R[j++];
            }
        }
        return count;
    }

}
