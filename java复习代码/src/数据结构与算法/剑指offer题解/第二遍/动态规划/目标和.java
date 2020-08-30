package 数据结构与算法.剑指offer题解.第二遍.动态规划;


public class 目标和 {
    public int findSWays(int[] nums,int S){
        int sum= 0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int target = (S + sum) / 2;
        if (sum < S || (target % 2 == 1)){
            return 0;
        }
        //dp[i] 表示从前面的元素（物品）中选取若干放入背包承重量为i的恰好装
        // 满的方案数(和二维的稍有不同，这里相当于只看结果啦）
        int[] dp = new int[S + 1];
        dp[0]=1;
        for (int i=1;i<nums.length;i++){//务必逆序，即倒着推
            //因为倒着推。每个物品只有一次选择的机会，放或者不放。
            //而如果是正向推，则每个物品在每次都可以有两种选择，即放或者不放。
            //所以是完全背包，不是不可以，只是写法上会不太一样。
            for (int j=target;j>nums[i-1];j--){
                dp[j] = dp[j] + dp[j-nums[i-1]];
            }
        }
        //返回结果
        return dp[target];
    }
}
