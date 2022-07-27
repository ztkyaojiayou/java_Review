package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo84_动态规划之零钱兑换所需的最小硬币数 {
    //由于面值不唯一，因此无法用贪心算法，切记
    public int coinChange(int[] coins, int amount) {
        //dp[i]表示兑换零钱i所需要的的最小硬币数
        //长度为amount+1是因为我们最少也是从1块钱算起，
        //但数组的下标又是从0开始的，于是我们要往后推一位
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            //即先初始化成最坏情况，全由一块钱组成
            dp[i] = amount;
        }

        //一般情况（即从一块钱开始算起，一步一步推演到最终结果，都是这个套路）
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                //前提，即只能使用小于等于当前面值的零钱来兑换呀
                if (coins[j] <= i) {
                    //核心
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //返回结果
        //此时表示凑不出
        if (dp[amount] > amount) {
            return -1;
        }
        return dp[amount];
    }

    //自写一遍
    public int coinChange02(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount;
        }

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < len; j++) {
                if (coins[j] <= i) {
                    //即从是否要用当前硬币来凑这两种方案中选最小值
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //表示凑不了，即需要的硬币数比理论上使用最多的硬币的数目还大，肯定凑不了呀，废话！
        if (dp[amount] > amount) {
            return -1;
        }
        return dp[amount];
    }
}
