package 数据结构与算法.LeetCode题解.第二遍.动态规划;

import java.util.Arrays;

public class 最长上升子序列的长度_即不要求连续 {
    public int lengthOfLIS(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        //定义一个数组，其中，dp[i] 表示 以nums[i] 结尾的序列的最长子序列的长度。
        int[] dp = new int[nums.length];
        //赋初值为1
        for (int i=0;i<dp.length;i++){
            dp[i] = 1;
        }
        //Arrays.fill(dp,1);给数组赋初值的骚操作
        //结果变量
        int res = 0;
        for (int i = 1;i<nums.length;i++){
            for (int j = 0;j<i;j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }

}
