package 数据结构与算法.第二遍.动态规划;

public class 使和为n的完全平方数的个数最小 {
public int numSquares(int n){
    //1.定义dp数组，
    // dp[i] 表示：组成i的完全平方数的个数的最小值，则所求结果即为dp[n]
    int[] dp = new int[n + 1];
    //2.确定初始值
    dp[0] = 0;
    dp[1] = 1;
    //3.一般情况
    for (int i=2;i<n;i++){ 
        for (int j=1;j*j<i;j++){
            dp[i] =Math.min(dp[i],dp[i-j*j]+1);
        }
    }
    //4.最后，返回所求结果即可
    return dp[n];
}

}
