package 数据结构与算法.LeetCode题解.动态规划;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。
 * 注意：当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

/**
 * 解法：使用动态规划，其实就是分而治之，其把区间无限分解成小区间
 */
public class 戳气球312 {
        public int maxCoins(int[] nums) {

            //定义一个新数组，即在原数组的收尾各加一个数字1，目的是可以方便地计算最左边和最右边的那个位置
            //则该新数组的长度就为nums.length + 2
            int new_len = nums.length + 2;//新数组的长度
            int[] new_nums = new int[new_len];

            //再把原数据填进去，且两头的值都设置为1
            new_nums[0] = new_nums[new_len - 1] = 1;
            for(int i = 0; i < nums.length; i++){
                new_nums[i+1] = nums[i];
            }

            //再定义一个与状态方程相关的二维数组，规格和新数组相同
            //其中，dp[left][right] 表示在开区间 (left, right) 中戳气球所能得到的最大金币数
            int[][] dp = new int[new_len][new_len];

            //返回结果就是求出在新区间的中所能获得的最大金币即可
            return dpMethod(dp, new_nums, 0, new_len - 1);
        }

        //真正的求法，该方法求的是dp[left][right]，即在开区间 (left, right) 中戳气球所能得到的最大金币数
        public int dpMethod(int[][] dp, int[] nums, int left, int right) {
            //确定初始情况
            if (left + 1 == right)
                return 0;

            // 因为原数组中可能有为0的位置，此情况必为0，可以不用考虑，只考虑大于0的情况即可，否则程序会超时
            if (dp[left][right] > 0){
                return dp[left][right];
            }


            // 处理一般情况，使用状态方程
            // 即从新数组的第二个元素（也就是原数组的第一个元素）开始遍历，
            // 对于每一个元素，其最大金币数就是在“第 i 个气球处所能得到的金币数以及其左右两部分分别能得到的最大金币数dp(left, i) + dp(i, right)
            // ”中取最大值即可（当然，再要和之前的result值比较，以保证result值永远为最大值），这里的result即为dp[left][right]。
            for (int i = left + 1; i < right; i++){
                //状态方程/递推关系式
                // dpCount(dp, nums, left, i) :向位置i的左边递归，求出其左边所能得到的最大金币数
                // dpCount(dp, nums, i, right)：向位置i的右边递归，求出其右边所能得到的最大金币数
                //每遍历一个元素，就把位置i处所能获得的最大金币数填入dp数组
                dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right]
                        + dpMethod(dp, nums, left, i) + dpMethod(dp, nums, i, right));
            }
            //返回最终结果，即dp（0，n-1），也就是整个原数组
            return dp[left][right];
        }
    }
