package 数据结构与算法.第三遍;

/**
 * 类比兑换零钱那个题，几乎如出一辙，把每一个小于n的自然数看成面值即可
 */
public class demo91_动态规划之构成目标和的最少完全平方数的个数 {
    public int numSquares(int target) {
        //dp[i] 表示：组成i的完全平方数的个数的最小值，则所求结果即为dp[n]
        int[] dp = new int[target + 1];
        //初始化
        dp[0] = 0;
        for (int i = 1; i < target + 1; i++) {
            dp[i] = i;//先赋初值，即设为最坏情况，即全由1相加，于是就有i种
        }
        //一般情况
        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (j * j < i) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }

        //最后返回结果即可
        return dp[target];
    }


    //自写一遍
    public int numSquares01(int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 1; i < target; i++) {
            dp[i] = i;
        }
        for (int i = 1; i < target + 1; i++) {
            //小于当前目标值的所有元素都可以通过平方来构成当前目标值，
            // 因此要不断比较来选出最小的那个方案，因此这里使用了min函数
            for (int j = 0; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[target];
    }
}
