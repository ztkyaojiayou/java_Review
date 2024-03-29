package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo86_动态规划之最短的路径的和 {
    public int minPathSum(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        //dp[i][j]表示从nums[0][0]到nums[i][j]的路径和最小的路径的和，即为所求（重点）
        int[][] dp = new int[m][n];
        //确定初始值，这也是“最短路径”的体现，
        //即初始位置时最短路径的和就是这个位置的值呀！！！
        dp[0][0] = nums[0][0];
        //第一行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + nums[0][i];
        }
        //第一列
        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + nums[j][0];
        }

        //一般情况
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //该状态方程的意义是到达位置（i，j）处的路径和最短的路径的和可以由“到达其左边位置的最短路径和到达其上方位置的最短路径的较小值+当前遍历值”表示
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
            }
        }
        //最后返回结果即可
        return dp[m - 1][n - 1];
    }

    //自写一遍
    public int minPathSum02(int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 0;
        for (int i = 1;i<col;i++){
            dp[0][i] = dp[0][i-1]+nums[0][i];
        }

        for (int j = 1;j<row;j++){
            dp[j][0] = dp[j-1][0]+nums[j][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+nums[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}
