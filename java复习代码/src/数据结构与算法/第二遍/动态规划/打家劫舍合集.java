package 数据结构与算法.第二遍.动态规划;

import java.util.Arrays;

public class 打家劫舍合集 {
    //(1)基础版
    public int rob(int[] nums){
        //dp[i] 表示 从前 i 个房屋中能抢劫到的最大数额，
        //则所求结果即为 dp[dp.length - 1]（因为索引从0开始）
        int[] dp = new int[nums.length];
        //确认初始值
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        //一般情况
        for (int i=2;i<nums.length;i++){
            dp[i] = Math.max(nums[i] + dp[i-2],dp[i-1]);
        }
        //最终结果
        return dp[dp.length-1];
    }

    // （2）进阶版：
    //(与入门版的区别来啦）这个地方所有的房屋都围成一圈，
    // 这意味着第一个房屋和最后一个房屋是紧挨着的。
    /**
     * 总体思路：
     * 此题是 198.打家劫舍 的拓展版： 唯一的区别是此题中的房间是环状排列的（即首尾相接），
     * 而 198题中的房间是单排排列的；而这也是此题的难点。
     * 环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，
     * 因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
     * (1)在不偷窃第一个房子的情况下（即nums[1:length)），最大金额是 p1；
     * (2)在不偷窃最后一个房子的情况下（即 nums[0:length-1)），最大金额是 p2。
     *
     * 再综合偷窃最大金额：易知，即为以上两种情况的较大值，即 max(p1,p2)。
     * 即：其实就是做两次198题的动态规划，一次是[0,n-2]，一次是[1,n-1]，再取二者中的较大值即可
     */
    public int rob02(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        //要用到Arrays.copyOfRange(T[] original,int from,int to)方法
        //其作用是：将原始数组original，从下标from开始复制，复制到上标to，生成一个新的数组。
        // 且新数组中包括下标from，但不包括上标to。
        return Math.max(rob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                rob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public int rob03(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int res = Math.max(rob(Arrays.copyOfRange(nums,0,nums.length-1)),rob(Arrays.copyOfRange(nums,1,nums.length)));
    return res;
    }
}
