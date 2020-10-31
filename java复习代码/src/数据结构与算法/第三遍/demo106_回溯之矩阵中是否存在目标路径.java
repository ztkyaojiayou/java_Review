package 数据结构与算法.第三遍;

public class demo106_回溯之矩阵中是否存在目标路径 {
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] visited = new boolean[rows * cols];
        int count = 0;
        //
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dps(matrix, rows, cols, str, visited, i, j, count)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dps(char[] matrix, int rows, int cols, char[] str, boolean[] visited, int cur_row, int cur_col, int count) {
        int cur_position = rows * cols + cur_col;//把当前位置拉直

        //递归出口
        if (count == str.length) {
            return true;
        }
        if (cur_row < 0 || cur_row > rows || cur_col < 0 || cur_col > cols || matrix[cur_position] != str[count] || visited[cur_position]) {
            return false;
        }

        //处理
        visited[cur_position] = true;
        count++;

        //做选择，递归，往四个方向搜索
        boolean res = dps(matrix, rows, cols, str, visited, cur_row, cur_col - 1, count)
                || dps(matrix, rows, cols, str, visited, cur_row, cur_col + 1, count)
                || dps(matrix, rows, cols, str, visited, cur_row - 1, cur_col, count)
                || dps(matrix, rows, cols, str, visited, cur_row + 1, cur_col, count);

        //若沿着当前位置往四个方向搜索都没有找到，则撤销该位置
        if (!res) {
            count--;
            visited[cur_position] = false;
        }
        return res;
    }
}
