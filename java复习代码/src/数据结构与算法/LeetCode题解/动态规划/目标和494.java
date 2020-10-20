package 数据结构与算法.LeetCode题解.动态规划;
/**
 *494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 *
 * 注意:
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。
 */


/**
 * 解析：
 * 原问题等同于:找到nums的一个正子集和一个负子集， 使得总和等于目标S
 * 我们假设P是正子集，N负子集，例如：假设 nums=[1,2, 3, 4, 5], 目标S = 3,
 * 一个可能的解决方案是+1-2+3-4+5= 3，这里的正子集P= [1, 3, 5]和负子集N =[2, 4]
 * 那么如何将其转换为子集求和问题呢？
 * 由于:
 *  sum(P) + sum(N) = 目标S
 *  sum(P) -sum(N) = 数组和
 * 因此，原来的问题可转化为一个求子集的和问题:
 * 找到数组nums中的一个正子集P,使得sum(P) = (目标S + 数组和) / 2  (易知为一个定值）
 *
 * 所以物品就相当于nums数组中的元素，P就是背包。
 * 选物品，则就是把数组中对应的数放进正子集P里，不选则不放，
 * 最终要求正子集P的元素和（也就是背包容量）刚好等于 (目标S + 数组和) / 2（target）即可
 *
 * 2.接下来就是常见的求0-1背包方案数的问题，这里是恰好装满背包的方案数，看看背包九讲就会了
 * 直接把状态转移方程贴这里：
 * （1）当0<=j<w[i]时，G[i][j]=G[i-1][j];
 * （2）当j>=w[i]时，G[i][j]=G[i-1][j]+G[i-1][j-w[i]]}，其中w[i]为第i件
 *
 * 初始化：G[0][0]=1，即容量为0的背包可以用“空”填满，也是一种方法
 *
 * 这里把我对状态转移方程的理解描述一下，因为感觉九讲这里讲的不太详细：（第416题已经讲清楚案啦，一模一样）
 * G[i][j]表示：从前i件物品中选取若干件放入容量为j的背包中，使背包刚好能装满的方案数目
 * （1）当w[i] >= j时，说明当前的第i件物品放不进背包里，那么要想填满容量为j的背包，只能从前i-1件物品里去选，所以其方案数继承自G[i-1][j]；
 * （2）当w[i] <= j时，说明第i件物品可以放进背包里，此时就有两种互斥的情况，即可以放入i，也可以不放入i（即能放但不放），最后把这这两种情况的方法数加起来即可，分析如下：
 *      2.1.若不放i，此时的方案数就是继承自第i-1件的方案数，即G[i-1][j]。
 *      2.2.若放i，即在背包载重最大为j-nums[i]的情况下（因为已经放了nums[i]了嘛），
 *      对前i-1个物品进行选择，所能装的最大价值，即G[i-1][j-w[i]]；
 *      2.3 最后，两者相加即可，即 G[i][j] = G[i-1][j]+G[i-1][j-w[i]]（核心）
 *
 * 最后，可以再优化一下，即使用一个一维数组存dp表格即可，压缩了i所在的维度，逆序更新数组元素。
 * 此时上述状态方程可以改写成：G[j] = G[j] + G[j-w[i]]，或dp[j] = dp[j] + dp[j-w[i]]。
 */
public class 目标和494 {
    /**
     *  普通版，更容易理解（二维dp）
     */
    public int findTargetSumWays02(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 背包容量为整数，sum + S为奇数的话不满足要求
        if (((sum + S) & 1) == 1) {
            return 0;
        }
        // 目标和不可能大于总和
        if (S > sum) {
            return 0;
        }
        sum = (sum + S) / 2;
        int len = nums.length;
        // dp[i][j]表示：从前i件物品中选取若干件放入容量为j的背包中，使背包刚好能装满的方案数目
        int[][] dp = new int[len + 1][sum + 1];
        //初始化
        dp[0][0] = 1;

        // 01背包
        // i(1 ~ len)表示遍历（不一定选）了 i 个元素，j(0 ~ sum) 表示它们的和
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= sum; j++) {
                // 装不下（不选当前元素）
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    //装得下，但有两种情况，即可装可不装（当前元素可选可不选）
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][sum];
    }


    /**
     * 优化版（一维dp）
     */

    public int findTargetSumWays(int[] nums, int S) {
        //先求该数组的和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //取目标背包的重量target
        int target = (S + sum) / 2;
        //特判：数组和小于目标S不行，target为奇数不行。
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        //创建一个一维数组（这是优化之后的解法，原始写法应该是使用一个二维数组的）
        //dp[i] 表示从前面的元素（物品）中选取若干放入背包承重量为i的恰好装满的方案数(和二维的稍有不同，这里相当于只看结果啦）
        int[] dp = new int[target + 1];
        //确定初始条件
        dp[0] = 1;
        //再考虑一般情况，使用状态方程即可
        for(int i=1;i<=nums.length;i++){
            for(int j=target;j>=nums[i-1];j--){//务必逆序，即倒着推
                //因为倒着推。每个物品只有一次选择的机会，放或者不放。
                //而如果是正向推，则每个物品在每次都可以有两种选择，即放或者不放。
                //所以是完全背包，不是不可以，只是写法上会不太一样。
                dp[j]=dp[j]+dp[j-nums[i-1]];//状态方程，即总方案数 = 放当前元素的方案数dp[j-nums[i-1]] + 不放当前元素的方案数dp[j]
            }
        }
        //最终返回结果值即可
        return dp[target];
    }
}
