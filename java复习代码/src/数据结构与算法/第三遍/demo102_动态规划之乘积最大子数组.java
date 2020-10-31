package 数据结构与算法.第三遍;

public class demo102_动态规划之乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        //dp[i][0]：以 下标i 结尾的连续子数组的最小值
        //dp[i][1]：以 下标i 结尾的连续子数组的最大值
        int[][] dp = new int[len][2];
        //初始化
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        //结果集
        int res = dp[0][1];
        //一般情况
        for (int i = 1;i<len;i++) {
            //为什么要计算dp[i][0]？因为dp[i][1]的计算需要用到它
            dp[i][0] = Math.min(Math.min(nums[i],nums[i]*dp[i-1][0]),nums[i]*dp[i-1][1]);
            dp[i][1] = Math.max(Math.max(nums[i],nums[i]*dp[i-1][0]),nums[i]*dp[i-1][1]);
            //不断更新最大值
            res = Math.max(res,dp[i][1]);
        }
     return res;
    }
}
