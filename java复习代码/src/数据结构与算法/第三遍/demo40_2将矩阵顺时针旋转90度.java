package 数据结构与算法.第三遍;

/**
 * 要求：只能在原矩阵上操作
 * 思路：先转置再旋转每一行，很简单
 */
public class demo40_2将矩阵顺时针旋转90度 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;//行数 = 列数
        //1.先转置
        for (int i = 0;i<n;i++){
            for (int j = i;j<n;j++){
                //交换
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //2.再反转每一行即可
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n/2;j++){//易知，只需遍历到n/2即可
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
    }
}
