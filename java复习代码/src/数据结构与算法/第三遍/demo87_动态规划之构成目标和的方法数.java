package 数据结构与算法.第三遍;

/**
 *  * 解析：
 *  * 原问题等同于:找到nums的一个正子集和一个负子集， 使得总和等于目标S
 *  * 我们假设P是正子集，N负子集，例如：假设 nums=[1,2, 3, 4, 5], 目标S = 3,
 *  * 一个可能的解决方案是+1-2+3-4+5= 3，这里的正子集P= [1, 3, 5]和负子集N =[2, 4]
 *  * 那么如何将其转换为子集求和问题呢？
 *  * 由于:
 *  *  sum(P) + sum(N) = 目标S
 *  *  sum(P) -sum(N) = 数组和
 *  * 因此，原来的问题可转化为一个求子集的和问题:
 *  * 找到数组nums中的一个正子集P,使得sum(P) = (目标S + 数组和) / 2  (易知为一个定值）
 *  *
 *  * 所以物品就相当于nums数组中的元素，P就是背包。
 *  * 选物品，则就是把数组中对应的数放进正子集P里，不选则不放，
 *  * 最终要求正子集P的元素和（也就是背包容量）刚好等于 (目标S + 数组和) / 2（target）即可
 *  *
 */
public class demo87_动态规划之构成目标和的方法数 {
    public int findTargetSumWays02(int[] nums, int S) {
        //先求数组的和
int sum = 0;
for (int i = 0;i<nums.length;i++){
    sum += nums[i];
}
//剪枝
if (sum < S || (sum + S) % 2 == 1){
    return 0;
}
//转化为背包问题（完美）
int target = (sum + S) / 2;//背包，即正子集P
int len = nums.length;
// dp[i][j]表示：从前i件物品中选取若干件放入容量为j的背包中，使背包刚好能装满的方案数目
        int[][] dp = new int[len + 1][target + 1];
        //初始化
        dp[0][0] = 1;
        for (int i = 1;i<target+1;i++){
            dp[0][i] = 0;//很明显放不了
        }
        for (int j = 1;j<len+1;j++){
            dp[j][0] = 1;//即不放，这也是一种方法
        }
        //0-1背包问题
        for (int i = 1;i< len+1;i++){
            for (int j = 0;j<target+1;j++){//nums[i]即为物品
                if (j < nums[i-1]){//即易知，放不下，则维持原来的结果
                    dp[i][j] = dp[i-1][j];
                }else {//表示可以放下，但有两种选择，即可放可不放（切记），又由于是求总方法数，因此要加起来
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
        }
        //最后返回结果即可
        return dp[len][target];
    }
}
