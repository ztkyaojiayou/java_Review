package 数据结构与算法.剑指offer第一版.数组;

import java.util.ArrayList;

/**
 * （简单）题目：输入一个矩阵，按照从外向里以顺时针的顺序依次扫印出每一个数字
 * 注意：一个矩阵很明显是一个二维数组
 *
 *分析：开宗明义，因为是顺时针遍历，因此先从左到最右，再从上到最下，再从右到最左，最后从下到最上即可
 *
 * 简单来说，就是不断地收缩矩阵的边界
 * 定义四个变量代表范围，up、down、left、right
 *
 * 向右走存入整行的值，当存入后，该行再也不会被遍历，代表上边界的 up 加一，同时判断是否和代表下边界的 down 交错
 * 向下走存入整列的值，当存入后，该列再也不会被遍历，代表右边界的 right 减一，同时判断是否和代表左边界的 left 交错
 * 向左走存入整行的值，当存入后，该行再也不会被遍历，代表下边界的 down 减一，同时判断是否和代表上边界的 up 交错
 * 向上走存入整列的值，当存入后，该列再也不会被遍历，代表左边界的 left 加一，同时判断是否和代表右边界的 right 交错
 */
public class array34顺时针打印矩阵 {

    public ArrayList<Integer> printMatrix(int[][] matrix) {

        ArrayList<Integer> ret = new ArrayList<>();

        int row_min = 0, row_max = matrix.length - 1;//行的最小下标和最大下标（用于列遍历）
        int col_min = 0, col_max = matrix[0].length - 1;//列的最小下标和最大下标（用于行遍历）

        while (row_min <= row_max && col_min <= col_max) {
            //（先从左上到右上）1.先遍历第一行（遍历完第一圈时，则为新的一行，c1++即可）到最后，把其添加到ArrayList中（先从左到右）
            for (int i = col_min; i <= col_max; i++){
                ret.add(matrix[row_min][i]);//添加第一行到ArrayList中
            }
            //（再从右上到右下）2.接着再遍历最右边的那一列（遍历完第一圈时，则为新的一列，row_max--即可），
            // 但要从第二行（即r1 + 1）的最右边那个数开始（因为第一行的最右边那个数在上次已经遍历完啦）
            //把其添加到ArrayList中
            for (int i = row_min + 1; i <= row_max; i++){
                ret.add(matrix[i][col_max]);
            }

            //（再先从右下到左下）3.再遍历最底行（遍历完第一圈时，则为倒数的新的一行，row_max--即可），
            // 但从高索引列向低索引列遍历，且从倒数第二列（即c2 - 1）开始；再存入ArrayList中
            if (row_min != row_max){
                //表示上行与下行不能交错
                for (int i = col_max - 1; i >= col_min; i--)
                    ret.add(matrix[row_max][i]);
            }
            //（最后从左下到左上）4.再遍历最左边列（遍历完第一圈时，则为新的顺数的一列，c1++即可），
            // 但从高索引行向低索引行遍历，且从倒数第二行（即r2 - 1）开始；再存入ArrayList中
            if (col_min != col_max){
                //表示不能右列与左列不能交错
                for (int i = row_max - 1; i > row_min; i--)
                    ret.add(matrix[i][col_min]);
            }

            //这代表一圈一圈地遍历，即每遍历完一圈就把相应的索引改变1
            row_min++; row_max--; col_min++; col_max--;
        }
        return ret;//最后返回该ArrayList即可
    }
}
