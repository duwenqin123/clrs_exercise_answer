import com.sun.deploy.panel.ITreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
public class CountSort {
    public static void main(String[] args) {
//        int[][] numbers = {{3, 2, 9}, {4, 5, 7}, {6, 5, 7}, {8, 3, 9}, {4, 3, 6}, {7, 2, 0}, {3, 5, 5}};
        int[][] numbers={{2,2},{2,3},{1,4},{3,2},{1,1},{1,2},{3,1},{4,1},{0,0},{0,0},{4,2},{1,5}};
        radixSortMSD(numbers,10);
        for (int[] number : numbers) {
            for (int d : number) {
                System.out.print(d);
            }
            System.out.print(" ");
        }
    }

    public static void countSort(int[] nums,int k) {
        int[] array_copy = new int[nums.length];
        int[] positions = new int[k + 1];
        for (int d : nums) {
            positions[d]++;
        }
        for (int i = 1; i <= k; i++) {
            positions[i] += positions[i - 1];
        }
        for(int i=nums.length-1;i>=0;i--) {
            array_copy[positions[nums[i]]-1]=nums[i];
            positions[nums[i]]--;
        }
        System.arraycopy(array_copy, 0, nums, 0, nums.length);
    }

    public static void countSortCommon(int[][] nums, int pos,int k) {
        int[][] array_copy = new int[nums.length][nums[0].length];
        int[] positions = new int[k + 1];
        for (int[] d : nums) {
            positions[d[pos]]++;
        }
        for (int i = 1; i <= k; i++) {
            positions[i] += positions[i - 1];
        }
        for(int i=nums.length-1;i>=0;i--) {
            array_copy[positions[nums[i][pos]]-1]=nums[i];
            positions[nums[i][pos]]--;
        }
        System.arraycopy(array_copy, 0, nums, 0, nums.length);
    }
    public static void radixSort(int[][] nums,int radix){
        int d=nums[0].length;
        for(int i=d-1;i>=0;i--){
            countSortCommon(nums,i,radix-1);
        }
    }

    public static void radixSortMSD(int[][] nums,int radix) {
        int d=nums[0].length;
        radixSortMSD_common(nums,0,nums.length-1,0,radix);
    }
    public static void radixSortMSD_common(int[][] nums, int begin, int end, int pos, int radix) {
        if(pos>=nums[0].length || end<=begin)
            return;
        int[][] copys=new int[end-begin+1][end-begin+1];
        int[] count = new int[radix];
        for(int i=begin;i<=end;i++) {
            count[nums[i][pos]]++;
        }
        for (int i = 1; i < radix; i++) {
            count[i]+=count[i-1];
        }
        for(int i=end;i>=begin;i--) {
            copys[count[nums[i][pos]]-1]=nums[i];
            count[nums[i][pos]]--;
        }
        System.arraycopy(copys,0,nums,begin,end-begin+1);
        //注意下面代码
        for(int i=0;i<radix;i++) {
            if (i == radix-1) {
                radixSortMSD_common(nums,begin+count[i-1],end,pos+1,radix);
            }
            else
                radixSortMSD_common(nums,begin+count[i],begin+count[i+1]-1,pos+1,radix);
        }
    }

    public static int[] getBits(int num, int radix,int d) {
        int[] result = new int[d];
        for (int i =d - 1; i >= 0; i--) {
             result[i]=num%radix;
             num/=radix;
        }
        return result;
    }
    /*貌似下边做法没意义，因为排完了numbers并没有对nums进行排序*/
    public static void radixSortForNumbers(int[] nums, int radix,int d) {
        int[][] numbers=new int[nums.length][d];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = getBits(nums[i], radix, d);
        }
        radixSort(numbers,radix);
    }
}
