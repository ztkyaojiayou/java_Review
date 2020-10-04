package 秋招笔试.五八同城;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] nums = new int[m][n];
        int x = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }

        test02 test = new test02();
        int res = test.method(nums);
        System.out.println(res);
    }

    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
    private boolean[][] marked;
    // grid 的行数
    private int rows;
    // grid 的列数
    private int cols;
    private int[][] grid;

    public int method(int[][] grid) {
        rows = grid.length;//行数
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;//列数
        this.grid = grid;
        //“标记是否已经访问过了”的数组，默认全为false，表示没有被访问过
        marked = new boolean[rows][cols];
        int count = 0;//用于计数，每一次递归结束就相当于找到了一个岛屿
        //开始遍历每一个点，从原点开始
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;//结果值加1
                    dfs(i, j);//开始深度遍历，找到与之相邻的所有陆地
                }
            }
        }
        //当所有的坐标都访问完之后，返回结果即可
        return count;
    }

    private void dfs(int i, int j) {
        marked[i][j] = true;//遍历过了的点要标记为true，表示已经访问过
        // 得到 4 个方向的坐标（固定写法）
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];

            if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                //开始递归，深度优先遍历
                dfs(newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        //等于号不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
