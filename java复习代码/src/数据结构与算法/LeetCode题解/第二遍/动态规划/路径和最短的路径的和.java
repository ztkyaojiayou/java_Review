package 数据结构与算法.LeetCode题解.第二遍.动态规划;

public class 路径和最短的路径的和 {
    //非常标准的写法
    public int minPathSum(int[][] nums){
        //0.获取行和列
        int m = nums.length;//行
        int n = nums[0].length;//列
        //1.定义一个二维数组，规格和原数组相同
        // 其中的dp[i][j]表示从nums[0][0]到nums[i][j]的路径和最小的路径的和，即为所求（重点）
        int[][] dp = new int[m][n];
        //2.初始化
        dp[0][0] =nums[0][0];
        for (int i = 1;i<m;i++){//第一列，只有横着走
            dp[i][0] = dp[i-1][0] + nums[i][0];
        }
        for (int j = 1;j<n;j++){//第一行，只有竖着走
            dp[0][j] = dp[0][j-1] = nums[0][j];
        }
        //3.处理一般情况，即可以从左边来，也可以从右边来
        //两个for循环就可以搞定，对于动态规划，不要去管时间复杂度（一般都是O(n2))
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + nums[i][j];//可以从左边来，也可以从右边来
            }
        }
        //4.最后，返回结果即可，即取最右下角的值
        return dp[m-1][n-1];
    }
}
