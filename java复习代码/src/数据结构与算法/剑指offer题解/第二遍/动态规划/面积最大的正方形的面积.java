package 数据结构与算法.剑指offer题解.第二遍.动态规划;

/**
 * (类比85题：最大矩形）
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 */
public class 面积最大的正方形的面积 {
    public int maxSquare(int[][] nums,int m,int n){//输入的是行和列，另外，该在矩阵的值要么是1要么是0
        //0.最大边长
        int maxSize =0;
        //1.定义dp数组
        //dp[i][j] 表示 以 (i,j) 为右下角，且只包含 1 的正方形的边长的最大值
        int[][] dp = new int[m][n];
        //2.确定初始值
       for (int i=0;i<m;i++){
           dp[i][0] = 1;
       }
       for (int j=0;j<n;j++){
           dp[0][j]=1;
       }
      //3.一般情况
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
if (nums[i][j] =='1'){
    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
}
         //时刻更新
         maxSize = Math.max(maxSize,dp[i][j]);
            }
        }
        //4.返回最大正方形的面积
        return maxSize*maxSize;
    }
}
