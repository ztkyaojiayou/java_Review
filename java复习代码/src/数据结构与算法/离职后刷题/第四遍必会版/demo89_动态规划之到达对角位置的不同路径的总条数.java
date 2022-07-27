package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo89_动态规划之到达对角位置的不同路径的总条数 {
    // 因为只需要求路径的条数，不涉及到具体的元素值（对比“最短路径的路径和”），
    // 因此无需给出具体的二维数组，只需给出行和列即可
    public int uniquePaths(int m, int n) {//m是行，n是列
        //dp[i][j]就表示从起点到达位置(i,j)的总路径数
        int[][] dp = new int[m][n];
        //初始化，即边上的位置，其路径就一条呀！！！
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        //一般情况，先列(j)再行(i)
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                //求得是总路径，因此不使用max函数呀！！！
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //最后返回结果即可
        return dp[m - 1][n - 1];
    }

    //自写一遍
    public int uniquePaths02(int m, int n) {//m是行，n是列
        int[][] dp = new int[m][n];
        //边界上的位置，都只有一条路径呀（即直达）
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
