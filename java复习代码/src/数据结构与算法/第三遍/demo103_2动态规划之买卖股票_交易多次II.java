package 数据结构与算法.第三遍;

//（2）可交易多次，则只需见到正利润就交易，最后累加即可
public class demo103_2动态规划之买卖股票_交易多次II {
    public int maxProfit(int[] nums) {
        int max_profit = 0;
        int cur_profit = 0;
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

    //自写一遍
    public int maxProfit02(int[] nums) {
        int maxProfit = 0;
        int curProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            //计算每两天的收益
            curProfit = nums[i] - nums[i - 1];
            //逮到有收益就冲！
            if (curProfit > 0) {
                maxProfit += curProfit;
            }
        }
        return maxProfit;
    }
}
