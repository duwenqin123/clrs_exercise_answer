import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */
public class YountMatrix {
    public static void main(String[] args) {
        int[] elements = {3, 2, 4, 1,7,6,9,8,5};
//        YoungSort(elements);
//        for (int d : elements) {
//            System.out.print(d+" ");
//        }
//        System.out.println();
        YountMatrix matrixs=new YountMatrix(elements);
        System.out.println(exist(matrixs,10));
        matrixs.remove();
        for (int i = 0; i < matrixs.matrix.length; i++) {
            for (int j = 0; j < matrixs.matrix[0].length; j++) {
                System.out.print(matrixs.matrix[i][j]+" ");
            }
        }
    }

    public static boolean exist(YountMatrix yountMatrix,int val) {
        int[][] matrix=yountMatrix.matrix;
        int i=0,j=matrix[0].length-1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] > val) {
                j--;
            } else if (matrix[i][j] < val) {
                i++;
            }
            else
                return true;
        }
        return false;
    }
    public static void YoungSort(int[] elements) {
        YountMatrix matrixs = new YountMatrix(elements);
        for (int i = 0; i < elements.length; i++) {
            elements[i]=matrixs.remove();
        }
    }
    private int[][] matrix;

    public YountMatrix() {

    }

    public YountMatrix(int[] elements) {
        int n = (int)Math.ceil(Math.sqrt(elements.length));
        matrix = new int[n][n];
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j]=Integer.MAX_VALUE;
            }
        }
        for (int d : elements) {
            insert(d);
        }
    }

    public void insert(int val) {
        int r=matrix.length-1,c=matrix[0].length-1;
        while (r > 0 && c > 0) {
            if (Math.max(matrix[r - 1][c], matrix[r][c - 1]) > val) {
                if (matrix[r - 1][c] >= matrix[r][c - 1]) {
                    matrix[r][c] = matrix[r - 1][c];
                    r--;
                }
                else{
                    matrix[r][c]=matrix[r][c-1];
                    c--;
                }
            }
            else{
                matrix[r][c]=val;
                return;
            }
        }
        if(r==0){
            int k=c-1;
            while (k >= 0 && matrix[0][k] > val) {
                matrix[0][k+1]=matrix[0][k];
                k--;
            }
            matrix[0][k+1]=val;
        }
        if (c == 0) {
            int k=r-1;
            while (k >= 0 && matrix[k][0] > val) {
                matrix[k + 1][0] = matrix[k][0];
                k--;
            }
            matrix[k+1][0]=val;
        }
    }

    public int remove() {
        int min=matrix[0][0];
        int r=0,c=0;
        int m=matrix.length,n=matrix[0].length;
        while (r < m-1 && c < n-1) {
            if (matrix[r][c + 1] <= matrix[r + 1][c]) {
                matrix[r][c] = matrix[r][c + 1];
                c++;
            }
            else{
                matrix[r][c]=matrix[r+1][c];
                r++;
            }
        }
        if (r == m-1) {
            for(int k=c+1;k<n;k++) {
                matrix[r][k-1]=matrix[r][k];
            }
            matrix[m-1][n-1]=Integer.MAX_VALUE;
        }
        if (c == n - 1) {
            for(int k=r+1;k<m;k++) {
                matrix[k-1][c]=matrix[k][c];
            }
            matrix[m-1][n-1]=Integer.MAX_VALUE;
        }
        return min;
    }

}
