package 数据结构与算法.LeetCode题解.动态规划;

/**
 *
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:
 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

 进阶:
 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */


/**
 * 方法1：使用动态规划（推荐）
 *第 i 个子组合的最大值可以通过第i-1个子组合的最大值和第 i 个数字获得，
 * 如果第 i-1 个子组合的最大值没法给第 i 个数字带来正增益，
 * 我们就抛弃掉前面的子组合，自己就是最大的了。
 */
public class 最大子序和53 {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];//dp[i]表示以 i 结尾d1子串的最大值
        dp[0] = nums[0];//初始条件
        int result = nums[0];//要返回的结果值
        for (int i = 1; i < nums.length; i++) {//遍历数组
            dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
            //状态方程，即当前位置i的最大值是在“以前一个值i-1结尾的子串的和的最大值+当前遍历值nums[i]”（即此时累加之后的和一直为正数）
            //和“只取当前遍历值nums[i]”（即再在此之前累加后的和为负数，直接舍弃掉）中取最大值
            if (dp[i] > result ) {
                result = dp[i];//把结果值赋给result并返回
            }
        }
        return result;//返回结果值即可
    }
}

//方法2：“只累计对结果有增益的值”（该方法也推荐）
//设当前最大连续子序列和为 curSum，结果为 result（类比剑指offer第45题）
//如果 curSum > 0，则说明 curSum 对结果有增益效果，则 curSum 保留并加上当前遍历数字
//如果 curSum <= 0，则说明 curSum 对结果无增益效果，需要舍弃，则 curSum 直接更新为当前遍历数字
//每次比较 curSum 和 ans的大小，将最大值置为result，遍历结束返回结果
//时间复杂度：O(n)
class Solution53 {
    public int maxSubArray(int[] nums) {
                int result = nums[0];//结果值
                int curSum = 0;//当前和
                //for(int num: nums) {//遍历数组的高级写法
                    for(int i = 0;i < nums.length;i++){//遍历数组
                        if(curSum > 0) {
                            curSum += nums[i];
                        } else {
                            curSum = nums[i];
                        }
                        result = Math.max(result, curSum);//和上一次的结果值比较，从而获取真正的结果值
                    }
                return result;
            }
        }
