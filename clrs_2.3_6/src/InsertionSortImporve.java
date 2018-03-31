/**
 * Created by Administrator on 2017/5/3.
 */
public class InsertionSortImporve {
    public static void main(String[] args) {
        int[] numbers = {3, 2, 7, 5, 6, 1, 4};
        insertionSort(numbers);
        for (int i : numbers) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] numbers) {
        for(int i=1;i<numbers.length;i++) {
            int temp = numbers[i];
            int index = lower_bound(numbers, 0, i - 1, temp);
            for(int j=i-1;j>=index;j--) {
                numbers[j + 1] = numbers[j];
            }
            numbers[index]=temp;
        }
    }
    public static int lower_bound(int[] numbers, int start, int end,int target) {
        int first=start,last=end;
        while (first <= last) {
            int mid=first+(last-first)/2;
            if (target > numbers[mid]) {
                first=mid+1;
            }
            else{
                last=mid-1;
            }
        }
        return first;
    }
}
