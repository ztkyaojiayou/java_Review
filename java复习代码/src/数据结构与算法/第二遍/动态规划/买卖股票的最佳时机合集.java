package 数据结构与算法.第二遍.动态规划;

public class 买卖股票的最佳时机合集 {
    //（1）入门版，只可买卖一次
    public int maxProfit(int[] nums) {
        int max_profit = 0;
        int min_price = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min_price) {
                min_price = nums[i];
            } else {
                max_profit = Math.max(max_profit, nums[i] - min_price);
            }
        }
        return max_profit;
    }

    //（2）进阶版1，可交易多次，
    //即可以买卖多只股票，但必须是一只股票先卖出，才能再买新股票继续交易（另外，肯定不能买之前已经交易过了的股票了啊，
    //即假如你在第三天卖出了自己的股票，则下一只股票只能买当天的或明天以后的了）

    /**
     * 算法流程：（思路太TM清晰啦）
     * 遍历整个股票交易日价格列表 price，策略是所有上涨交易日都买卖（赚到所有利润），所有下降交易日都不买卖（永不亏钱）。
     * 设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润，即 tmp = prices[i] - prices[i - 1] ；
     * 当该天利润为正 tmp > 0，则将利润加入总利润 profit；当利润为 0 或为负，则直接跳过；
     * 遍历完成后，返回总利润 profit。
     */
    public int maxProfit02(int[] nums) {
        int maxProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            int cur_profit = nums[i] - nums[i - 1];//可能为负，即亏损
            if (cur_profit > 0) {
                maxProfit += cur_profit;
            }
        }
        return maxProfit;
    }

    /**
     * （3）进阶版2：最佳买卖股票时机含冷冻期：可进行多次交易，但卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，（区别1）你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * （区别2）当天卖出股票后，你无法在第二天买入股票 (即冷冻期/间隔期为 1 天)。
     * <p>
     * 示例:
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */

    public int maxProfit03(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        /**
         * 定义两个状态：
         * (1)0: 表示当天不持有股票，于是dp[i][0]就表示：第i天不持有股票时的最大利润，此时就有两种情况：
         *    1）今天本来持有股票的，但是把它卖了：此时的利润就为：昨天持有股票时的利润+今天卖掉该股票的利润,即：
         *    dp[i][0] = dp[i-1][1] + price[i]
         *    2）昨天就没有，而今天又啥也没干，所以此时的利润就为：昨天没有持有股票时的利润，即为：
         *    dp[i][0] = dp[i-1][0]
         *    3)最后，再取二者的最大值即可
         *    4)易知，初始值即为：dp[0][0] = 0;
         *
         * (2)1：表示当天持有股票，于是dp[i][1]就表示：第i天持有股票时的最大利润，此时也有两种情况：
         *    1）今天刚买的:则由于冷冻期的存在，一定是因为前天卖了一次股票，所以今天才可以买(负利润/成本），于是此时利润就可以表示为：
         *       dp[i][1] = dp[i-2][0] - price[i]，即前一天卖了股票的利润-今天买入的成本
         *    2）昨天就有:于是今天就不能买，因为昨天的都还没有卖呀，所以今天的利润就是昨天的利润，于是此时利润就可以表示为：
         *       dp[i][1] = dp[i-1][1]，即昨天还持有股票没卖时的利润
         *    3）最后，再取二者的最大值即可
         *    4)易知，初始值即为：dp[0][1] = 0 - price[0]，即自费买的第一支股票，即为成本;
         */
//定义dp数组
        int[][] dp = new int[nums.length][2];
        //确定初始值
        dp[0][0] = 0;
        dp[0][1] = 0 - nums[0];
//处理一般情况
        for (int i = 1; i < nums.length; i++) {

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]);
            dp[i][1] = Math.max(dp[i - 1][1], ((i - 2) >= 0 ? dp[i - 2][0] : 0) - nums[i]);//i-2>0时
        }
        //最后，返回结果
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }


/**
 * (4)高阶版：只可买卖两次，但没有冷冻期
 */
        public int maxProfit04(int[] nums){
            //把两次交易都看成在一天内完成，这一点非常重要
            int min_price1 = Integer.MAX_VALUE;//第一次买入时的价格，尽量低（使用min）
            int max_Profit1 = 0;//第一次卖出时的利润，尽量大（使用max）
            int maxProfitAfterBuy1 = Integer.MIN_VALUE;//第二次买入时还剩余的利润，尽量大（使用max）
            int max_Profit2 = 0;//第二次卖出后最终剩余的利润，尽量大（使用max）
            for (int i = 0; i < nums.length; i++) {
                min_price1 = Math.min(min_price1, nums[i]);
                max_Profit1 = Math.max(max_Profit1, nums[i] - min_price1);
                maxProfitAfterBuy1 = Math.max(maxProfitAfterBuy1, max_Profit1 - nums[i]);
                max_Profit2 = Math.max(max_Profit2, maxProfitAfterBuy1 + nums[i]);
            }
            return max_Profit2;
        }
}
