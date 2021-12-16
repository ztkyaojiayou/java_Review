package 数据结构与算法.第三遍;

//只可交易两次
public class demo103_4动态规划之买卖股票的最佳时机IV {
    public int maxProfit(int[] nums) {
        int minPrice1 = Integer.MAX_VALUE;//第一次买入的最低价格
        int maxProfit1 = 0;//第一次卖出时的利润
        int maxProfitAfterBuy = Integer.MIN_VALUE;//第二次买入后还剩余的利润
        int maxProfit2 = 0;//第二次卖出后的最终利润
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minPrice1) {
                minPrice1 = nums[i];//获得最低价格，以最低价格买
            }
            //minPrice1 = Math.min(minPrice1,nums[i]);//以最低价格买
            maxProfit1 = Math.max(maxProfit1, nums[i] - minPrice1);//第一次卖出后得到的利润
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy, maxProfit1 - nums[i]);//第二次买入后的剩余利润
            maxProfit2 = Math.max(maxProfit2, maxProfitAfterBuy + nums[i]);//第二次卖出后得到的最终利润
        }
        return maxProfit2;
    }
}
