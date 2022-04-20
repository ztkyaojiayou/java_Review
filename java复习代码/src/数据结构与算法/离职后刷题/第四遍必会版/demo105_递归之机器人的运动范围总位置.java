package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo105_递归之机器人的运动范围总位置 {
    public int MoveCount(int[][] matrix, int k) {
        int rows = matrix.length;//行
        int cols = matrix[0].length;//列
        //访问数组
        boolean[][] visited = new boolean[rows][cols];//代表每一个位置
        //直接调用递归方法即可
        return dps(k, rows, cols, 0, 0, visited);
    }

    //具体的递归方法
    private int dps(int target, int rows, int cols, int cur_row, int cur_col, boolean[][] visited) {
        //递归出口
        if (cur_col < 0 || cur_col > cols || cur_row < 0
                || cur_row > rows || visited[cur_row][cur_col] == true
                || !checkSum(target, cur_col, cur_row)) {
            return 0;
        }

        //做选择，即：走当前位置,将当前位置置为“已经访问过”
        visited[cur_row][cur_col] = true;
        //下一轮递归：四个方向都相加即可（式后的“+1”是指当前位置也算一个格子）
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
        //行和（先加个位后加十位）
        while (cur_row != 0) {
            //个位（取余即可）
            sum += cur_row % 10;
            //十位（取商即可）
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


    //自写一遍
    public int MoveCount02(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        Boolean[][] visited = new Boolean[row][col];
        //不需要向【岛屿数量】那道题一样对每一个位置都遍历，这个题就是
        return method(matrix, k, 0, 0, visited);
    }

    //递归方法
    private int method(int[][] matrix, int target, int curRow, int curCol, Boolean[][] visited) {
        //递归出口
        //越界或已访问过或各位上的和大于目标值，此时返回0，即该位置不符合题目要求啦
        if (curRow < 0 || curRow > matrix.length
                || curCol < 0 || curCol > matrix[0].length
                || visited[curRow][curCol] == true
                || !checkSum02(target, curCol, curRow)) {
            return 0;
        }
        //标记当前结点为已经访问过的结点
        visited[curRow][curCol] = true;
        //对当前结点的四个结点相加即可
        //（式后的“+1”是指当前位置也算一个格子）
        return method(matrix, target, curRow + 1, curCol, visited)
                + method(matrix, target, curRow - 1, curCol, visited)
                + method(matrix, target, curRow, curCol + 1, visited)
                + method(matrix, target, curRow, curCol - 1, visited)
                + 1;
    }

    private boolean checkSum02(int target, int curCol, int curRow) {
        int sum = 0;
        //先加个位（取余：%）后加十位（取商：/）：最终都是以个位的形式相加的
        while (curCol != 0) {
            sum += curCol % 10;
            curCol = curCol / 10;

        }
        while (curRow != 0) {
            sum += curRow % 10;
            curRow = curRow / 10;
        }

        if (sum <= target) {
            return true;
        } else {
            return false;
        }
    }
}
