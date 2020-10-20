package 数据结构与算法.第三遍;

public class demo88_动态规划之是否可以分隔等和子集 {
    public boolean canPartition(int[] nums) {
        //先求数组的和
        int sum = 0;
        for (int i = 0;i<nums.length;i++){
            sum += nums[i];
        }
        //若为奇数，则肯定不行
        if (sum % 2 == 1){
            return false;
        }
        int len = nums.length;
        //和上一题（demo87）几乎相同，只是target不同而已
        int target = sum / 2;
        //dp[i][j]表示在总和最大为j的情况下，对前i个元素进行选择并装入后是否可以装满的状态，即true或false。
        boolean[][] dp = new boolean[len + 1][target + 1];
        //初始化
        for (int i = 0;i<target+1;i++){
            dp[0][i] = false;
        }
        for (int j = 0;j<len+1;j++){
            dp[j][0] = true;
        }

        //一般情况
        for (int i = 1;i<len + 1;i++){
            for (int j = 1;j<target+1;j++){
                if (j < nums[i-1]){//装不下，即维持原样
                    dp[i][j] = dp[i-1][j];
                }else {//可以装下，但可装可不装，装满即可，即取并集即可
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];//由于是判断，而不是求所有方法数，因此不是相加，而是取并集即可
                }
            }
        }
        //最后返回结果即可
        return dp[len][target];
    }
}
