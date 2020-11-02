package 数据结构与算法.第三遍;

public class demo106_回溯之矩阵中是否存在目标路径 {
    int rows;
    int cols;
    boolean[][] isVisited;
    public boolean hasPath(char[][] matrix,char[] str) {
        rows = matrix.length;
        cols = matrix[0].length;
        isVisited = new boolean[rows][cols];
        int cur_index = 0;//深度搜索得到的路径的下标
        //从第一个位置起开始深度优先搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dps(matrix,str, i, j, cur_index)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dps(char[][] matrix,char[] str, int cur_row, int cur_col, int cur_index) {
        //递归出口

        if (cur_index == str.length-1) {//当下标等于目标路径的最大下标时，就表示已经找到该路径了
            return true;
        }
        if (cur_row < 0 || cur_row > rows || cur_col < 0 || cur_col > cols || matrix[cur_row][cur_col] != str[cur_index] || isVisited[cur_row][cur_col]) {
            return false;
        }

        //处理
        cur_index++;
        isVisited[cur_row][cur_col] = true;
        //做选择，递归，往四个方向搜索
        boolean res = dps(matrix,str, cur_row, cur_col - 1, cur_index)
                || dps(matrix,str,cur_row, cur_col + 1, cur_index)
                || dps(matrix, str,cur_row - 1, cur_col, cur_index)
                || dps(matrix,str,cur_row + 1, cur_col, cur_index);

        //若沿着当前位置往四个方向搜索都没有找到，则撤销该位置
        if (!res) {
            cur_index--;
            isVisited[cur_row][cur_col] = false;
        }
        return res;
    }
}
