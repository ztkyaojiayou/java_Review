package 数据结构与算法.第三遍;

public class demo93_动态规划之最长上升子序列的长度 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int res = 0;
        //dp[i] 表示 以nums[i] 结尾的序列的最长子序列的长度
        int[] dp = new int[len];
        //初始化
        for (int i = 0;i<len;i++){
            dp[i] = 1;
        }
        for (int i = 1;i<len;i++){
            for (int j = 0;j<i;j++){
                if (nums[j] < nums[i]){//若小于，则可以状态转移
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
                //随时更新
                res = Math.max(res,dp[i]);
            }
        }
        //返回最终结果
        return res;
    }
}
