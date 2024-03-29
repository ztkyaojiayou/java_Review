package 数据结构与算法.第三遍;

//连续子数组本来就有一个和，现在要求最大的和，
//因此肯定是需要在dp数组中再找到所有连续子数组的最大的值！！！
public class demo102_动态规划之连续子数组的最大和 {
    //思路2：使用动态规划（很经典但也很简单）
    private int FindGreatestSumOfSubArray02(int[] nums) {
        int[] dp = new int[nums.length];//dp[i]表示以 i 结尾的子串的最大值
        dp[0] = nums[0];//初始条件
        int res = nums[0];//要返回的结果值
        for (int i = 1; i < nums.length; i++) {//遍历数组
            //状态方程，即当前位置i的最大值是在“以前一个值i-1结尾的子串的和的最大值+当前遍历值nums[i]”（即此时累加之后的和一直为正数）
            //和“只取当前遍历值nums[i]”（即在此之前累加的和为负数，直接舍弃掉）中取最大值
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);//更新结果
        }
        return res;
    }

    //自写一遍
    private int FindGreatestSumOfSubArray002(int[] nums) {
        int len = nums.length;
        int res = nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + nums[i]);
            //小结：dp[i]求得是当前位置出的值，即便是dp[len-1],也只不过是最后一个位置处的值,
            //有些题确实只需求dp[len-1]，但也有一些题还需要求所有的dp值中的最大值，此时就需要再使用
            //max函数去找最大值啦，思路其实很清晰！！！
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    //思路1：只加正的
    private int FindGreatestSumOfSubArray01(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int cur_sum = arr[0];//表示当前的累加结果
        int res = cur_sum;//表示最终要返回的最大值
        for (int i = 1; i < arr.length; i++) {
            cur_sum += arr[i];//向后加，累加之后的结果重新赋给cur
            res = Math.max(res, cur_sum);//对比累加了arr[i]之后的res值和没累加之前的res值，取其最大值作为最终的res

            //关键点：cur如果没有累加出到负数，就继续往下走，否则cur置为0，重新从下一个数开始累加
            cur_sum = (cur_sum > 0 ? cur_sum : 0);
        }
        return res;
    }
}
