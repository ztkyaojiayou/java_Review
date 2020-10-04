package 数据结构与算法.第二遍.动态规划;

public class 和最大的连续子序列的和 {
    //方法1：只累加对结果有增益的值
    public int MaxSubArray(int[] nums){
        int res = nums[0];
        int curSum =0;
        for (int i= 0;i<nums.length;i++){
            if (curSum>0){
                curSum+=nums[i];
            }else{
                curSum = nums[i];
            }
            res = Math.max(res,curSum);
        }
       return res;
    }

    //方法2：动态规划
    public int MaxSubArray02(int[] nums){
        //1.先定义数组，dp[i]就表示以 i 结尾的连续子串的最大值
        int[] dp = new int[nums.length];
        //2.再确定初始值
        dp[0] = nums[0];
        //3.再处理一般情况
        for (int i=0;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
        }
        //4.最后返回结果即可
        return dp[nums.length-1];
    }
}
