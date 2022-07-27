package 数据结构与算法.离职后刷题.第四遍必会版;

//（1）只可交易一次，则只要存在利润，就卖出，再比较得到最大利润
public class demo103_1动态规划之买卖股票_交易一次I {
    public int maxProfit(int[] nums) {
        int max_profit = 0;//最大利润
        int min_price = nums[0];//最低价格
        for (int i = 1; i < nums.length; i++) {
            //随时更新最低价
            min_price = Math.min(min_price, nums[i]);
            //随时更新最大利润，所求的最大利润就是以某一个低价买入，另一个价卖出的结果
            max_profit = Math.max(max_profit, nums[i] - min_price);
        }
        return max_profit;
    }


    //自写一遍
    public int maxProfit02(int[] nums) {
        int maxProfit = 0;
        int minPrice = nums[0];
        for (int num : nums) {
            minPrice = Math.min(minPrice, num);
            maxProfit = Math.max(maxProfit, num - minPrice);
        }
        return maxProfit;
    }
}
