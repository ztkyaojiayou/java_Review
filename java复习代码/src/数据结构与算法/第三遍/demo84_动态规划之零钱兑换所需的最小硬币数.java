package 数据结构与算法.第三遍;

public class demo84_动态规划之零钱兑换所需的最小硬币数 {
    //由于面值不唯一，因此无法用贪心算法，切记
    public int coinChange(int[] coins, int amount) {
        //dp[i]表示兑换零钱i所需要的的最小硬币数
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 0;
        for (int i = 1;i<amount;i++){
            dp[i] = amount;//即先初始化成最坏情况，全由一块钱组成
        }

        //一般情况
        for (int i = 1;i<amount+1;i++){
            for (int j = 0;j<coins.length;j++) {
                if (coins[j] <= i) {//前提
                    dp[i] = Math.min(dp[i],dp[i-coins[j]] + 1);//核心
                }
            }
        }

        //返回结果
        return dp[amount];
    }
}
