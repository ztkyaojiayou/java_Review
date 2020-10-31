package 数据结构与算法.第三遍;

public class demo85_动态规划之面积最大的正方形的面积 {
    public int maximalSquare(char[][] nums) {
int maxSize = 0;
int m = nums.length;//行
int n = nums[0].length;//列
//dp[i][j] 表示 以 (i,j) 为右下角，且只包含 1 的正方形的边长的最大值
        int[][] dp = new int[m][n];
        for (int i = 0;i<m;i++){
            for (int j = 0;j<n;j++){
                if (nums[i][j] == 1){//前提
                    //这其实就是初始条件
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else {//一般情况，注意：是取这三种情况中的最小值再加1，画图就能明白
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                    maxSize =Math.max(maxSize,dp[i][j]);
                }
            }
        }
        //返回结果
        int maxSquare = maxSize * maxSize;
        return maxSquare;
    }
}
