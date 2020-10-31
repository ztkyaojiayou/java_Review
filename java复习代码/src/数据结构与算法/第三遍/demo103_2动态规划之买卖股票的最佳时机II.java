package 数据结构与算法.第三遍;

//（2）可交易多次，见到正利润就计入
public class demo103_2动态规划之买卖股票的最佳时机II {
    public int maxProfit(int[] nums) {
        int max_profit = 0;
        int cur_profit;
        for (int i = 1; i < nums.length; i++) {
            //当前利润
            cur_profit = nums[i] - nums[i - 1];
            //若为正，就加上
            if (cur_profit > 0) {
                max_profit += cur_profit;
            }//否则跳过
        }
        return max_profit;
    }
}
