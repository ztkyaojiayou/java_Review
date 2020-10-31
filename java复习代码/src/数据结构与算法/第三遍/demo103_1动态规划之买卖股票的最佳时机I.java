package 数据结构与算法.第三遍;

//（1）只可交易一次
public class demo103_1动态规划之买卖股票的最佳时机I {
    public int maxProfit(int[] nums) {
        int max_profit = 0;
        int min_price = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min_price) {
                min_price = nums[i];
            } else {
                max_profit = Math.max(max_profit, nums[i] - min_price);
            }
        }
        return max_profit;
    }
}
