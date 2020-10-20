package 数据结构与算法.第三遍;

public class demo92_动态规划之打家劫舍 {
    public int rob(int[] nums) {
        int len = nums.length;
        //dp[i] 表示 从前 i 个房屋中能抢劫到的最大数额，
        //则所求结果即为 dp[len - 1]（因为索引从0开始）
        int[] dp = new int[len];
        //初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        //一般情况
        for (int i = 2;i<len;i++){
            dp[i] = Math.max(nums[i] + dp[i-2],dp[i-1]);//偷或不偷当前房屋，选较大值即可
        }
        //最后返回结果即可
        return dp[len-1];
    }
}
