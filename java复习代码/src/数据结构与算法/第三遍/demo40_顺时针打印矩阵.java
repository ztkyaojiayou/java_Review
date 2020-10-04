package 数据结构与算法.第三遍;

import java.util.ArrayList;

public class demo40_顺时针打印矩阵 {
    public ArrayList<Integer> printMatrix(int[][] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int row_max = nums.length-1;
        int col_max = nums[0].length-1;
        int row = 0;
        int col = 0;
        while (row <= row_max &&col <= col_max){
            for (int i = col;i<col_max;i++){
                res.add(nums[row][i]);
            }
            for (int i = row+1;i<row_max;i++){
                res.add(nums[i][row_max]);
            }
if (row != row_max){
    for (int i = col_max-1;i>0;i--){
        res.add(nums[row_max][i]);
    }
}
if (col != col_max){
    for (int i = row_max-1;i>0;i--){
        res.add(nums[i][col]);
    }
}
//表示打印第二圈
row++;
col++;
row_max--;
col_max--;
        }
        return res;
    }
}
