package 数据结构与算法.第三遍;

/**
 * 类比兑换零钱那个题，几乎如出一辙，把每一个小于n的自然数看成面值即可
 */
public class demo91_动态规划之构成目标和的最少完全平方数的个数 {
    public int numSquares(int n) {
        //dp[i] 表示：组成i的完全平方数的个数的最小值，则所求结果即为dp[n]
        int[] dp = new int[n + 1];
        dp[0] = 0;
for (int i = 1;i<n+1;i++){
    //初始化
    dp[i] = i;//先赋初值，即设为最坏情况，即全由1相加，于是就有i种
    for (int j = 1;j*j<i;j++){//一般情况
        dp[i] = Math.min(dp[i],dp[i-j*j] + 1);
    }
}
//最后返回结果即可
return dp[n];
    }
}
