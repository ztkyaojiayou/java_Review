package 数据结构与算法.剑指offer题解.第二遍.动态规划;

public class 零钱兑换 {
    public int coinChange(int[] nums,int amount){
        ////dp[i] 表示凑成总金额为i所需的最少的硬币个数
        int[] dp = new int[nums.length + 1];
        //确定初始值
        dp[0]=0;
        for (int i=1;i<nums.length+1;i++){
            dp[i] = amount+1;
        }
        //一般情况
        for (int i=1;i<=amount;i++){
            for (int j=0;j<nums.length;j++){
                if (nums[j] < i){
                    dp[i] = Math.min(dp[i],dp[i-nums[j]] +1);
                }
            }
        }
        //若总数还大于amount，即说明无法兑换，返回-1
        if (dp[amount] > amount){
            return -1;
        }
        return dp[amount];
    }
}
