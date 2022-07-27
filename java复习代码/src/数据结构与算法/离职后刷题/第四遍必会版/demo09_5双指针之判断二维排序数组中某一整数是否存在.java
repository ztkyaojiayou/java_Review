package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo09_5双指针之判断二维排序数组中某一整数是否存在 {
    //matrix 待查找的数组,target 要查找的数
    public boolean Find(int target, int[][] matrix) {

        int cow = matrix.length;//行数
        int cols = matrix[0].length;//列数

        //从右上角开始找（非常关键，也很巧妙呀）
        int i = 0;
        int j = cols - 1;
        while (i < cow && j >= 0) {
            int cur = matrix[i][j];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    //自写一遍
    public boolean Find02(int target, int[][] matrix) {
        int cow = matrix.length;
        int cols = matrix[0].length;
        //从右上角开始找
        int i = 0;
        int j = cols - 1;
        while (i < cow && j > 0) {
            int curValue = matrix[i][j];
            if (curValue == target) {
                return true;
            } else if (curValue < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

}
