package 数据结构与算法.离职后刷题.第四遍必会版;

//类比：构成目标和的最少完全平方数的个数
public class demo93_动态规划之最长上升子序列的长度 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int res = 0;
        //dp[i] 表示 以nums[i] 结尾的序列的最长上升子序列的长度
        //也即： nums前i个数字的最长子序列长度。
        int[] dp = new int[len];
        //初始化
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {//若小于，则可以状态转移
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                //随时更新，因为dp[i]表示的只是以num[i]结尾时的最长上升子序列的长度，
                //但不等于是整个数组中的最长上升子序列
                res = Math.max(res, dp[i]);
            }
        }
        //返回最终结果
        return res;
    }

    //自写一遍
    public int lengthOfLIS01(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 在当前元素前面的所有小于当前值的元素都可以和当前元素构成上升序列，
                // 即都是备选项，因此还需要选出最大值
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                // 再比较以当前元素为结尾所形成的最长的上升子序列的长度和之前的最大值，
                //相当于要在所有的dp[i]中找出最大值，从而得到最终的最大值
                // 而上面求出的每一个dp[i]都只是以当前位置结尾处的最大值，即只是一个位置的值，
                //对于“构成目标和的最少完全平方数的个数”这个题时，由于只需要最后一个值的dp值即可，
                //因此最后一个值就是题给的target值，则它的dp即为所求呀，
                // 而不需要再去所有的dp[i]中再找最大值了，否则就变成了从1至target的所有数中构成各个目标值的最少完全平方数的最小值了
                // 务必理解这个逻辑！！！
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
