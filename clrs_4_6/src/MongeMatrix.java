import java.util.ArrayList;
import java.util.List;

public class MongeMatrix {
    /**
     * Created by Administrator on 2017/5/6.
     */
    static class Pair{
        int col;
        int val;

        public Pair(int col, int val) {
            this.col=col;
            this.val=val;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{37, 23, 22, 32}, {21, 6, 5, 10}, {53, 34, 30, 31}, {32, 13, 9, 6}, {43, 21, 15, 8}};
       // int[][] matrix = {{3, 6}, {7, 4}};
        Pair[] result = findLeftMinimums(matrix);
        for (Pair pair : result) {
            System.out.print(pair.val+" ");
        }
        System.out.println();
    }


    public static Pair[] findLeftMinimums(int[][] matrix) {
        Pair[] result = new Pair[matrix.length];
        findLeftMinimumsMain(matrix, 0, matrix.length - 1, 1, result);
        return result;
    }

    public static Pair find_minimum_of_an_array(int[] array, int first, int last) {
        int min=array[first],min_index=first;
        for(int i=first+1;i<=last;i++) {
            if (array[i] < min) {
                min=array[i];
                min_index=i;
            }
        }

        return new Pair(min_index, min);
    }
    public static void findLeftMinimumsMain(int[][] matrix, int firstRow, int lastRow,int gap,
                                            Pair[] result) {
        if (firstRow == lastRow) {
            result[firstRow] = find_minimum_of_an_array(matrix[firstRow], 0, matrix[firstRow].length - 1);
            return;
        }
        int last=firstRow;
        int new_gap=2*gap;
        while(last<=lastRow)
            last+=new_gap;
        last-=new_gap;
        findLeftMinimumsMain(matrix, firstRow, last, new_gap, result);
        for(int i=firstRow+gap;i<=lastRow;i+=new_gap) {
            int begin=result[i-gap].col;
            int end=(i==lastRow)?matrix[i].length-1:result[i+gap].col;
            result[i] = find_minimum_of_an_array(matrix[i], begin, end);
        }
    }
}
