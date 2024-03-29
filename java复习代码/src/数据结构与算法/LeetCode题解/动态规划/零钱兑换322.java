package 数据结构与算法.LeetCode题解.动态规划;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。

 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 解释：因为无法凑出
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */

/**
 * 方法：使用动态规划：自底向上
 * 即先使总金额从1开始（则易知，只需要遍历总金额amount即可），
 * 先计算出凑成金额1需要的最少硬币数
 * （则易知，直接遍历一遍硬币面值，一个一个试，取其中的最小值即可，
 * 但前提是凑成该面值的硬币要比该值要小，否则肯定凑不了嘛，这里在代码中加一个判断即可）
 * 再计算金额2需要的最少银币数，此时就可以转化到 “凑成金额1需要的最少银币数 + 1”来解决，
 * 这里的1表示使用凑成金额1需要的最少银币数再加上一个面值为1（被减去的那个硬币值）的硬币即可凑成金额2（每一步都是这样）
 * 以此类推，直到计算出金额11需要的最少银币数。
 *
 * 使用例子说明，通俗易懂：
 * 假设 coins = [1, 2, 5], amount = 11。
 * 则当 i==0 时无法用硬币组成，为 0 。当 i<0 时，忽略 F(i)
 *
 * F(i)	最小硬币数量
 * F(0)	0 //金额为0不能由硬币组成
 * F(1)	1 //F(1)=min(F(1-1),F(1-2),F(1-5))+1=1
 * F(2)	1 //F(2)=min(F(2-1),F(2-2),F(2-5))+1=1
 * F(3)	2 //F(3)=min(F(3-1),F(3-2),F(3-5))+1=2
 * F(4)	2 //F(4)=min(F(4-1),F(4-2),F(4-5))+1=2
 * ...	...
 * F(11)	3 //F(11)=min(F(11-1),F(11-2),F(11-5))+1=3
 *
 * 务必注意：不能用贪心算法，因为本题的钞票面值是不确定的，这一点很重要！！！
 * （即面额是任意的，不一定是日常生活中的那几种唯一的面值！！！）
 * 钞票面值:[1,2,5,10] ;金额: 14;
 * 最优解需要3张
 * 贪心思想:每次优先使用大面值的金额，如:
 * 先选1张10块的，剩下4元;再选1张2元的，剩下2元;再选1张2元的，搞定!
 * 钞票面值:[1,2,5,7, 10] ;金额: 14;最优解需要2张(两张7块的，易知，日常生活中其实是没有面额为7的钞票的)。
 * 若仍然用贪心思想:
 * 先选1张10块的，剩下4元;再选1张2元的，剩下2元;再选1张2元的，这就错了!
 *
 * 结论:贪心思想在个别面值组合时是可以的，比如日常生活中的RMB面值[1,2, 5, 10, 20, 50, 100]，但是本题面值不确定，故贪心思想不可以。
 */
public class 零钱兑换322 {

    //动态规划求解
    public int coinChange(int[] coins, int amount) {
        //先定义一个数组
        int[] dp = new int[amount + 1];//dp[i] 表示凑成总金额为i所需的最少的硬币个数
        //再确定初始条件
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            //对后面的每个元素都初始化为 amount + 1，表示不可能凑出来的情况（只要设置成大于amount即可）
            dp[i] = amount + 1;
        }
        //Arrays.fill(dp, amount + 1);//骚操作，直接填充

        for (int i = 1; i <= amount; i++) {//这层遍历相当于是使amount为遍历，没有其它意义
            for (int j = 0; j < coins.length; j++) {//对于每一个金额i，它都可能由所有的硬币面值凑出，因此需要对硬币面值做一次遍历，一个一个试
                if (coins[j] <= i) {//但前提是硬币面值要小于当前金额，否则怎么凑嘛（1块零钱怎么用2块钱的硬币凑？半个？）
                    //状态方程，即当前金额为i所需的最少的硬币个数 = 减去当前要兑换的这个硬币后所剩下的金额所需的最少硬币数 + 1（即当前这个要兑换的硬币coins[j]）
                    //当然，这里还需要和之前的dp[i] 继续比较，使得该值永远是最小值，所以这里用了min（）
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);// 当确定了一个硬币时，就把总金额i - 当前硬币值，剩下的金额继续如此
                }
            }
        }
        //返回结果即可（主要凑不了的情况）
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。
     * 写出函数来计算可以凑成总金额的硬币组合数。
     * 假设每一种面额的硬币有无限个。
     *
     * 示例 1:
     *
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2:
     *
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     * 示例 3:
     *
     * 输入: amount = 10, coins = [10]
     * 输出: 1
     */

    class Solution518 {
        public int change(int[] nums,int amount) {
            //dp[i]表示从前i个零钱里选择若干去兑换面额为amount的总方案数
            //从0个零钱里去兑换面额为0的整钱是可兑换的，一个方案
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            //与上题不同，这里是倒过来遍历，即用零钱去凑，先遍历零钱
                for (int i=0;i<nums.length;i++){
                    //对于遍历到的每一种面值的硬币，逐个考虑添加到 “总金额” 中。
                    // 由于硬币的个数可以无限选取，因此对于一种新的面值的硬币 coins[i - 1]（注意这里有一个位移偏差），
                    // 依次考虑选取 0 枚、1 枚、2 枚，以此类推，直到选取这种面值的硬币的总金额超过需要的总金额 j，
                    // dp[i][j] 是它们的值的和。
                for (int j = nums[i]; j < amount + 1; j++) {
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
            return dp[amount];
        }
    }

class Solution1 {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        //明确base case  在金额为0的情况下,不管是任何***，只能有一种组合（也就是什么***都不用这种组合）
        for(int i = 0; i<coins.length+1; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < coins.length+1; i++){
            for(int j = 1; j<amount+1; j++){
                //如果当前的金额比***面值小，那只能选择不放，继承之前的状态了
                if(j < coins[i-1]) {
                    dp[i][j] = dp[i-1][j];
                }
                    //当前的组合数目应该是选择不选择都放到一起的
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[coins.length][amount];
    }
}




