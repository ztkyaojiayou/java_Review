package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Arrays;

/**
 * 递归三部曲:
 * - 选择递归函数的参数
 * - 终止条件
 * - 递归方向
 * 基本上所有动态规划都可以又递归式子进行转化（这个感觉说反了吧？）
 * 动态规划（对于特例的判断是不包括在其中的！！！）
 * - baseCase 初始化（基础能推断的数据？）
 * - 状态转移方程（递推公式？）--就是用dp数组的元素来递推，同时也就缓存了结果啦（状态方程所定义的含义非常重要！！！）
 * - 缓存中间结果--就是使用dp数组
 * - 顺序问题（例如知道0和1的情况，推断n的结果，n->0递归，0->n dp）
 */
public class demo92_动态规划之打家劫舍 {
    public int rob(int[] nums) {
        int len = nums.length;
        //dp[i] 表示 从前 i 个房屋中能抢劫到的最大数额，
        //则所求结果即为 dp[len - 1]（因为索引从0开始）
        int[] dp = new int[len];
        //初始化，也叫baseCase
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //一般情况
        for (int i = 2; i < len; i++) {
            //状态转移方程
            //偷或不偷当前房屋，选较大值即可
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        //最后返回结果即可
        return dp[len - 1];
    }

    //自写一遍
    public int rob02(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2 + nums[i]]);
        }
        return dp[len - 1];
    }



    /**
     * （2）进阶版：
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * （与入门版的区别来啦）这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
     *
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *

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
    class 打家劫舍进阶版213 {
        public int rob(int[] nums) {
//            if(nums.length == 0) {
//                return 0;
//            }
//            if(nums.length == 1) {
//                return nums[0];
//            }
            //要用到Arrays.copyOfRange(T[ ] original,int from,int to)方法
            //其作用是：将原始数组original，从下标from开始复制，复制到上标to，生成一个新的数组。且新数组中包括下标from，但不包括上标to。
            return Math.max(rob198(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                    rob198(Arrays.copyOfRange(nums, 1, nums.length)));
        }

        //再直接调用入门版的方法即可
        private int rob198(int[] nums) {
//            //0.特判：数组长度为0和1时（则后面只需从2开始即可）
//            if (nums.length == 0) {
//                return 0;
//            } else if (nums.length == 1) {
//                return nums[0];
//            }
            //1.定义一个与状态方程相关的一维数组，其中，dp[i] 表示 从前 i 个房屋中能抢劫到的最大数额，
            //则所求结果即为 dp[dp.length - 1]（因为索引从0开始）
            int[] dp = new int[nums.length];
            //2.再确定初始值，即当长度为1和为2时，至于结果，前面已经分析过了。
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            //3.再看一般情况，使用状态方程化为初始情况即可
            for (int i = 2; i < nums.length; i++) {//下标从2开始即可
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }
            //4.获取所求结果并返回即可
            return dp[dp.length - 1];
        }
    }

}
