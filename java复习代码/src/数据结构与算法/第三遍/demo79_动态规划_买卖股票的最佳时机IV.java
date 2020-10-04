package 数据结构与算法.第三遍;

//只可交易两次
public class demo79_动态规划_买卖股票的最佳时机IV {
    public int maxProfit(int[] nums) {
        int minPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = 0;
        int maxProfitAfterBuy = Integer.MIN_VALUE;
        int maxProfit2 = 0;
        for (int i = 0;i<nums.length;i++){
            //if (nums[i] < minPrice1){
            //    minPrice1 = nums[i];//以最低价格买
            //}
            minPrice1 = Math.min(minPrice1,nums[i]);
            maxProfit1 = Math.max(maxProfit1,nums[i] - minPrice1);//第一次卖
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy,maxProfit1-nums[i]);//第二次买
            maxProfit2 = Math.max(maxProfit2,maxProfitAfterBuy+nums[i]);//第二次卖
        }
        return maxProfit2;
    }
}
