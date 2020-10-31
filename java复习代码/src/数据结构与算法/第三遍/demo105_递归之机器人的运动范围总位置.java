package 数据结构与算法.第三遍;

public class demo105_递归之机器人的运动范围总位置 {
    public int MoveCount(int k, int rows, int cols) {
        boolean[] visited = new boolean[rows * cols];//代表每一个位置
        return dps(k, rows, cols, 0, 0, visited);
    }

    private int dps(int target, int rows, int cols, int cur_row, int cur_col, boolean[] visited) {
        int cur_position = cur_row * cols + cur_col;//把当前位置拉直，很巧妙~

        //递归出口
        if (cur_col < 0 || cur_col > cols || cur_row < 0
                || cur_row > rows || visited[cur_position] == true
                || !checkSum(target, cur_col, cur_row)) {
            return 0;
        }

        //做选择，即：走当前位置
        visited[cur_position] = true;
        //递归
        int res = dps(target, rows, cols, cur_row, cur_col - 1, visited)
                + dps(target, rows, cols, cur_row + 1, cur_col, visited)
                + dps(target, rows, cols, cur_row - 1, cur_col, visited)
                + dps(target, rows, cols, cur_row, cur_col + 1, visited)
                + 1;
        return res;
    }

    //用于检查当前位置是否小于target
    private boolean checkSum(int target, int cur_col, int cur_row) {
        int sum = 0;
        //行和
        while (cur_row != 0) {
            sum += cur_row % 10;
            cur_row = cur_row / 10;
        }
        //再加列和
        while (cur_col != 0) {
            sum += cur_col % 10;
            cur_col = cur_col / 10;
        }

        //判断
        if (sum > target) {
            return false;
        } else {
            return true;
        }
    }
}
