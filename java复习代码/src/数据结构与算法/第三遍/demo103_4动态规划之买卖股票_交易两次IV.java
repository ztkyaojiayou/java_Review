package 数据结构与算法.第三遍;

//只可交易两次
public class demo103_4动态规划之买卖股票_交易两次IV {
    public int maxProfit(int[] nums) {
        //第一次买入的最低价格
        int minPrice1 = Integer.MAX_VALUE;
        //第一次卖出时的利润
        int maxProfit1 = 0;
        //第二次买入后还剩余的利润
        int maxProfitAfterBuy = Integer.MIN_VALUE;
        //第二次卖出后的最终利润，也即所求的值
        int maxProfit2 = 0;
        for (int i = 0; i < nums.length; i++) {
            //以最低价格买
            minPrice1 = Math.min(minPrice1, nums[i]);
            //第一次卖出后得到的利润
            maxProfit1 = Math.max(maxProfit1, nums[i] - minPrice1);
            //第二次买入后的剩余利润
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy, maxProfit1 - nums[i]);
            //第二次卖出后得到的最终利润
            maxProfit2 = Math.max(maxProfit2, maxProfitAfterBuy + nums[i]);
        }
        return maxProfit2;
    }

    //自写一遍
    public int maxProfit01(int[] nums) {
//四个状态
        int minPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = 0;
        int maxProfitAfterBuy = Integer.MIN_VALUE;
        int finalProfit = 0;
        for (int num : nums) {
            minPrice1 = Math.min(minPrice1, num);
            maxProfit1 = Math.max(maxProfit1, num - minPrice1);
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy, maxProfit1 - num);
            finalProfit = Math.max(finalProfit, maxProfitAfterBuy + num);
        }
        return finalProfit;
    }
}
