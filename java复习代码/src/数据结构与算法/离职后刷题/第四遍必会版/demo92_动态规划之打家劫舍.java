package 数据结构与算法.离职后刷题.第四遍必会版;

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

}
