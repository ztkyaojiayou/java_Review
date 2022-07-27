package 数据结构与算法.LeetCode题解.动态规划;

import java.util.Arrays;

/**
 * 300.给定一个无序的整数数组，找到其中最长上升子序列(不要求相邻）的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */

/**
 * 解题思路：
 *
 * 状态定义：
 * dp[i] 的值代表 nums 前 i 个数字的最长子序列长度。
 * 转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i) 列表区间，做以下判断：
 *
 * （1）当 nums[i] > nums[j] 时： nums[i] 可以接在 nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j] + 1；
 * （2）当 nums[i] <= nums[j] 时： nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。
 *
 * 上述所有的（1）情况 计算出的 dp[j] + 1 的最大值，就为直到 i 的最长上升子序列长度（即 dp[i] ）。
 * 实现方式为遍历 j 时，每轮执行 dp[i] = max(dp[i], dp[j] + 1)。
 * 转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
 *
 * 初始状态：dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
 * 返回值：返回 dp 列表最大值，即可得到全局最长上升子序列长度。
 *
 * 复杂度分析：
 * 时间复杂度 O(N^2)：遍历计算 dp 列表需 O(N)，计算每个 dp[i] 需 O(N)。
 * 空间复杂度 O(N) ： dp 列表占用线性大小额外空间。

 //解释2：
 dp[i]表示以第 i 个数字为结尾的最长上升子序列的长度。
 求 dp[i] 的时候，如果前边的某个数 nums[j] < nums[i] ，
 那么我们可以将第 i 个数接到第 j 个数字的后边作为一个新的上升子序列，
 此时对应的上升子序列的长度就是 dp[j] + 1。

 可以从下边情况中选择最大的。
 如果 nums[0] < nums[i]，dp[0] + 1 就是 dp[i] 的一个备选解。
 如果 nums[1] < nums[i]，dp[1] + 1 就是 dp[i] 的一个备选解。
 如果 nums[2] < nums[i]，dp[2] + 1 就是 dp[i] 的一个备选解。
 ...
 如果 nums[i-1] < nums[i]，dp[i-1] + 1 就是 dp[i] 的一个备选解。
 从上边的备选解中选择最大的就是 dp[i] 的值。
 */
public class 最长上升子序列300 {
        public int lengthOfLIS(int[] nums) {
            //特判
            if (nums.length == 0) {
                return 0;
            }
            //定义一个长度为原数组长度的数组，其中，dp[i] 表示 以nums[i] 结尾的序列的最长子序列的长度。
            int[] dp = new int[nums.length];
            int result = 0;
            //初始化数组的元素全为1，即置为最小长度
            for (int i = 0; i < dp.length; i++) {
                dp[i] = 1;
            }
            // Arrays.fill(dp, 1);(骚操作）
            //（先填满数组）开始遍历，先用指针i固定一个值nums[i]。再用另外一个指针j从头开始遍历，
            //当nums[j] < nums[i]时，此时最长上升子序列长度就可以写为 dp[j] + 1；
            //即“以当前位置i结尾的最长子序列的值 = max（以前一个位置j结尾的最长子序列的值 + 当前值 nums[i]，当前值num[i]单独构成的子序列的长度”)。
            //要取max是因为可能有多个满足条件"nums[j] < nums[i]"的nums[j]，我们需要选一个最大的。
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {//这是前提条件，即前一个数必须比后一个数小才构成上升序列
                        //“以当前位置i结尾的最长子序列的值 = max（以前一个位置j结尾的最长子序列的长度 + 1（因为当前值 nums[i]也属于），当前值num[i]单独构成的子序列的长度”)。
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                    // 第找到一个符合上升序列的j就先更新一下result，
                    // 直到遍历结束，则所求结果即为最终的result
                    result = Math.max(result, dp[i]);
                }
            }
            return result;
        }
}
