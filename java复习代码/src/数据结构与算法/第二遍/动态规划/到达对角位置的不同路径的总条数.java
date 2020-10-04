package 数据结构与算法.第二遍.动态规划;

public class 到达对角位置的不同路径的总条数 {
    //非常标准的动态规划写法
    public int diffPath(int m,int n){//传入的是矩阵的行和列，若直接输入矩阵/二维数组，则还要先求出行和列
        //1.先定义一个数组dp,dp[i][j]就表示到达该位置的总路径
        int[][] dp = new int[m][n];
        //2.确定初值
        for (int i=0;i<m;i++){
            dp[i][0] = 1;
        }
        for (int j=0;j<n;j++){
            dp[0][j] = 1;
        }
        //3.处理一般情况，使用多台方程即可，两个for循环就ok，都是套路
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];//求的是路径条数，易知与max就没什么关系，因为与最值没关系
            }
        }
        //4.最后，返回结果即可
        return dp[m-1][n-1];
    }

}
