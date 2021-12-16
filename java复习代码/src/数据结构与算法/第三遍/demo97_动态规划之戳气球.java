package 数据结构与算法.第三遍;

public class demo97_动态规划之戳气球 {
    public int maxCoins(int[] nums) {
        int new_len = nums.length + 2;
        //构建新数组
        int[] new_nums = new int[new_len];
        new_nums[0] = new_nums[new_len - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            new_nums[i + 1] = nums[i];
        }
        //dp[i][j]就表示：戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高分数
        int[][] dp = new int[new_len][new_len];

        //返回最终结果即可
        return dpMethod(dp, new_nums, 0, new_len - 1);
    }

    /**
     * 具体方法,求的是开区间(left,right)的最高分数，用到了递归
     */
    private int dpMethod(int[][] dp, int[] nums, int left, int right) {
        //递归出口，即若区间之间没有值了，则易知分数为0
        if (left + 1 == right) {
            return 0;
        }
        //只返回有意义的值（较难想到）
        if (dp[left][right] > 0) {
            return dp[left][right];
        }

        //一般情况
        for (int i = left + 1; i < right; i++) {//从新数组的第二个位置起，其实也就是原数组的第一个位置
            dp[left][right] = Math.max(dp[left][right],//原值
                    nums[left] * nums[i] * nums[right] + //当前位置i
                            dpMethod(dp, nums, left, i) + //i的左边
                            dpMethod(dp, nums, i, right));//i的右边
        }
        return dp[left][right];
    }
}
