package 数据结构与算法.离职后刷题.第四遍必会版;

/**
 * 要求：只能在原矩阵上操作
 * 思路：先转置再旋转每一行，很简单
 */
public class demo40_2将矩阵顺时针旋转90度 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;//行数 = 列数
        //1.先转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //交换
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //2.再反转每一行即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {//易知，只需遍历到n/2即可
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

    }

    //自写一遍
    public void rotate02(int[][] matrix) {
        int len = matrix.length;
        //1.先转置，两层循环
        for (int i = 0; i < len; i++) {
            // j = i，即每次都是以对角线元素为标准/交换线，
            // 交换其水平和垂直方向上的元素
            for (int j = i; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }

        }

        //2.再对每一行的元素进行对调即可
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }

    }
}
