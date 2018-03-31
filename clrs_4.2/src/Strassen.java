/**
 * Created by Administrator on 2017/5/5.
 */
public class Strassen {
    public static void printMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
//        int[][] matrix1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
//        int[][] matrix2 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
//        int[][] matrix1 = {{1, 3}, {7, 5}};
//        int[][] matrix2 = {{6, 8}, {4, 2}};
       // matrix_copy(matrix1,matrix2,0,0);
       // System.out.println(matrix2);
        for(int count=0;count<1;count++){
            final int m1=6,n1=6;
            final int m2=6,n2=6;
            int[][] matrix1 = new int[m1][n1];
            int[][] matrix2 = new int[m2][n2];
//            int[][] matrix1 = {{1, 1, 1, 0}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 0, 1, 0}};
//            int[][] matrix2 = {{1, 1, 1, 0}, {0, 1, 0, 0}, {1, 0,0,0}, {0, 0, 0, 0}};

            for(int i=0;i<matrix1.length;i++) {
                for(int j=0;j<matrix1[0].length;j++) {
                    matrix1[i][j]=(int)(Math.random()*2);
                    matrix2[i][j]=(int)(Math.random()*2);
//                    matrix1[i][j]=2;
//                    matrix2[i][j]=1;
                }
            }

            int[][] result=StrassenBrute(matrix1, matrix2);
            int[][] result1 = StrassenCount(matrix1, matrix2);
            boolean isEqual=true;
            for(int i=0;i<result.length;i++) {
                for(int j=0;j<result[0].length;j++) {
                    if(result[i][j]!=result1[i][j]){
                        isEqual=false;
                        break;
                    }
                }
            }
            if (!isEqual) {
                printMatrix(matrix1);
                printMatrix(matrix2);
                printMatrix(result);
                printMatrix(result1);
            }
        }
    }

    public static int[][] StrassenBrute(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for(int i=0;i<result.length;i++) {
            for(int j=0;j<result[0].length;j++) {
                for(int k=0;k<matrix1[0].length;k++) {
                    result[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return result;
    }
    public static int[][] StrassenCount(int[][] matrix1, int[][] matrix2) {
        int m=matrix1.length;
        int n=1;
        while (n < m) {
            n<<=1;
        }
        if(n>m) {
            int[][] matrix1_new=new int[n][n];
            int[][] matrix2_new=new int[n][n];
           for(int i=0;i<m;i++) {
                for(int j=0;j<m;j++) {
                    matrix1_new[i][j]=matrix1[i][j];
                    matrix2_new[i][j]=matrix2[i][j];
                }
            }
            /*
            for (int i = 0; i < m; i++) {
                for(int j=m;j<n;j++) {
                    matrix1_new[i][j]=0;
                    matrix2_new[i][j]=0;
                    matrix1_new[j][i]=0;
                    matrix2_new[j][i]=0;
                }
            }
            for(int i=m;i<n;i++) {
                for(int j=m;j<n;j++) {
                    matrix1_new[i][j]=0;
                    matrix2_new[i][j]=0;
                }
            }*/
//            matrix1_new;
//            matrix2=matrix2_new;
//            m=n;
            int[][] temp=Strassen_main(matrix1_new, 0, 0, matrix2_new, 0, 0, n);
            int[][] result=new int[m][m];
            for(int i=0;i<m;i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j]=temp[i][j];
                }
            }
            return result;
        }
        else
            return Strassen_main(matrix1, 0, 0, matrix2, 0, 0, m);
    }

    public static int[][] Strassen_main(int[][] matrix1,int row_begin,int col_begin, int[][] matrix2, int row_begin2,int col_begin2,int size) {
        int[][] result=new int[size][size];
        if(size==1){
            result[0][0]=matrix1[row_begin][col_begin]*matrix2[row_begin2][col_begin2];
        }
        else{
            int half_size=size/2;
            int[][] S1 = matrix_add(matrix2, row_begin2,col_begin2+half_size,matrix2,row_begin2+half_size,col_begin2+half_size,half_size,true);
            int[][] S2 = matrix_add(matrix1, row_begin, col_begin,matrix1, row_begin, col_begin+half_size,half_size,false);
            int[][] S3 = matrix_add(matrix1, half_size+row_begin, col_begin,matrix1, half_size+row_begin, half_size+col_begin,half_size,false);
            int[][] S4 = matrix_add(matrix2,row_begin2+half_size, col_begin2, matrix2,row_begin2, col_begin2,half_size,true);
            int[][] S5 = matrix_add(matrix1, row_begin, col_begin,matrix1, half_size+row_begin,half_size+col_begin,half_size,false);
            int[][] S6 = matrix_add(matrix2, row_begin2, col_begin2,matrix2,row_begin2+half_size,col_begin2+half_size,half_size,false);
            int[][] S7 = matrix_add(matrix1, row_begin, half_size+col_begin,matrix1, half_size+row_begin, half_size+col_begin,half_size,true);
            int[][] S8 = matrix_add(matrix2,row_begin2+half_size, col_begin2,matrix2,row_begin2+half_size,col_begin2+half_size,half_size,false);
            int[][] S9 = matrix_add(matrix1, row_begin, col_begin,matrix1, half_size+row_begin,col_begin,half_size,true);
            int[][] S10 = matrix_add(matrix2, row_begin2, col_begin2,matrix2, row_begin2,col_begin2+half_size,half_size,false);

            int[][] P1 = Strassen_main(matrix1, row_begin, col_begin, S1,0,0, half_size);
            int[][] P2 = Strassen_main(S2, 0, 0,matrix2, row_begin2 + half_size, col_begin2 + half_size, half_size);
            int[][] P3 = Strassen_main( S3, 0, 0, matrix2, row_begin2, col_begin2,half_size);
            int[][] P4 = Strassen_main(matrix1, row_begin + half_size, col_begin + half_size,
                    S4, 0, 0, half_size);
            int[][] P5 = Strassen_main(S5, 0, 0, S6, 0, 0, half_size);
            int[][] P6 = Strassen_main(S7, 0, 0, S8, 0, 0, half_size);
            int[][] P7 = Strassen_main(S9, 0, 0, S10, 0, 0, half_size);

            int[][] C11=matrix_add(matrix_add(P5,0,0,P4,0,0,half_size,
                    false),0,0,matrix_add(P6,0,0,P2,0,0,half_size,
                    true),0,0,half_size,false);
            int[][] C12 = matrix_add(P1, 0, 0, P2, 0, 0, half_size, false);
            int[][] C21 = matrix_add(P3, 0, 0, P4, 0, 0, half_size, false);
            int[][] C22=matrix_add(matrix_add(P5,0,0,P1,0,0,half_size,
                    false),0,0,matrix_add(P3,0,0,P7,0,0,half_size,
                    false),0,0,half_size,true);
            matrix_copy(C11, result, 0, 0);
            matrix_copy(C12,result,0,half_size);
            matrix_copy(C21,result,half_size,0);
            matrix_copy(C22,result,half_size,half_size);

        }
        return result;
    }

    public static void matrix_copy(int[][] matrix1, int[][] matrix2, int row_begin, int col_begin) {
        for(int i=0;i<matrix1.length;i++) {
            for(int j=0;j<matrix1[0].length;j++) {
                matrix2[row_begin+i][col_begin+j]=matrix1[i][j];
            }
        }
    }
    public static int[][] matrix_add(int[][] matrix, int row_begin1,int col_begin1,int[][] matrix2,
            int row_begin2,int col_begin2,int size,boolean substract) {
        int[][] result=new int[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(!substract)
                    result[i][j]=matrix[i+row_begin1][j+col_begin1]+matrix2[i+row_begin2][j+col_begin2];
                else
                    result[i][j]=matrix[i+row_begin1][j+col_begin1]-matrix2[i+row_begin2][j+col_begin2];
            }
        }
        return result;
    }
}
