package 数据结构与算法.第三遍;

public class demo85_动态规划之面积最大的正方形的面积 {
    public int maximalSquare(char[][] nums) {
        int maxSize = 0;
        int m = nums.length;//行
        int n = nums[0].length;//列
        //dp[i][j] 表示 以 (i,j) 为右下角，且只包含 1 的正方形的边长的最大值
        int[][] dp = new int[m][n];
        //对于动态规划，两层循环很正常
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 1) {//前提，即当前位置首先必须是'1'.
                    //这其实就是初始条件
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {//一般情况，注意：是取这三种情况中的最小值再加1，画图就能明白
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    // 注意：dp[i][j]求得只是以当前位置为右下角所形成的最大边长，
                    // 但并不一定是最终的最大边长，因此还要随时更新真正的最大值
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        //返回结果
        int maxSquare = maxSize * maxSize;
        return maxSquare;
    }
}
