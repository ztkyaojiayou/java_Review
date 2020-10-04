package 数据结构与算法.第三遍;

//（2）可交易多次，见到正利润就计入
public class demo77_动态规划_买卖股票的最佳时机II {
    public int maxProfit(int[] nums) {
        int max_profit = 0;
        int cur_profit = 0;
        for (int i = 1;i<nums.length;i++){
            cur_profit = nums[i] - nums[i-1];
            if (cur_profit > 0){
                max_profit += cur_profit;
            }//否则跳过
        }
        return max_profit;
    }
}
