package 数据结构与算法.第三遍;

public class demo89_动态规划之到达对角位置的不同路径的总条数 {
    public int uniquePaths(int m, int n) {//m是行，n是列
        //dp[i][j]就表示从起点到达位置(i,j)的总路径数
        int[][] dp = new int[m][n];
//初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        //一般情况，先列再行
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //最后返回结果即可
        return dp[m - 1][n - 1];
    }
}
