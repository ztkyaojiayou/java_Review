package 数据结构与算法.剑指offer题解.第二遍.动态规划;

public class 戳气球 {
        public int maxCoins(int[] nums) {
        int len = nums.length;
        // 新建一个数组，添加两侧的虚拟气球
        int[] new_arr = new int[len + 2];
        //赋值新数组
        new_arr[0] = new_arr[len + 1] = 1;
        for (int i = 1; i <= len; i++) {
            new_arr[i] = nums[i - 1];
        }
        //定义dp数组，dp[i][j]表示，戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高分数
            int[][] dp = new int[len + 2][len + 2];
        //初始值，即为dp[i][j]=0
        for (int i=len;i>=0;i--){
            for (int j=i+1;j<len+2;j++){
                for (int k=i+1;k<j;k++){
                    dp[i][j] = Math.max(dp[i][j],dp[i][k]+dp[k][j]+new_arr[i]*new_arr[k]*new_arr[j]);
                }
            }
        }
        return dp[0][len-1];
        }
}
