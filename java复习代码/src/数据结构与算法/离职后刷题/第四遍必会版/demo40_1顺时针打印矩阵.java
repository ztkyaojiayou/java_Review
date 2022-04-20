package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.ArrayList;

public class demo40_1顺时针打印矩阵 {
    public ArrayList<Integer> printMatrix(int[][] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int row_max = nums.length - 1;//行
        int col_max = nums[0].length - 1;//列
        int row_min = 0;
        int col_min = 0;
        while (row_min <= row_max && col_min <= col_max) {
            //顺序打印第一行
            for (int i = col_min; i < col_max; i++) {
                res.add(nums[row_min][i]);
            }

            //顺序打印最后一列
            for (int i = row_min + 1; i < row_max; i++) {
                res.add(nums[i][row_max]);
            }

            //从右到左打印最后一行
            if (row_min != row_max) {
                for (int i = col_max - 1; i > 0; i--) {
                    res.add(nums[row_max][i]);
                }
            }

            //从下到上打印第一列
            if (col_min != col_max) {
                for (int i = row_max - 1; i > 0; i--) {
                    res.add(nums[i][col_min]);
                }
            }

            //表示打印第二圈
            row_min++;
            col_min++;
            row_max--;
            col_max--;
        }

        return res;
    }
}
