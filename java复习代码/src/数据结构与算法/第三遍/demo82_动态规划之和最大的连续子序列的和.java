package 数据结构与算法.第三遍;

/**
 * @author :zoutongkun
 * @date :2022/4/20 3:34 下午
 * @description :
 * @modyified By:
 * <p>
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 注意：这里所说的连续不等于递增
 * <p>
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 注意：这里所说的连续不等于递增
 * <p>
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

/**
 *
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:
 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 注意：这里所说的连续不等于递增

 进阶:
 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */


/**
 * 方法1：使用动态规划（推荐）
 *第 i 个子组合的最大值可以通过第i-1个子组合的最大值和第 i 个数字获得，
 * 如果第 i-1 个子组合的最大值没法给第 i 个数字带来正增益，
 * 我们就抛弃掉前面的子组合，自己就是最大的了。
 */

public class demo82_动态规划之和最大的连续子序列的和 {
    public int maxSubArray(int[] nums) {
        //dp[i]表示以 i 结尾的连续子串的最大值
        int[] dp = new int[nums.length];
        //初始条件
        dp[0] = nums[0];
        //要返回的结果值
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {//遍历数组
            //状态方程，即以当前位置i结尾所构成的子串的和的最大值是在“以前一个位置i-1结尾的子串的和的最大值+当前遍历值nums[i]”（即此时累加之后的和一直为正数）
            //和“只取当前遍历值nums[i]”（即再在此之前累加后的和为负数，直接舍弃掉）中取最大值
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            //这个题和打家劫舍不同，这里得到的dp[i]只是以当前位置结尾所构成的子串的和的最大值，
            //即即便是dp[len-1]也只是以最后一个位置结尾所构成的子串中的连续子序列的和的最大值，
            // 还必须和原来的最值进行比较
            result = Math.max(result, dp[i]);//更新结果值
        }
        return result;//返回结果值即可
    }

    //自写一遍
    public int maxSubArray02(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], dp[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
